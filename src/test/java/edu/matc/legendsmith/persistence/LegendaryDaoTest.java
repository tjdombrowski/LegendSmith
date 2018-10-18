package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Legendary dao test.
 */
class LegendaryDaoTest {
    GenericDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Legendary.class);

    }

    /**
     * Tests for successfully retrieving all legendaries, when the search term is empty.
     */
    @Test
    void getAllSuccess() {
        List<Legendary> legendaries = dao.getAll();

        assertEquals(7, legendaries.size());
    }

    /**
     * Gets legendaries by name success.
     */
    @Test
    void getLegendariesByNameSuccess() {
        List<Legendary> legendaries = dao.getByName("the");

        assertEquals(legendaries.size(), 3);
    }

    /**
     * Tests whether getting a legendary by its id is successful.
     */
    @Test
    void getLegendaryByIdSuccess() {
        Legendary legendary = (Legendary)dao.getById(7);

        assertEquals("The Binding of Ipos", legendary.getName());
    }

    /**
     * Tests whether inserting a legendary is successful.
     */
    @Test
    void insertLegendarySuccess() {
        Legendary newLegendary = new Legendary("Xiuquatl", "Scepter");
        int id = dao.insert(newLegendary);

        Legendary returnedLegendary = (Legendary)dao.getById(id);

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

        Legendary returnedLegendary = (Legendary)dao.getById(4);

        assertEquals("Claw of the Khan-Ur", returnedLegendary.getName());
    }

    /**
     * Tests whether deleting a legendary is successful.
     */
    @Test
    void deleteLegendarySuccess() {
        Legendary returnedLegendary = (Legendary)dao.getById(5);
        dao.delete(returnedLegendary);

        assertNull(dao.getById(5));
    }

}