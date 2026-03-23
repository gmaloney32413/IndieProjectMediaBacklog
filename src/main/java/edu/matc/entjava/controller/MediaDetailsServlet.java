package edu.matc.entjava.controller;

import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.Movie;
import edu.matc.entjava.entity.TvShow;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import edu.matc.entjava.persistence.MediaItemDao;
import edu.matc.entjava.persistence.TMDBDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mediaDetails")
public class MediaDetailsServlet extends HttpServlet {
    private MediaItemDao mediaItemDao = new MediaItemDao();
    private TMDBDao tmdbDao = new TMDBDao(); // add this

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tmdbIdParam = request.getParameter("tmdbId");
        String mediaType = request.getParameter("mediaType");
        String returnPage = request.getParameter("returnPage");

        MediaItem mediaItem = null;

        if (tmdbIdParam != null && !tmdbIdParam.isEmpty()) {

            Long tmdbId = Long.parseLong(tmdbIdParam);

            // Try DB first
            mediaItem = mediaItemDao.getByPropertyEqual("tmdbId", tmdbId)
                    .stream()
                    .findFirst()
                    .orElse(null);

            // If not found, fetch from TMDB
            if (mediaItem == null) {

                if ("movie".equalsIgnoreCase(mediaType)) {

                    MovieItem movieItem = tmdbDao.getMovieDetails(tmdbId);

                    if (movieItem != null) {
                        Movie movie = new Movie();
                        movie.setTmdbId(tmdbId);
                        movie.setTitle(movieItem.getTitle());
                        movie.setOverview(movieItem.getOverview());
                        movie.setPosterUrl(movieItem.getPosterPath() != null ?
                                "https://image.tmdb.org/t/p/w500" + movieItem.getPosterPath() : null);

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
                        show.setPosterUrl(tvItem.getPosterPath() != null ?
                                "https://image.tmdb.org/t/p/w500" + tvItem.getPosterPath() : null);

                        show.setNumberOfSeasons(tvItem.getNumberOfSeasons());
                        show.setTotalEpisodes(tvItem.getTotalEpisodes());
                        show.setOngoing(tvItem.getOngoing());

                        mediaItem = show;
                    }
                }
            }
        }

        request.setAttribute("mediaItem", mediaItem);
        request.setAttribute("returnPage", returnPage);

        request.getRequestDispatcher("/mediaDetails.jsp")
                .forward(request, response);
    }
}