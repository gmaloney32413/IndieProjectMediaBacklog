package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.BacklogEntry;
import edu.matc.entjava.entity.BacklogStatus;
import edu.matc.entjava.entity.MediaItem;
import edu.matc.entjava.entity.User;
import edu.matc.entjava.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BacklogEntryDaoTest {
    BacklogEntryDao backlogDao;
    UserDao userDao;
    MediaItemDao mediaItemDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        backlogDao = new BacklogEntryDao();
        userDao = new UserDao();
        mediaItemDao = new MediaItemDao();
    }

    @Test
    void getById() {
        BacklogEntry entry = backlogDao.getById(1L);

        assertNotNull(entry);
        assertEquals(BacklogStatus.COMPLETED, entry.getStatus());
        assertEquals("Mind-bending movie!", entry.getNotes());
        assertEquals(10, entry.getUserRating().intValue());
    }

    @Test
    void getAll() {
        List<BacklogEntry> entries = backlogDao.getAll();

        assertEquals(7, entries.size());
    }

    @Test
    void getByUserId() {
        List<BacklogEntry> aliceEntries = backlogDao.getByUserId(1L);

        assertEquals(3, aliceEntries.size());
    }

    @Test
    void getByUserAndMedia() {
        // Alice (1) + Inception (media id 1)
        BacklogEntry entry = backlogDao.getByUserAndMedia(1L, 1L);

        assertNotNull(entry);
        assertEquals(BacklogStatus.COMPLETED, entry.getStatus());
    }

    @Test
    void countByStatusForUser() {
        Long completedCount = backlogDao.countByStatusForUser(
                BacklogStatus.COMPLETED,
                2L
        );

        // Bob has 2 COMPLETED entries
        assertEquals(2L, completedCount.intValue());
    }

    @Test
    void insert() {
        User user = userDao.getById(1);
        MediaItem media = mediaItemDao.getById(2L);

        BacklogEntry newEntry = new BacklogEntry();
        newEntry.setUser(user);
        newEntry.setMediaItem(media);
        newEntry.setStatus(BacklogStatus.PLANNED);
        newEntry.setNotes("Test insert");
        newEntry.setUserRating(null);

        Long id = backlogDao.insert(newEntry);

        assertNotNull(id);

        BacklogEntry inserted = backlogDao.getById(id);

        assertEquals(BacklogStatus.PLANNED, inserted.getStatus());
        assertEquals("Test insert", inserted.getNotes());
    }

    @Test
    void update() {
        BacklogEntry entry = backlogDao.getById(1L);

        entry.setStatus(BacklogStatus.IN_PROGRESS);
        entry.setUserRating(8);

        backlogDao.update(entry);

        BacklogEntry updated = backlogDao.getById(1L);

        assertEquals(BacklogStatus.IN_PROGRESS, updated.getStatus());
        assertEquals(8, updated.getUserRating().intValue());
    }

    @Test
    void delete() {
        BacklogEntry entry = backlogDao.getById(7L);

        backlogDao.delete(entry);

        BacklogEntry deleted = backlogDao.getById(7L);

        assertNull(deleted);
    }
}