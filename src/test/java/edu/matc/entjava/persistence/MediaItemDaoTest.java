package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.Movie;
import edu.matc.entjava.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaItemDaoTest {
    MediaItemDao mediaItemDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        mediaItemDao = new MediaItemDao();
    }

    @Test
    void getById() {
        MediaItem item = mediaItemDao.getById(1L); // Inception

        assertNotNull(item);
        assertEquals("Inception", item.getTitle());
        assertEquals("A thief steals corporate secrets through dream-sharing technology.", item.getOverview());
    }

    @Test
    void getAll() {
        List<MediaItem> items = mediaItemDao.getAll();

        // According to cleandb.sql: 7 media_items
        assertEquals(7, items.size());
    }

    @Test
    void searchByTitle() {
        List<MediaItem> results = mediaItemDao.searchByTitle("the");

        // Should find "The Matrix", "The Godfather", "The Office (TV)"
        assertTrue(results.stream().anyMatch(item -> item.getTitle().equals("The Matrix")));
        assertTrue(results.stream().anyMatch(item -> item.getTitle().equals("The Godfather")));
        assertTrue(results.stream().anyMatch(item -> item.getTitle().equals("The Office (TV)")));
    }

    @Test
    void insert() {
        Movie newMovie = new Movie();
        newMovie.setTmdbId(999L);
        newMovie.setTitle("Test Movie");
        newMovie.setOverview("A test movie overview");
        newMovie.setReleaseDate(new Date());
        newMovie.setPosterUrl("http://example.com/poster.jpg");
        newMovie.setDirector("Test Director");
        newMovie.setRating("PG-13");
        newMovie.setRuntime(120);

        Long id = mediaItemDao.insert(newMovie);
        assertNotNull(id);

        MediaItem inserted = mediaItemDao.getById(id);
        assertEquals("Test Movie", inserted.getTitle());
        assertEquals("A test movie overview", inserted.getOverview());
    }

    @Test
    void update() {
        MediaItem item = mediaItemDao.getById(1L); // Inception
        item.setTitle("Inception Updated");

        mediaItemDao.update(item);

        MediaItem updated = mediaItemDao.getById(1L);
        assertEquals("Inception Updated", updated.getTitle());
    }

    @Test
    void delete() {
        MediaItem item = mediaItemDao.getById(7L); // The Office (TV)

        mediaItemDao.delete(item);

        MediaItem deleted = mediaItemDao.getById(7L);
        assertNull(deleted);
    }
}