package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.hibernate.annotations.GenericGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {
    GenericDao userLegendaryDataDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userLegendaryDataDao = new GenericDao(UserLegendaryPrimaryItem.class);
    }

    @Test
    void updateUserTaskStatusSuccess() {
        userLegendaryDataDao.getByName();
    }
}
