package edu.matc.entjava.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.org.themoviedb.MediaPage;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import edu.matc.entjava.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * TMDB DAO
 */
public class TMDBDao implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Properties properties = loadProperties("/tmdb.properties");
    private final String apiKey = properties.getProperty("tmdb.api.key");
    private final String baseUrl = properties.getProperty("tmdb.base.url");

    private final ObjectMapper mapper;

    /**
     * Instantiates a new Tmdb dao.
     */
    public TMDBDao() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // =========================================================
    // CORE FETCH METHOD
    // =========================================================
    private <T> MediaPage<T> fetchMediaPage(String urlPath, Class<T> clazz) {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl + urlPath)
                .queryParam("api_key", apiKey);

        try {
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            return mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, clazz)
            );

        } catch (JsonProcessingException e) {
            logger.error("JSON parse error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("API error: " + e.getMessage());
        }

        return null;
    }

    // =========================================================
    // PAGINATION FIXED (IMPORTANT PART)
    // =========================================================

    /**
     * Gets page.
     *
     * @param page the page
     * @return the page
     */
    public MediaPage<TVItem> getPage(int page) {
        return fetchMediaPage("/trending/all/week?page=" + page, TVItem.class);
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public MediaPage<TVItem> getPage() {
        return getPage(1);
    }

    /**
     * Gets movie page.
     *
     * @param page the page
     * @return the movie page
     */
    public MediaPage<MovieItem> getMoviePage(int page) {
        return fetchMediaPage("/trending/movie/week?page=" + page, MovieItem.class);
    }

    /**
     * Gets movie page.
     *
     * @return the movie page
     */
    public MediaPage<MovieItem> getMoviePage() {
        return getMoviePage(1);
    }

    /**
     * Gets tv page.
     *
     * @param page the page
     * @return the tv page
     */
    public MediaPage<TVItem> getTVPage(int page) {
        return fetchMediaPage("/trending/tv/week?page=" + page, TVItem.class);
    }

    /**
     * Gets tv page.
     *
     * @return the tv page
     */
    public MediaPage<TVItem> getTVPage() {
        return getTVPage(1);
    }

    // =========================================================
    // SEARCH
    // =========================================================

    /**
     * Search list.
     *
     * @param query the query
     * @param type  the type
     * @return the list
     */
    public List<TVItem> search(String query, String type) {

        Client client = ClientBuilder.newClient();
        WebTarget target;

        switch (type.toLowerCase()) {

            case "movie":
                target = client.target(baseUrl + "/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query);
                break;

            case "tv":
                target = client.target(baseUrl + "/search/tv")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query);
                break;

            default:
                List<TVItem> movies = search(query, "movie");
                List<TVItem> tvShows = search(query, "tv");
                movies.addAll(tvShows);
                return movies;
        }

        try {
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            MediaPage<TVItem> page = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class)
            );

            return page.getResults();

        } catch (Exception e) {
            logger.error("Search error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Search tv list.
     *
     * @param query the query
     * @param page  the page
     * @return the list
     */
    public List<TVItem> searchTv(String query, int page) {
        try {
            String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);

            MediaPage<TVItem> pageResult = fetchMediaPage(
                    "/search/tv?query=" + encoded + "&page=" + page,
                    TVItem.class
            );

            return pageResult != null ? pageResult.getResults() : Collections.emptyList();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Search movies list.
     *
     * @param query the query
     * @param page  the page
     * @return the list
     */
    public List<MovieItem> searchMovies(String query, int page) {
        try {
            String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);

            MediaPage<MovieItem> pageResult = fetchMediaPage(
                    "/search/movie?query=" + encoded + "&page=" + page,
                    MovieItem.class
            );

            return pageResult != null ? pageResult.getResults() : Collections.emptyList();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    // =========================================================
    // DETAILS
    // =========================================================

    private <T> T fetchDetails(String path, Class<T> clazz) {

        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client.target(baseUrl + path)
                    .queryParam("api_key", apiKey)
                    .queryParam("append_to_response",
                            clazz == MovieItem.class ? "credits,release_dates" : "credits");

            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            return mapper.readValue(response, clazz);

        } catch (Exception e) {
            logger.error("Details error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets movie details.
     *
     * @param tmdbId the tmdb id
     * @return the movie details
     */
    public MovieItem getMovieDetails(long tmdbId) {
        return fetchDetails("/movie/" + tmdbId, MovieItem.class);
    }

    /**
     * Gets tv details.
     *
     * @param tmdbId the tmdb id
     * @return the tv details
     */
    public TVItem getTVDetails(long tmdbId) {
        return fetchDetails("/tv/" + tmdbId, TVItem.class);
    }
}