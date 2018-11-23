package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {
    LegendaryDataTracker legendaryDataTracker;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        legendaryDataTracker = new LegendaryDataTracker();
    }

    @Test
    void getUserLegendaryPrimaryItemTasSuccess() {
        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);
        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem)userPrimaryItemDao.getById(1);
        UserLegendaryPrimaryItemTask userLegendaryPrimaryItemTask = userLegendaryPrimaryItem.getUserTasks().get(0);

        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)userTaskDao.getById(1);

        assertEquals(userTask, userLegendaryPrimaryItemTask);
    }

    @Test
    void updateUserTaskStatusSuccess() {
        legendaryDataTracker.updateUserTaskStatus(2, 1);
        GenericDao dao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userLegendaryPrimaryItemTask = (UserLegendaryPrimaryItemTask)dao.getById(2);

        //Assert when the value is already 0
        assertEquals(1, userLegendaryPrimaryItemTask.getCompletion());

        legendaryDataTracker.updateUserTaskStatus(2, 1);
        userLegendaryPrimaryItemTask = (UserLegendaryPrimaryItemTask)dao.getById(2);

        //Assert when the value is already 1
        assertEquals(0, userLegendaryPrimaryItemTask.getCompletion());
    }
}
