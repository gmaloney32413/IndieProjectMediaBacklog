package edu.matc.entjava.persistence;

import edu.matc.entjava.org.themoviedb.MediaPage;
import edu.matc.entjava.org.themoviedb.TVItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TMDBDaoTest {

    TMDBDao dao;

    @BeforeEach
    void setUp() {
        dao = new TMDBDao();
    }

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
        assertEquals("Peaky Blinders: The Immortal Man", firstItem.getTitle());
    }

    @Test
    void getMoviePageSuccess() {
        MediaPage page = dao.getMoviePage();

        assertNotNull(page);
        assertFalse(page.getResults().isEmpty());

        TVItem firstItem = page.getResults().get(0);

        assertNotNull(firstItem);
        assertTrue(firstItem.getId() > 0);

        // Movies should have titles
        assertNotNull(firstItem.getTitle(), "Movie should have a title");
        assertEquals(firstItem.getTitle(), "Peaky Blinders: The Immortal Man");

    }

    @Test
    void getTVPageSuccess() {
        MediaPage page = dao.getTVPage();

        assertNotNull(page);
        assertFalse(page.getResults().isEmpty());

        TVItem firstItem = page.getResults().get(0);

        assertNotNull(firstItem);
        assertTrue(firstItem.getId() > 0);

        // TV shows should have names
        assertNotNull(firstItem.getName(), "TV show should have a name");

        assertEquals(firstItem.getName(), "ONE PIECE");

    }

    @Test
    void getPageReturnsResults() {
        var page = dao.getPage();
        assertNotNull(page, "MediaPage should not be null");
        assertNotNull(page.getResults(), "Results list should not be null");
        assertFalse(page.getResults().isEmpty(), "Results list should not be empty");
    }
}