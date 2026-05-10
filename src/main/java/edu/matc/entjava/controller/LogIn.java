package edu.matc.entjava.controller;

import edu.matc.entjava.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/logIn"}
)

/** Begins the authentication process using AWS Cognito
 *
 */
public class LogIn extends HttpServlet implements PropertiesLoader {
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            logger.info("Loading properties...");
            properties = loadProperties("/cognito.properties");
            logger.info("Properties loaded = " + (properties != null));

            if (properties == null) {
                throw new ServletException("cognito.properties not found");
            }

            CLIENT_ID = properties.getProperty("CLIENT_ID");
            LOGIN_URL = properties.getProperty("LOGIN_URL");
            REDIRECT_URL = properties.getProperty("REDIRECT_URL");

            logger.info("Login servlet initialized successfully");

        } catch (Exception e) {
            logger.error("Failed to load cognito.properties", e);
            throw new ServletException(e);
        }
    }


    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO if properties weren't loaded properly, route to an error page
        if (LOGIN_URL == null || REDIRECT_URL == null || CLIENT_ID == null) {
            //
            RequestDispatcher rd = req.getRequestDispatcher("authError.jsp");
            rd.forward(req, resp);
        } else{
            String url = LOGIN_URL
                    + "?response_type=code"
                    + "&client_id=" + CLIENT_ID
                    + "&redirect_uri=" + REDIRECT_URL
                    + "&scope=openid+email+profile";
            resp.sendRedirect(url);
        }
    }
}
