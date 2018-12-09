package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgressTrackerTest {
    ProgressTracker progressTracker;
    GenericDao userPrimaryItemDao;

    /**
     * Sets up the tests by resetting the database and instantiating ProgressTracker.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);
        progressTracker = new ProgressTracker();
    }

    /**
     * Checks whether the progress tracker updates primary item progress with an expected status below 100 correctly.
     */
    @Test
    void updateWithPrimaryItemProgressBelow1Success() {
        progressTracker.updateAllProgress(4);

        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem) userPrimaryItemDao.getById(4);

        double progress = userLegendaryPrimaryItem.getProgress();

        assertEquals(0.25, progress);
    }

    /**
     * Checks whether the progress tracker updates primary item progress with an expected status above 100 correctly.
     */
    @Test
    void updateWithPrimaryItemProgressAbove1Success() {
        progressTracker.updateAllProgress(4);
        progressTracker.updateAllProgress(4);
        progressTracker.updateAllProgress(4);
        progressTracker.updateAllProgress(4);
        progressTracker.updateAllProgress(4);

        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem) userPrimaryItemDao.getById(4);

        double progress = userLegendaryPrimaryItem.getProgress();

        assertEquals(1.0, progress);
    }

    /**
     * Checks whether the UserLegendary progress is correctly calculated.
     */
    @Test
    void updateAllProgressSuccess() {
        progressTracker.updateAllProgress(4);

        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem) userPrimaryItemDao.getById(4);

        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        UserLegendary userLegendary = (UserLegendary) userLegendaryDao.getById(2);

        double legendaryProgress = userLegendary.getProgress();

        assertEquals(0.06, legendaryProgress);

    }

}
