package edu.matc.entjava.controller;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/backlog")
public class BacklogServlet extends HttpServlet {

    private GenericDao<BacklogEntry> backlogDao;

    @Override
    public void init() {
        backlogDao = new GenericDao<>(BacklogEntry.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO: Replace with actual logged-in user ID from session
        Long userId = (long)1;
        User user = new User();
        user.setId(userId);


        //HttpSession session = request.getSession(false);

        /*
        // Redirect if not logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("logIn"); // adjust if your login path is different
            return;
        }

        User user = (User) session.getAttribute("user");
        Long userId = user.getId();

         */

        List<BacklogEntry> backlogEntries = backlogDao.getByPropertyEqual("user", user);

        // Add the list to the request
        request.setAttribute("backlogEntries", backlogEntries);

        // Forward to JSP
        request.getRequestDispatcher("/backlog.jsp").forward(request, response);
    }
}