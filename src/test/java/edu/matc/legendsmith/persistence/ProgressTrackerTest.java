package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProgressTrackerTest {
    ProgressTracker progressTracker;

    /**
     * Sets up the tests by resetting the database and instantiating ProgressTracker.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        progressTracker = new ProgressTracker();
    }

    @Test
    void updateWithPrimaryItemProgressBelow100Success() {
        progressTracker.updateAllProgress(4);

        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem) userPrimaryItemDao.getById(4);

        double progress = userLegendaryPrimaryItem.getProgress();

        assertEquals(0.25, progress);
    }

}
