package edu.matc.entjava.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TMDBDaoTest {

    TMDBDao dao;

    @BeforeEach
    void setUp() {
        dao = new TMDBDao();
    }

    @Test
    void getPageSuccess() {
        TMDBDao dao = new TMDBDao();
        dao.getPage();
        assertEquals("???", dao.getPage().getResults().get(0));

    }

    @Test
    void getMoviePageSuccess() {
        TMDBDao dao = new TMDBDao();
        dao.getMoviePage();
        assertEquals("???", dao.getPage().getResults().get(0));

    }

    @Test
    void getTVPageSuccess() {
        TMDBDao dao = new TMDBDao();
        dao.getTVPage();
        assertEquals("???", dao.getPage().getResults().get(0));

    }

    @Test
    void getPageReturnsResults() {
        var page = dao.getPage();
        assertNotNull(page, "MediaPage should not be null");
        assertNotNull(page.getResults(), "Results list should not be null");
        assertFalse(page.getResults().isEmpty(), "Results list should not be empty");
    }
}