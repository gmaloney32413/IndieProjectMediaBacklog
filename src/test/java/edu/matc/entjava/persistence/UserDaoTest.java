package edu.matc.entjava.persistence;

import edu.matc.entjava.entity.User;
import edu.matc.entjava.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new UserDao();
    }

    @Test
    void getById() {
        User user = dao.getById(1);

        assertNotNull(user);
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("11111111-1111-1111-1111-111111111111", user.getCognitoSub());
        assertEquals("alice", user.getUsername());
        assertEquals("Alice Johnson", user.getName());
    }

    @Test
    void update() {
        User user = dao.getById(1);

        user.setEmail("updated@email.com");
        dao.update(user);

        User updatedUser = dao.getById(1);

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

        Long id = dao.insert(newUser);

        assertNotNull(id);

        User insertedUser = dao.getById(id.intValue());

        assertEquals("newuser@test.com", insertedUser.getEmail());
        assertEquals("44444444-4444-4444-4444-444444444444", insertedUser.getCognitoSub());
        assertEquals("newuser", insertedUser.getUsername());
        assertEquals("New User", insertedUser.getName());
    }

    @Test
    void delete() {
        User user = dao.getById(3);

        dao.delete(user);

        User deletedUser = dao.getById(3);

        assertNull(deletedUser);
    }

    @Test
    void getAll() {
        List<User> users = dao.getAll();

        assertEquals(3, users.size());
    }

    @Test
    void getByCognitoSub() {
        User user = dao.getByCognitoSub("11111111-1111-1111-1111-111111111111");

        assertNotNull(user);
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("alice", user.getUsername());
    }


    @Test
    void getByCognitoSubNotFound() {
        User user = dao.getByCognitoSub("non-existent-sub");

        assertNull(user);
    }

    @Test
    void getOrCreateUser_existingUser() {
        User user = dao.getOrCreateUser(
                "11111111-1111-1111-1111-111111111111",
                "alice@example.com",
                "alice",
                "Alice Johnson"
        );

        assertNotNull(user);
        assertEquals("alice", user.getUsername());

        // Should NOT create a new record
        List<User> users = dao.getAll();
        assertEquals(3, users.size());
    }

    @Test
    void getOrCreateUser_newUser() {
        User user = dao.getOrCreateUser(
                "55555555-5555-5555-5555-555555555555",
                "newcognito@test.com",
                "cognitoUser",
                "Cognito User"
        );

        assertNotNull(user);
        assertEquals("cognitoUser", user.getUsername());

        // Should create a new record
        List<User> users = dao.getAll();
        assertEquals(4, users.size());

        User insertedUser = dao.getByCognitoSub("55555555-5555-5555-5555-555555555555");
        assertEquals("newcognito@test.com", insertedUser.getEmail());
    }

}