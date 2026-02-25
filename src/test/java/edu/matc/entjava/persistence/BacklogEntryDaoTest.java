package edu.matc.entjava.persistence;

import edu.matc.entjava.testUtils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacklogEntryDaoTest {
    BacklogEntryDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new BacklogEntryDao();
    }

    @Test
    void getById() {

    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {

    }

    @Test
    void getByUserId() {
    }

    @Test
    void countByStatusForUser() {
    }
}