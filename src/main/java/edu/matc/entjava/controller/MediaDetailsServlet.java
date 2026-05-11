package edu.matc.entjava.controller;

import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.Movie;
import edu.matc.entjava.entity.TvShow;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import edu.matc.entjava.persistence.GenericDao;
import edu.matc.entjava.persistence.MediaItemDao;
import edu.matc.entjava.persistence.TMDBDao;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Media details servlet.
 */
@WebServlet("/mediaDetails")
public class MediaDetailsServlet extends HttpServlet {
    private MediaItemDao mediaItemDao = new MediaItemDao();
    private TMDBDao tmdbDao = new TMDBDao(); // add this

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        String tmdbIdParam = request.getParameter("tmdbId");
        String mediaType = request.getParameter("mediaType");
        String returnPage = request.getParameter("returnPage");

        MediaItem mediaItem = null;
        BacklogEntry backlogEntry = null;   // ✅ MOVE THIS HERE (important)

        final Long tmdbId = (tmdbIdParam != null && !tmdbIdParam.isEmpty())
                ? Long.parseLong(tmdbIdParam)
                : null;

        final User currentUser = user;

        // -----------------------------
        // 1. LOAD MEDIA ITEM
        // -----------------------------
        if (tmdbId != null) {

            mediaItem = mediaItemDao.getByPropertyEqual("tmdbId", tmdbId)
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (mediaItem == null) {

                if ("movie".equalsIgnoreCase(mediaType)) {

                    MovieItem movieItem = tmdbDao.getMovieDetails(tmdbId);

                    if (movieItem != null) {
                        Movie movie = new Movie();
                        movie.setTmdbId(tmdbId);
                        movie.setTitle(movieItem.getTitle());
                        movie.setOverview(movieItem.getOverview());
                        movie.setPosterUrl(movieItem.getPosterPath() != null
                                ? "https://image.tmdb.org/t/p/w500" + movieItem.getPosterPath()
                                : null);

                        movie.setRuntime(movieItem.getRuntime());
                        movie.setDirector(movieItem.getDirector());
                        movie.setRating(movieItem.getRating());

                        mediaItem = movie;
                    }

                } else if ("tv".equalsIgnoreCase(mediaType) || "tv_show".equalsIgnoreCase(mediaType)) {

                    TVItem tvItem = tmdbDao.getTVDetails(tmdbId);

                    if (tvItem != null) {
                        TvShow show = new TvShow();
                        show.setTmdbId(tmdbId);
                        show.setTitle(tvItem.getName());
                        show.setOverview(tvItem.getOverview());
                        show.setPosterUrl(tvItem.getPosterPath() != null
                                ? "https://image.tmdb.org/t/p/w500" + tvItem.getPosterPath()
                                : null);

                        show.setNumberOfSeasons(tvItem.getNumberOfSeasons());
                        show.setTotalEpisodes(tvItem.getTotalEpisodes());
                        show.setOngoing(tvItem.getOngoing());

                        mediaItem = show;
                    }
                }
            }
        }

        // -----------------------------
        // 2. LOAD BACKLOG ENTRY (IMPORTANT FIX)
        // -----------------------------
        if (currentUser != null && tmdbId != null) {

            List<BacklogEntry> matches = new GenericDao<>(BacklogEntry.class)
                    .getAll()
                    .stream()
                    .filter(be ->
                            be.getUser() != null &&
                                    be.getUser().getId().equals(currentUser.getId()) &&
                                    be.getMediaItem() != null &&
                                    be.getMediaItem().getTmdbId().equals(tmdbId)
                    )
                    .collect(Collectors.toList());

            if (!matches.isEmpty()) {
                backlogEntry = matches.get(0);
            }
        }

        // -----------------------------
        // 3. SEND TO JSP
        // -----------------------------
        request.setAttribute("mediaItem", mediaItem);
        request.setAttribute("backlogEntry", backlogEntry);
        request.setAttribute("returnPage", returnPage);

        request.getRequestDispatcher("/mediaDetails.jsp")
                .forward(request, response);
    }
}