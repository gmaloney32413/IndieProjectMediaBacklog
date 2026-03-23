package edu.matc.entjava.controller;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.entity.Movie;
import edu.matc.entjava.entity.TvShow;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.GenericDao;
import edu.matc.entjava.persistence.MediaItemDao;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import edu.matc.entjava.persistence.TMDBDao;

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
    private TMDBDao tmdbDao; // added TMDB DAO

    @Override
    public void init() {
        backlogEntryDao = new BacklogEntryDao();
        mediaItemDao = new MediaItemDao();
        userDao = new GenericDao<>(User.class);
        tmdbDao = new TMDBDao(); // initialize TMDB DAO
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
            mediaItem = entry.getMediaItem();
        } else {
            String idParam = request.getParameter("id");
            String tmdbIdParam = request.getParameter("tmdbId");

            if (idParam != null && !idParam.isEmpty()) {
                Long mediaId = Long.parseLong(idParam);
                mediaItem = mediaItemDao.getById(mediaId);
            } else if (tmdbIdParam != null && !tmdbIdParam.isEmpty()) {
                Long tmdbId = Long.parseLong(tmdbIdParam);
                mediaItem = mediaItemDao.getByPropertyEqual("tmdbId", tmdbId)
                        .stream().findFirst().orElse(null);

                if (mediaItem == null) {
                    String mediaType = request.getParameter("mediaType");
                    if ("movie".equalsIgnoreCase(mediaType)) {
                        mediaItem = new Movie();
                    } else if ("tv".equalsIgnoreCase(mediaType)) {
                        mediaItem = new TvShow();
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
        String tmdbIdParam = request.getParameter("tmdbId");
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

        if (mediaItem == null && tmdbIdParam != null && !tmdbIdParam.isEmpty()) {
            Long tmdbId = Long.parseLong(tmdbIdParam);

            mediaItem = mediaItemDao.getByPropertyEqual("tmdbId", tmdbId)
                    .stream().findFirst().orElse(null);

            if (mediaItem == null) {
                if ("movie".equalsIgnoreCase(mediaType)) {
                    mediaItem = new Movie();
                } else if ("tv".equalsIgnoreCase(mediaType) || "tv_show".equalsIgnoreCase(mediaType)) {
                    mediaItem = new TvShow();
                }

                if (mediaItem == null) {
                    throw new ServletException("Cannot create MediaItem — mediaType missing or invalid");
                }

                mediaItem.setTmdbId(tmdbId);
                mediaItem.setTitle(title);
                mediaItem.setOverview(overview);
                mediaItem.setPosterUrl(posterUrl);

                // **Populate extra fields from TMDB**
                try {
                    if (mediaItem instanceof Movie) {
                        MovieItem movieItem = tmdbDao.getMovieDetails(tmdbId.intValue());
                        ((Movie) mediaItem).setRuntime(movieItem.getRuntime());
                        ((Movie) mediaItem).setRating(movieItem.getRating());
                        ((Movie) mediaItem).setDirector(movieItem.getDirector());
                    } else if (mediaItem instanceof TvShow) {
                        TVItem tvItem = tmdbDao.getTVDetails(tmdbId.intValue());
                        ((TvShow) mediaItem).setNumberOfSeasons(tvItem.getNumberOfSeasons());
                        ((TvShow) mediaItem).setTotalEpisodes(tvItem.getTotalEpisodes());
                        ((TvShow) mediaItem).setOngoing(tvItem.getOngoing());
                    }
                } catch (Exception e) {
                    // log and continue without extra fields
                    e.printStackTrace();
                }

                mediaItemDao.insert(mediaItem);
            }
        }

        User user = userDao.getById(1L);
        if (user == null) throw new ServletException("User not found");

        BacklogEntry entry;
        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            entry = backlogEntryDao.getById(Long.parseLong(entryIdParam));
            if (entry == null) {
                throw new ServletException("BacklogEntry not found for ID: " + entryIdParam);
            }
        } else {
            BacklogEntry existingEntry = backlogEntryDao.getByUserAndMedia(user, mediaItem);

            if (existingEntry != null) {
                response.sendRedirect("backlog");
                return;
            } else {
                entry = new BacklogEntry();
                entry.setUser(user);
                entry.setMediaItem(mediaItem);
                entry.setDateAdded(new java.util.Date());
            }
        }

        entry.setStatus(statusParam != null ? BacklogStatus.valueOf(statusParam) : BacklogStatus.PLANNED);
        entry.setUserRating(userRatingParam != null && !userRatingParam.isEmpty() ? Integer.parseInt(userRatingParam) : null);
        entry.setNotes(notesParam);

        if (entryIdParam != null && !entryIdParam.isEmpty()) {
            backlogEntryDao.update(entry);
        } else {
            backlogEntryDao.insert(entry);
        }

        response.sendRedirect("backlog");
    }
}