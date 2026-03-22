package edu.matc.entjava.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entjava.org.themoviedb.MediaPage;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * The type Tmdb dao.
 */
public class TMDBDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets page.
     *
     * @return the page
     */
    public MediaPage<TVItem> getPage() {
        Client client = ClientBuilder.newClient();
        //TODO Read in uri from a properties file
        WebTarget target =
                client.target("https://api.themoviedb.org/3/trending/all/week")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
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
                client.target("https://api.themoviedb.org/3/trending/movie/week")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
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
                client.target("https://api.themoviedb.org/3/trending/tv/week")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf");
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
                target = client.target("https://api.themoviedb.org/3/search/movie")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf")
                        .queryParam("query", query);
                break;
            case "tv":
                target = client.target("https://api.themoviedb.org/3/search/tv")
                        .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf")
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

    public List<TVItem> searchTv(String query) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://api.themoviedb.org/3/search/tv")
                .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf")
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

    public List<MovieItem> searchMovies(String query) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://api.themoviedb.org/3/search/movie")
                .queryParam("api_key", "94e4d20dd642f4bf70833b534e35b1bf")
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
}




