package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.User;
import edu.matc.entjava.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    GenericDao<User> userDao;
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userDao = new GenericDao<>(User.class);

    }

    @Test
    void getById() {
        User user = userDao.getById(1L);

        assertNotNull(user);
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("11111111-1111-1111-1111-111111111111", user.getCognitoSub());
        assertEquals("alice", user.getUsername());
        assertEquals("Alice Johnson", user.getName());
    }

    @Test
    void update() {
        User user = userDao.getById(1L);

        user.setEmail("updated@email.com");
        userDao.update(user);

        User updatedUser = userDao.getById(1L);

        assertEquals("updated@email.com", updatedUser.getEmail());
    }

    @Test
    void insert() {
        User newUser = new User(
                null,
                "44444444-4444-4444-4444-444444444444",
                "newuser@test.com",
                "newuser",
                "New User"
        );

        Long id = userDao.insert(newUser);

        assertNotNull(id);

        User insertedUser = userDao.getById(id);

        assertEquals("newuser@test.com", insertedUser.getEmail());
        assertEquals("44444444-4444-4444-4444-444444444444", insertedUser.getCognitoSub());
        assertEquals("newuser", insertedUser.getUsername());
        assertEquals("New User", insertedUser.getName());
    }

    @Test
    void delete() {
        User user = userDao.getById(3L);

        userDao.delete(user);

        User deletedUser = userDao.getById(3L);

        assertNull(deletedUser);
    }

    @Test
    void getAll() {
        List<User> users = userDao.getAll();

        assertEquals(3, users.size());
    }

    @Test
    void getByCognitoSub() {
        List<User> users = userDao.getByPropertyEqual("cognitoSub", "11111111-1111-1111-1111-111111111111");
        User user = users.isEmpty() ? null : users.get(0);

        assertNotNull(user);
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("alice", user.getUsername());
    }


    @Test
    void getByCognitoSubNotFound() {
        List<User> users = userDao.getByPropertyEqual("cognitoSub", "non-existent-sub");
        User user = users.isEmpty() ? null : users.get(0);
        assertNull(user);
    }

    @Test
    void getOrCreateUser_existingUser() {
        List<User> users = userDao.getByPropertyEqual(
                "cognitoSub", "11111111-1111-1111-1111-111111111111"
        );
        User user = users.isEmpty() ? null : users.get(0);

        if (user == null) {
            user = new User(null,
                    "11111111-1111-1111-1111-111111111111",
                    "alice@example.com",
                    "alice",
                    "Alice Johnson");
            userDao.insert(user);
        }

        assertNotNull(user);
        assertEquals("alice", user.getUsername());

        // Should NOT create a new record
        users = userDao.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void getOrCreateUser_newUser() {
        List<User> users = userDao.getByPropertyEqual(
                "cognitoSub", "11111111-1111-1111-1111-111111111111"
        );
        User user = users.isEmpty() ? null : users.get(0);

        if (user == null) {
            user = new User(null,
                    "11111111-1111-1111-1111-111111111111",
                    "alice@example.com",
                    "alice",
                    "Alice Johnson");
            userDao.insert(user);
        }

        assertNotNull(user);
        assertEquals("alice", user.getUsername());

        // Should not create a new record
        assertEquals(3, userDao.getAll().size());
    }

}