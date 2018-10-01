package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.Legendary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegendaryDaoTest {
    LegendaryDao dao;

    @BeforeEach
    void setUp() {
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
    void getLegendaryById() {
        Legendary legendary = dao.getLegendaryById(7);

        assertEquals("The Binding of Ipos", legendary.getName());
    }

    @Test
    void

}