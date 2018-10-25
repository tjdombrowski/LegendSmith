package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.PrimaryItem;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryItemDaoTest {
    GenericDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(PrimaryItem.class);

    }

    @Test
    void getAllPrimaryItemsSuccess() {
        List<PrimaryItem> primaryItems = dao.getAll();

        assertEquals(4, primaryItems.size());
    }

    @Test
    void insertSuccess() {
        PrimaryItem primaryItem = new PrimaryItem("Cake");

        int id = dao.insert(primaryItem);

        assertNotEquals(0, id);

        PrimaryItem retrievedPrimaryItem = (PrimaryItem)dao.getById(id);

        assertEquals(primaryItem, retrievedPrimaryItem);
    }

    @Test
    void saveOrUpdateSuccess() {
        PrimaryItem primaryItem = (PrimaryItem)dao.getById(1);
        assertNotNull(primaryItem);

        primaryItem.setName("Pancake");

        dao.saveOrUpdate(primaryItem);

        PrimaryItem retrievedPrimaryItem = (PrimaryItem)dao.getById(1);

        assertEquals(primaryItem, retrievedPrimaryItem);
    }

    @Test
    void deleteSuccess() {
        PrimaryItem primaryItem = (PrimaryItem)dao.getById(1);
        assertNotNull(primaryItem);

        dao.delete(primaryItem);

        PrimaryItem retrievedPrimaryItem = (PrimaryItem)dao.getById(1);

        assertNull(retrievedPrimaryItem);
    }


}