package edu.matc.entjava.auth.web;

import edu.matc.entjava.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
    name = "start",
        urlPatterns = {"/startup"},
        loadOnStartup = 1
)
public class StartupServlet extends HttpServlet implements PropertiesLoader{

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String message;

    public void init() throws ServletException {

        logger.info("Loading Cognito properties...");

        try {
            Properties properties = loadProperties("/cognito.properties");

            ServletContext context = getServletContext();

            context.setAttribute("CLIENT_ID", properties.getProperty("client.id"));
            context.setAttribute("CLIENT_SECRET", properties.getProperty("client.secret"));
            context.setAttribute("OAUTH_URL", properties.getProperty("oauthURL"));
            context.setAttribute("LOGIN_URL", properties.getProperty("loginURL"));
            context.setAttribute("REDIRECT_URL", properties.getProperty("redirectURL"));
            context.setAttribute("REGION", properties.getProperty("region"));
            context.setAttribute("POOL_ID", properties.getProperty("poolId"));

        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}