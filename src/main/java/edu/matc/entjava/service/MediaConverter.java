package edu.matc.entjava.service;

import edu.matc.entjava.entity.Movie;
import edu.matc.entjava.entity.TvShow;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;

public class MediaConverter {

    public TvShow convertToTvShow(TVItem item) {
        TvShow tvShow = new TvShow();

        tvShow.setTmdbId((long) item.getId());
        tvShow.setTitle(item.getName());
        tvShow.setOverview(item.getOverview());
        tvShow.setPosterUrl(item.getPosterPath() != null ? "https://image.tmdb.org/t/p/w500" + item.getPosterPath() : null);

        if (item.getFirstAirDate() != null && !item.getFirstAirDate().isEmpty()) {
            tvShow.setReleaseDate(java.sql.Date.valueOf(item.getFirstAirDate()));
        }

        tvShow.setOngoing(false); // TMDb search results don't have ongoing status
        tvShow.setNumberOfSeasons(1); // Requires details API
        tvShow.setTotalEpisodes(1); // Requires details API

        return tvShow;
    }

    public Movie convertToMovie(MovieItem item) {
        Movie movie = new Movie();

        movie.setTmdbId((long) item.getId());
        movie.setTitle(item.getTitle());
        movie.setOverview(item.getOverview());
        movie.setPosterUrl(item.getPosterPath() != null ? "https://image.tmdb.org/t/p/w500" + item.getPosterPath() : null);

        if (item.getReleaseDate() != null && !item.getReleaseDate().isEmpty()) {
            movie.setReleaseDate(java.sql.Date.valueOf(item.getReleaseDate()));
        }

        movie.setRuntime(null);   // Requires /movie/{id} API call
        movie.setDirector(null);  // Requires credits API
        movie.setRating(null);    // Requires details API or certification endpoint

        return movie;
    }
}
