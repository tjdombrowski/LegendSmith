package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.PrimaryItem;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PrimaryItemDaoTest {
    PrimaryItemDao dao;

    /**
     * Sets up the tests by resetting the database and instantiating the necessary dao.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new PrimaryItemDao();

    }

    @Test
    void getAllPrimaryItemsSuccess() {
        List<PrimaryItem> allPrimaryItems = dao.getAllPrimaryItems();

        assertEquals(4, allPrimaryItems.size());
    }

    @Test
    void insertSuccess() {
    }

    @Test
    void saveOrUpdateSuccess() {
    }

    @Test
    void deleteSuccess() {
    }

}