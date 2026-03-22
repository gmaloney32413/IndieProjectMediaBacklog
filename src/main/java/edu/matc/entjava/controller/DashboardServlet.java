package edu.matc.entjava.controller;

import edu.matc.entjava.entity.*;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import edu.matc.entjava.persistence.BacklogEntryDao;
import edu.matc.entjava.persistence.GenericDao;
import edu.matc.entjava.persistence.MediaItemDao;
import edu.matc.entjava.persistence.TMDBDao;
import edu.matc.entjava.service.MediaConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final MediaItemDao mediaItemDao = new MediaItemDao();
    private final BacklogEntryDao backlogEntryDao = new BacklogEntryDao();
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final TMDBDao tmdbDao = new TMDBDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long userId = 1L; // hardcoded for now (until Cognito)

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User currentUser = userDao.getById(userId);
        // Get search parameters (if present)
        String searchQuery = request.getParameter("searchQuery");
        String searchType = request.getParameter("searchType");

        logger.debug("Search parameters received - query: " + searchQuery + ", type: " + searchType);
        List<MediaItem> mediaItems = new ArrayList<>();
        MediaConverter converter = new MediaConverter();
        TMDBDao tmdbDao = new TMDBDao();

        if (searchQuery != null && !searchQuery.isBlank()) {
            if (searchType == null) searchType = "any";
            switch (searchType.toLowerCase()) {
                case "movie":
                    // searchMovies returns List<MovieItem>
                    List<MovieItem> movieResults = tmdbDao.searchMovies(searchQuery);
                    for (MovieItem item : movieResults) {
                        Movie movie = converter.convertToMovie(item);
                        mediaItems.add(movie);
                    }
                    break;

                case "tv":
                    // searchTv returns List<TVItem>
                    List<TVItem> tvResults = tmdbDao.searchTv(searchQuery);
                    for (TVItem item : tvResults) {
                        TvShow show = converter.convertToTvShow(item);
                        mediaItems.add(show);
                    }
                    break;

                default:
                    // generic search: combine movies and tv shows
                    List<MovieItem> movies = tmdbDao.searchMovies(searchQuery);
                    List<TVItem> tvShows = tmdbDao.searchTv(searchQuery);

                    movies.forEach(item -> mediaItems.add(converter.convertToMovie(item)));
                    tvShows.forEach(item -> mediaItems.add(converter.convertToTvShow(item)));

                    logger.debug("Media items size: " + mediaItems.size());
                    mediaItems.forEach(m -> logger.debug("Item: " + m));

                    break;
            }
        } else {
            // NEW: fetch trending movies and TV shows when no search query
            tmdbDao.getMoviePage().getResults()
                    .forEach(item -> mediaItems.add(converter.convertToMovie(item)));
            tmdbDao.getTVPage().getResults()
                    .forEach(item -> mediaItems.add(converter.convertToTvShow(item)));
        }


        // Count backlog entries by status
        Long plannedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.PLANNED);
        Long inProgressCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.IN_PROGRESS);
        Long completedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.COMPLETED);
        Long droppedCount = backlogEntryDao.countByStatusForUser(currentUser, BacklogStatus.DROPPED);

        request.setAttribute("plannedCount", plannedCount);
        request.setAttribute("inProgressCount", inProgressCount);
        request.setAttribute("completedCount", completedCount);
        request.setAttribute("droppedCount", droppedCount);

        request.setAttribute("mediaItems", mediaItems);
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
