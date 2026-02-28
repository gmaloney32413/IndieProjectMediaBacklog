package edu.matc.entjava.web;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.MediaItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.*;

@WebServlet("/editBacklog")
public class EditBacklogServlet extends HttpServlet {

    private BacklogEntryDao backlogEntryDao;
    private MediaItemDao mediaItemDao;

    @Override
    public void init() {
        backlogEntryDao = new BacklogEntryDao();
        mediaItemDao = new MediaItemDao();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        final long currentUserId = 1L; // hardcoded for now

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("backlog");
            return;
        }

        Long id = Long.parseLong(idParam);
        BacklogEntry entry = backlogEntryDao.getById(id);

        // Only allow access if the entry belongs to user ID 1
        if (entry == null || !entry.getUser().getId().equals(currentUserId)) {
            response.sendRedirect("backlog");
            return;
        }

        request.setAttribute("backlogStatuses", BacklogStatus.values());
        request.setAttribute("backlogEntry", entry);

        request.getRequestDispatcher("/editBacklog.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the form
        String idParam = request.getParameter("id"); // will be null if adding
        String statusParam = request.getParameter("status");
        String userRatingParam = request.getParameter("userRating");
        String notesParam = request.getParameter("notes");

        BacklogEntry entry;

        if (idParam != null && !idParam.isEmpty()) {
            // Editing an existing entry
            Long id = Long.parseLong(idParam);
            entry = backlogEntryDao.getById(id);
            if (entry == null) {
                // If somehow the entry doesn't exist, redirect back
                response.sendRedirect("backlog");
                return;
            }
        } else {
            // Adding a new entry
            entry = new BacklogEntry();
            // TODO: set the current user and mediaItem if adding
            // entry.setUser(currentUser);
            // entry.setMediaItem(selectedMediaItem);
        }

        // Set/update fields from the form
        if (statusParam != null && !statusParam.isEmpty()) {
            entry.setStatus(BacklogStatus.valueOf(statusParam));
        }

        if (userRatingParam != null && !userRatingParam.isEmpty()) {
            entry.setUserRating(Integer.parseInt(userRatingParam));
        } else {
            entry.setUserRating(null);
        }

        entry.setNotes(notesParam);

        // Save to database (update or insert)
        backlogEntryDao.update(entry);

        // Redirect to the backlog listing page
        response.sendRedirect("backlog"); // make sure /backlog maps to your backlog.jsp servlet
    }
}