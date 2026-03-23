package edu.matc.entjava.persistence;

import edu.matc.entjava.org.themoviedb.MediaPage;
import edu.matc.entjava.org.themoviedb.MovieItem;
import edu.matc.entjava.org.themoviedb.TVItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Tmdb dao test.
 */
class TMDBDaoTest {

    /**
     * The Dao.
     */
    TMDBDao dao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new TMDBDao();
    }

    /**
     * Gets page success.
     */
    @Test
    void getPageSuccess() {
        MediaPage page = dao.getPage();

        assertNotNull(page, "MediaPage should not be null");
        assertTrue(page.getPage() > 0, "Page number should be greater than 0");
        assertTrue(page.getTotalResults() > 0, "Total results should be greater than 0");

        List<TVItem> results = page.getResults();
        assertNotNull(results, "Results list should not be null");
        assertFalse(results.isEmpty(), "Results list should not be empty");

        TVItem firstItem = results.get(0);
        assertNotNull(firstItem, "First result should not be null");

        // At least one of these should exist depending on media type
        assertTrue(
                firstItem.getTitle() != null || firstItem.getName() != null,
                "Item should have either a title (movie) or name (tv)"
        );
        //assertEquals("Peaky Blinders: The Immortal Man", firstItem.getTitle());
    }

    /**
     * Gets movie page success.
     */
    @Test
    void getMoviePageSuccess() {
        MediaPage<MovieItem> page = dao.getMoviePage();

        assertNotNull(page);
        assertFalse(page.getResults().isEmpty());

        MovieItem firstItem = page.getResults().get(0);

        assertNotNull(firstItem);
        assertTrue(firstItem.getId() > 0);

        // Movies should have titles
        assertNotNull(firstItem.getTitle(), "Movie should have a title");
        //assertEquals(firstItem.getTitle(), "Peaky Blinders: The Immortal Man");

    }

    /**
     * Gets tv page success.
     */
    @Test
    void getTVPageSuccess() {
        MediaPage<TVItem> page = dao.getTVPage();

        assertNotNull(page);
        assertFalse(page.getResults().isEmpty());

        TVItem firstItem = page.getResults().get(0);

        assertNotNull(firstItem);
        assertTrue(firstItem.getId() > 0);

        // TV shows should have names
        assertNotNull(firstItem.getName(), "TV show should have a name");

        //assertEquals(firstItem.getName(), "ONE PIECE");


    }

    /**
     * Gets page returns results.
     */
    @Test
    void getPageReturnsResults() {
        var page = dao.getPage();
        assertNotNull(page, "MediaPage should not be null");
        assertNotNull(page.getResults(), "Results list should not be null");
        assertFalse(page.getResults().isEmpty(), "Results list should not be empty");
    }

    /**
     * Search tv returns results.
     */
    @Test
    void searchTvReturnsResults() {
        List<TVItem> results = dao.searchTv("one piece");
        assertNotNull(results, "Results should not be null");
        assertFalse(results.isEmpty(), "Results should not be empty");

        TVItem firstItem = results.get(0);
        assertNotNull(firstItem, "First TVItem should not be null");
        assertTrue(firstItem.getId() > 0, "TVItem ID should be positive");
        assertNotNull(firstItem.getName(), "TVItem should have a name");
    }

    /**
     * Search movies returns results.
     */
    @Test
    void searchMoviesReturnsResults() {
        List<MovieItem> results = dao.searchMovies("avengers");
        assertNotNull(results, "Results should not be null");
        assertFalse(results.isEmpty(), "Results should not be empty");

        MovieItem firstItem = results.get(0);
        assertNotNull(firstItem, "First MovieItem should not be null");
        assertTrue(firstItem.getId() > 0, "MovieItem ID should be positive");
        assertNotNull(firstItem.getTitle(), "MovieItem should have a title");
        //assertEquals(firstItem.getTitle(), "ONE PIECE");
    }

    /**
     * Search movie type returns movie items.
     */
    @Test
    void searchMovieTypeReturnsMovieItems() {
        List<TVItem> results = dao.search("avengers", "movie");
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    /**
     * Search tv type returns tv items.
     */
    @Test
    void searchTvTypeReturnsTvItems() {
        List<TVItem> results = dao.search("one piece", "tv");
        assertNotNull(results);
        assertFalse(results.isEmpty());

            TVItem firstItem = results.get(0);
        assertNotNull(firstItem.getName(), "TVItem should have a name");
    }

    /**
     * Search any type combines results.
     */
    @Test
    void searchAnyTypeCombinesResults() {
        List<TVItem> results = dao.search("one piece", "any");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.size() > 1, "Results should combine movie and tv results");
    }


    /**
     * Test getting details for a specific movie by ID.
     */
    @Test
    void getMovieDetailsSuccess() {
        // You can pick a known movie ID from TMDB
        int movieId = 603; // The Matrix as an example

        MovieItem movie = dao.getMovieDetails(movieId);

        assertNotNull(movie, "Movie details should not be null");
        assertEquals(movieId, movie.getId(), "Movie ID should match");
        assertNotNull(movie.getTitle(), "Movie should have a title");
        assertTrue(movie.getOverview() != null && !movie.getOverview().isEmpty(), "Movie should have an overview");
        assertTrue(movie.getReleaseDate() != null, "Movie should have a release date");
        assertTrue(movie.getRuntime() > 0, "Movie runtime should be positive");
    }

    /**
     * Test getting details for a specific TV show by ID.
     */
    @Test
    void getTvDetailsSuccess() {
        // You can pick a known TV ID from TMDB
        int tvId = 1396; // Example: "Breaking Bad"

        TVItem tv = dao.getTVDetails(tvId);

        assertNotNull(tv, "TV show details should not be null");
        assertEquals(tvId, tv.getId(), "TV ID should match");
        assertNotNull(tv.getName(), "TV show should have a name");
        assertTrue(tv.getOverview() != null && !tv.getOverview().isEmpty(), "TV show should have an overview");
        assertTrue(tv.getNumberOfSeasons() > 0, "TV show should have at least one season");
        assertTrue(tv.getTotalEpisodes() > 0, "TV show should have at least one episode");
    }

    /**
     * Test that invalid movie ID returns null or throws an exception.
     */
    @Test
    void getMovieDetailsInvalidId() {
        int invalidMovieId = -1;
        MovieItem movie = dao.getMovieDetails(invalidMovieId);
        assertNull(movie, "Invalid movie ID should return null");
    }

    /**
     * Test that invalid TV ID returns null or throws an exception.
     */
    @Test
    void getTvDetailsInvalidId() {
        int invalidTvId = -1;
        TVItem tv = dao.getTVDetails(invalidTvId);
        assertNull(tv, "Invalid TV ID should return null");
    }
}
