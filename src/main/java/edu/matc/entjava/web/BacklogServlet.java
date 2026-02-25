package edu.matc.entjava.web;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.persistence.BacklogEntryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/backlog")
public class BacklogServlet extends HttpServlet {

    private final BacklogEntryDao backlogEntryDao = new BacklogEntryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO: Replace with actual logged-in user ID from session
        Long userId = (long)1;

        List<BacklogEntry> backlogEntries = backlogEntryDao.getByUserId(userId);

        // Add the list to the request
        request.setAttribute("backlogEntries", backlogEntries);

        // Forward to JSP
        request.getRequestDispatcher("/backlog.jsp").forward(request, response);
    }
}