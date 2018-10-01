package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegendaryDaoTest {
    LegendaryDao dao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new LegendaryDao();

    }

    @Test
    void getAllLegendariesSuccess() {
        List<Legendary> legendaries = dao.getAllLegendaries();

        assertEquals(legendaries.size(), 8);
    }

    @Test
    void getLegendariesByNameSuccess() {
        List<Legendary> legendaries = dao.getLegendariesByName("the");

        assertEquals(legendaries.size(), 3);
    }

    @Test
    void getLegendaryByIdSuccess() {
        Legendary legendary = dao.getLegendaryById(7);

        assertEquals("The Binding of Ipos", legendary.getName());
    }

    @Test
    void insertLegendarySuccess() {
        Legendary newLegendary = new Legendary("Xiuquatl", "Scepter", "PoF");
        dao.insert(newLegendary);

        Legendary returnedLegendary = dao.getLegendaryById(8);

        assertEquals("Xiuquatl", returnedLegendary.getName());
    }

}