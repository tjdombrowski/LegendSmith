package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Legendary dao test.
 */
class LegendaryDaoTest {
    LegendaryDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new LegendaryDao();

    }

    /**
     * Tests for successfully retrieving all legendaries, when the search term is empty.
     */
    @Test
    void getAllLegendariesWithEmptySearchSuccess() {
        List<Legendary> legendaries = dao.getLegendariesByName("");

        assertEquals(legendaries.size(), 7);
    }

    /**
     * Gets legendaries by name success.
     */
    @Test
    void getLegendariesByNameSuccess() {
        List<Legendary> legendaries = dao.getLegendariesByName("the");

        assertEquals(legendaries.size(), 3);
    }

    /**
     * Tests whether getting a legendary by its id is successful.
     */
    @Test
    void getLegendaryByIdSuccess() {
        Legendary legendary = dao.getLegendaryById(7);

        assertEquals("The Binding of Ipos", legendary.getName());
    }

    /**
     * Tests whether inserting a legendary is successful.
     */
    @Test
    void insertLegendarySuccess() {
        Legendary newLegendary = new Legendary("Xiuquatl", "Scepter");
        int id = dao.insert(newLegendary);

        Legendary returnedLegendary = dao.getLegendaryById(id);

        assertEquals("Xiuquatl", returnedLegendary.getName());
    }

    /**
     * Tests whether updating a legendary is successful.
     */
    @Test
    void updateLegendarySuccess() {
        Legendary newLegendary = new Legendary("Claw of the Khan-Ur", "Dagger");
        newLegendary.setId(4);

        dao.saveOrUpdate(newLegendary);

        Legendary returnedLegendary = dao.getLegendaryById(4);

        assertEquals("Claw of the Khan-Ur", returnedLegendary.getName());
    }

    /**
     * Tests whether deleting a legendary is successful.
     */
    @Test
    void deleteLegendarySuccess() {
        Legendary returnedLegendary = dao.getLegendaryById(5);
        dao.delete(returnedLegendary);

        assertNull(dao.getLegendaryById(5));
    }

}