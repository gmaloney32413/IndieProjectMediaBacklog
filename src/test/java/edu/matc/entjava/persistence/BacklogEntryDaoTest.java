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

/**
 * The type Backlog entry dao test.
 */
class BacklogEntryDaoTest {
    private GenericDao<BacklogEntry> backlogDao;
    private GenericDao<User> userDao;
    private GenericDao<MediaItem> mediaItemDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        backlogDao = new GenericDao<>(BacklogEntry.class);
        userDao = new GenericDao<>(User.class);
        mediaItemDao = new GenericDao<>(MediaItem.class);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        BacklogEntry entry = backlogDao.getById(1L);

        assertNotNull(entry);
        assertEquals(BacklogStatus.COMPLETED, entry.getStatus());
        assertEquals("Mind-bending movie!", entry.getNotes());
        assertEquals(10, entry.getUserRating().intValue());
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<BacklogEntry> entries = backlogDao.getAll();

        assertEquals(7, entries.size());
    }

    /**
     * Gets by user id.
     */
    @Test
    void getByUserId() {
        List<BacklogEntry> aliceEntries = backlogDao.getByPropertyEqual("user", userDao.getById(1L));

        assertEquals(3, aliceEntries.size());
    }

    /**
     * Gets by user and media.
     */
    @Test
    void getByUserAndMedia() {
        Long userId = 1L;
        Long mediaId = 1L;
        // Alice (1) + Inception (media id 1)
        BacklogEntry entry = backlogDao.getAll().stream()
                .filter(b -> b.getUser().getId().equals(userId) &&
                        b.getMediaItem().getId().equals(mediaId))
                .findFirst()
                .orElse(null);

        assertNotNull(entry);
        assertEquals(BacklogStatus.COMPLETED, entry.getStatus());
    }

    /**
     * Count by status for user.
     */
    @Test
    void countByStatusForUser() {
        Long userId = 2L;
        Long completedCount = backlogDao.getAll().stream()
                .filter(b -> b.getUser().getId().equals(userId) && b.getStatus() == BacklogStatus.COMPLETED)
                .count();
        // Bob has 2 COMPLETED entries
        assertEquals(2L, completedCount.intValue());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = userDao.getById(1L);
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

    /**
     * Update.
     */
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

    /**
     * Delete.
     */
    @Test
    void delete() {
        BacklogEntry entry = backlogDao.getById(7L);

        backlogDao.delete(entry);

        BacklogEntry deleted = backlogDao.getById(7L);

        assertNull(deleted);
    }

    /**
     * Delete backlog entry does not delete user or media.
     */
    @Test
    void deleteBacklogEntryDoesNotDeleteUserOrMedia() {
        BacklogEntry entry = backlogDao.getById(1L);
        User user = entry.getUser();
        MediaItem media = entry.getMediaItem();

        backlogDao.delete(entry);

        // Backlog entry deleted
        assertNull(backlogDao.getById(entry.getId()));

        // User should still exist
        assertNotNull(userDao.getById(user.getId()));

        // Media item should still exist
        assertNotNull(mediaItemDao.getById(media.getId()));
    }


    /**
     * Delete user also deletes backlog entries.
     */
    @Test
    void deleteUserAlsoDeletesBacklogEntries() {
        User user = userDao.getById(1L); // Alice

        // Get all backlog entries for this user before deletion
        List<BacklogEntry> entriesBefore = backlogDao.getByPropertyEqual("user", user);
        assertFalse(entriesBefore.isEmpty());

        // Delete the user
        userDao.delete(user);

        // User should be gone
        assertNull(userDao.getById(user.getId()));

        // All backlog entries for this user should also be gone
        List<BacklogEntry> entriesAfter = backlogDao.getByPropertyEqual("user", user);
        assertTrue(entriesAfter.isEmpty(), "All backlog entries for deleted user should also be deleted");
    }
}