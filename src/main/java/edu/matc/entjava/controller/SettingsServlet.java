package edu.matc.entjava.controller;

import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

    private GenericDao<User> userDao;

    @Override
    public void init() {
        userDao = new GenericDao<>(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("logIn");
            return;
        }

        req.getRequestDispatcher("/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        user.setName(req.getParameter("name"));
        user.setUsername(req.getParameter("username"));

        userDao.update(user);

        session.setAttribute("user", user);

        resp.sendRedirect("settings");
    }
}