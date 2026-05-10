package edu.matc.entjava.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 1. Destroy local session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 2. Redirect to Cognito logout
        String logoutUrl =
                "https://mediabacklog-auth.auth.us-east-2.amazoncognito.com/logout"
                        + "?client_id=1scsm0vn6c3a42btor1f69gv7o"
                        + "&logout_uri=https://mediabacklog-env.eba-xs4nepzw.us-east-2.elasticbeanstalk.com/";

        resp.sendRedirect(logoutUrl);
    }
}