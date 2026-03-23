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
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * The type Tmdb dao.
 */
public class TMDBDao implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties = loadProperties("/tmdb.properties");

    private String apiKey = properties.getProperty("tmdb.api.key");
    private String baseUrl = properties.getProperty("tmdb.base.url");

    /**
     * Gets page.
     *
     * @return the page
     */
    public MediaPage<TVItem> getPage() {
        Client client = ClientBuilder.newClient();
        //TODO Read in uri from a properties file
        WebTarget target =
                client.target(baseUrl + "/trending/all/week")
                        .queryParam("api_key", apiKey);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Mapping objects from the api
        ObjectMapper mapper = new ObjectMapper();
        MediaPage<TVItem> mediaPage = null;

        try {
            mediaPage = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class));
        } catch (JsonProcessingException e) {
            logger.error("Error processing response: " + e.getMessage());
        }
        return mediaPage;
    }

    /**
     * Gets movie page.
     *
     * @return the movie page
     */
    public MediaPage<MovieItem> getMoviePage() {
        Client client = ClientBuilder.newClient();
        //TODO Read in uri from a properties file
        WebTarget target =
                client.target(baseUrl + "/trending/movie/week")
                        .queryParam("api_key", apiKey);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Mapping objects from the api
        ObjectMapper mapper = new ObjectMapper();
        MediaPage<MovieItem> mediaPage = null;

        try {
            mediaPage = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, MovieItem.class));
        } catch (JsonProcessingException e) {
            logger.error("Error processing response: " + e.getMessage());
        }
        return mediaPage;
    }

    /**
     * Gets tv page.
     *
     * @return the tv page
     */
    public MediaPage<TVItem> getTVPage() {
        Client client = ClientBuilder.newClient();
        //TODO Read in uri from a properties file
        WebTarget target =
                client.target(baseUrl + "/trending/tv/week")
                        .queryParam("api_key", apiKey);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Mapping objects from the api
        ObjectMapper mapper = new ObjectMapper();
        MediaPage<TVItem> mediaPage = null;

        try {
            mediaPage = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class)
            );
        } catch (JsonProcessingException e) {
            logger.error("Error processing response: " + e.getMessage());
        }
        return mediaPage;
    }


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
            default: // any
                // call both endpoints and combine results
                List<TVItem> movies = search(query, "movie");
                List<TVItem> tvShows = search(query, "tv");
                movies.addAll(tvShows);
                return movies;
        }

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            MediaPage<TVItem> page = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class)
            );
            return page.getResults();
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Search tv list.
     *
     * @param query the query
     * @return the list
     */
    public List<TVItem> searchTv(String query) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(baseUrl + "/search/tv")
                .queryParam("api_key", apiKey)
                .queryParam("query", query);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            MediaPage<TVItem> page = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, TVItem.class)
            );
            return page.getResults();
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Search movies list.
     *
     * @param query the query
     * @return the list
     */
    public List<MovieItem> searchMovies(String query) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target(baseUrl + "/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("query", query);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            MediaPage<MovieItem> page = mapper.readValue(
                    response,
                    mapper.getTypeFactory().constructParametricType(MediaPage.class, MovieItem.class)
            );
            return page.getResults();
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }


    /**
     * Gets movie details.
     *
     * @param tmdbId the tmdb id
     * @return the movie details
     */
    public MovieItem getMovieDetails(long tmdbId) {
        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client
                    .target(baseUrl + "/movie/" + tmdbId)
                    .queryParam("api_key", apiKey)
                    .queryParam("append_to_response", "credits,release_dates");

            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(response, MovieItem.class);

        } catch (Exception e) {
            logger.error("Error fetching movie details: " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets tv details.
     *
     * @param tmdbId the tmdb id
     * @return the tv details
     */
    public TVItem getTVDetails(long tmdbId) {
        Client client = ClientBuilder.newClient();

        try {
            WebTarget target = client
                    .target(baseUrl + "/tv/" + tmdbId)
                    .queryParam("api_key", apiKey)
                    .queryParam("append_to_response", "credits");

            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(response, TVItem.class);

        } catch (Exception e) {
            logger.error("Error fetching TV details: " + e.getMessage());
            return null;
        }
    }
}




