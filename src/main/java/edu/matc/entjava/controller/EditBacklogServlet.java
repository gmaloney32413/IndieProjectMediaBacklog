package edu.matc.entjava.controller;


import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.GenericDao;
import edu.matc.entjava.persistence.MediaItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editBacklog")
public class EditBacklogServlet extends HttpServlet {

    private BacklogEntryDao backlogEntryDao;
    private MediaItemDao mediaItemDao;
    private GenericDao<User> userDao;

    @Override
    public void init() {
        backlogEntryDao = new BacklogEntryDao();
        mediaItemDao = new MediaItemDao();
        userDao = new GenericDao<>(User.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        final long currentUserId = 1L;

        String entryIdParam = request.getParameter("entryId");
        BacklogEntry entry = null;

        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            entry = backlogEntryDao.getById(Long.parseLong(entryIdParam));
        }

        MediaItem mediaItem = null;

        if (entry != null) {
            // editing existing entry
            mediaItem = entry.getMediaItem();
        } else {
            // existing logic for mediaItem via id or tmdbId
            String idParam = request.getParameter("id");
            String tmdbIdParam = request.getParameter("tmdbId");

            // Case 1: Media item already exists in DB
            if (idParam != null && !idParam.isEmpty()) {
                Long mediaId = Long.parseLong(idParam);
                mediaItem = mediaItemDao.getById(mediaId);
            }
            // Case 2: Item came from TMDB search results
            else if (tmdbIdParam != null && !tmdbIdParam.isEmpty()) {
                Long tmdbId = Long.parseLong(tmdbIdParam);
                mediaItem = mediaItemDao
                        .getByPropertyEqual("tmdbId", tmdbId)
                        .stream()
                        .findFirst()
                        .orElse(null);

                if (mediaItem == null) {
                    String mediaType = request.getParameter("mediaType");

                    if ("movie".equalsIgnoreCase(mediaType)) {
                        mediaItem = new edu.matc.entjava.entity.Movie();
                    } else if ("tv".equalsIgnoreCase(mediaType)) {
                        mediaItem = new edu.matc.entjava.entity.TvShow();
                    }
                    mediaItem.setTmdbId(tmdbId);
                    mediaItem.setTitle(request.getParameter("title"));
                    mediaItem.setOverview(request.getParameter("overview"));
                    mediaItem.setPosterUrl(request.getParameter("posterUrl"));
                }
            } else {
                response.sendRedirect("backlog");
                return;
            }

            // No existing entry, create a new one for the form
            User currentUser = userDao.getById(currentUserId);
            entry = new BacklogEntry();
            entry.setMediaItem(mediaItem);
            entry.setStatus(BacklogStatus.PLANNED);
            entry.setUserRating(null);
            entry.setNotes("");
            entry.setUser(currentUser);
        }

        request.setAttribute("backlogStatuses", BacklogStatus.values());
        request.setAttribute("backlogEntry", entry);

        request.getRequestDispatcher("/editBacklog.jsp")
                .forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String entryIdParam = request.getParameter("entryId");
        String mediaIdParam = request.getParameter("mediaId");
        String tmdbIdParam = request.getParameter("tmdbId"); // added
        String mediaType = request.getParameter("mediaType");
        String title = request.getParameter("title");
        String overview = request.getParameter("overview");
        String posterUrl = request.getParameter("posterUrl");
        String statusParam = request.getParameter("status");
        String userRatingParam = request.getParameter("userRating");
        String notesParam = request.getParameter("notes");

        MediaItem mediaItem = null;

        if (mediaIdParam != null && !mediaIdParam.isEmpty()) {
            Long mediaId = Long.parseLong(mediaIdParam);
            mediaItem = mediaItemDao.getById(mediaId);
        }

        // If not found by mediaId, check tmdbId and create new
        if (mediaItem == null && tmdbIdParam != null && !tmdbIdParam.isEmpty()) {
            Long tmdbId = Long.parseLong(tmdbIdParam);

            mediaItem = mediaItemDao.getByPropertyEqual("tmdbId", tmdbId)
                    .stream().findFirst().orElse(null);

            if (mediaItem == null) {
                if ("movie".equalsIgnoreCase(mediaType)) {
                    mediaItem = new edu.matc.entjava.entity.Movie();
                } else if ("tv".equalsIgnoreCase(mediaType)) {
                    mediaItem = new edu.matc.entjava.entity.TvShow();
                }
                mediaItem.setTmdbId(tmdbId);
                mediaItem.setTitle(title);
                mediaItem.setOverview(overview);
                mediaItem.setPosterUrl(posterUrl);
                mediaItemDao.insert(mediaItem);
            }
        }

        User user = userDao.getById(1L);
        if (user == null) throw new ServletException("User not found");

        BacklogEntry entry;
        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            entry = backlogEntryDao.getById(Long.parseLong(entryIdParam));
        } else {
            entry = new BacklogEntry();
            entry.setUser(user);
            entry.setMediaItem(mediaItem);
            entry.setDateAdded(new java.util.Date());
        }

        entry.setStatus(statusParam != null ? BacklogStatus.valueOf(statusParam) : BacklogStatus.PLANNED);
        entry.setUserRating(userRatingParam != null && !userRatingParam.isEmpty() ? Integer.parseInt(userRatingParam) : null);
        entry.setNotes(notesParam);

        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            // existing entry → update
            backlogEntryDao.update(entry);
        } else {
            // new entry → insert
            backlogEntryDao.insert(entry);
        }

        response.sendRedirect("backlog");
    }
}