package edu.matc.entjava.web;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.MediaItemDao;
import edu.matc.entjava.persistence.UserDao;

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
    private UserDao userDao;

    @Override
    public void init() {
        backlogEntryDao = new BacklogEntryDao();
        mediaItemDao = new MediaItemDao();
        userDao = new UserDao();
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

        Long mediaId = Long.parseLong(idParam);

        // Try to find an existing backlog entry for this user and media item
        BacklogEntry entry = backlogEntryDao.getByUserAndMedia(currentUserId, mediaId);

        // Only allow access if the entry belongs to user ID 1
        if (entry == null) {
            // No entry exists, create a new backlog entry for this media item
            entry = new BacklogEntry();

            // Set the user
            User currentUser = userDao.getById((int) currentUserId);
            entry.setUser(currentUser);

            // Set the media item from the MediaItemDao
            entry.setMediaItem(mediaItemDao.getById(mediaId));

            // Optionally set default values for status, rating, notes
            entry.setStatus(BacklogStatus.PLANNED); // or null if you prefer
            entry.setUserRating(null);
            entry.setNotes("");
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
        String entryIdParam = request.getParameter("entryId");
        String mediaIdParam = request.getParameter("mediaId");
        String statusParam = request.getParameter("status");
        String userRatingParam = request.getParameter("userRating");
        String notesParam = request.getParameter("notes");

        BacklogEntry entry;

        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            // Editing an existing entry
            Long id = Long.parseLong(entryIdParam);
            entry = backlogEntryDao.getById(id);
            if (entry == null) {
                // If somehow the entry doesn't exist, redirect back
                response.sendRedirect("backlog");
                return;
            }
        } else {
            // Adding a new entry
            entry = new BacklogEntry();

            Long mediaId = Long.parseLong(request.getParameter("mediaId"));

            User user = userDao.getById(1);
            entry.setUser(user);
            entry.setMediaItem(mediaItemDao.getById(mediaId));
            entry.setDateAdded(new java.util.Date());
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