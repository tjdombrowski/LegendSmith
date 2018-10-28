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
    GenericDao primaryItemDao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Legendary.class);
        primaryItemDao =  new GenericDao(PrimaryItem.class);

    }

    /**
     * Tests for successfully retrieving all legendaries.
     */
    @Test
    void getAllSuccess() {
        List<Legendary> legendaries = dao.getAll();

        assertEquals(7, legendaries.size());
    }

    /**
     * Gets legendaries by name success.
     * TODO Change the code so it retrieves by property and change test accordingly
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
        Legendary legendary = new Legendary(7, "The Binding of Ipos", "Focus");

        Legendary retrievedLegendary = (Legendary)dao.getById(7);

        assertEquals(legendary, retrievedLegendary);
    }

    /**
     * Tests whether inserting a legendary is successful.
     */
    @Test
    void insertLegendarySuccess() {
        Legendary newLegendary = new Legendary("Xiuquatl", "Scepter");
        int id = dao.insert(newLegendary);

        Legendary returnedLegendary = (Legendary)dao.getById(id);

        assertEquals(newLegendary, returnedLegendary);
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

        assertEquals(newLegendary, returnedLegendary);
    }

    /**
     * Tests whether deleting a legendary is successful.
     */
    @Test
    void deleteLegendarySuccess() {
        Legendary returnedLegendary = (Legendary)dao.getById(5);
        assertNotNull(returnedLegendary);

        dao.delete(returnedLegendary);

        assertNull(dao.getById(5));
    }

    /**
     * Tests whether retrieving a legendary's (The Shining Blade) primary items is a success.
     */
    @Test
    void getLegendaryPrimaryItemsSuccess() {
        Legendary legendary = (Legendary)dao.getById(2);

        List<LegendaryPrimaryItem> primaryItems = legendary.getPrimaryItems();

        assertEquals(4, primaryItems.size());

        //Assert Primary Item, Save the Queen (id of 1) matches what is retrieved from the Legendary, The Shining Blade
        assertEquals(primaryItemDao.getById(1), primaryItems.get(0).getPrimaryItem());
    }

    /**
     * Tests whether removing a legendary's (The Shining Blade) primary item (Gift of the Blade) is a success.
     *
     */
    @Test
    void removeLegendaryPrimaryItemSuccess() {
        GenericDao legendaryPrimaryItemDao = new GenericDao(LegendaryPrimaryItem.class);

        LegendaryPrimaryItem giftOfTheBlade = (LegendaryPrimaryItem)legendaryPrimaryItemDao.getById(2);
        assertNotNull(giftOfTheBlade);
        Legendary legendary = (Legendary)dao.getById(2);

        List<LegendaryPrimaryItem> primaryItems = legendary.getPrimaryItems();
        assertNotNull(primaryItems);
        primaryItems.remove(1);

        dao.saveOrUpdate(legendary);

        giftOfTheBlade = (LegendaryPrimaryItem)legendaryPrimaryItemDao.getById(2);

        assertNull(giftOfTheBlade);

    }

}