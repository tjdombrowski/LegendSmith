package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    void getUserDataWithMapSuccess() {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        Map<String, Integer> userLegendaryFkMap = new HashMap<>();
        userLegendaryFkMap.put("legendary", 2);
        userLegendaryFkMap.put("user", 1);

        UserLegendary userLegendary1 = (UserLegendary) userLegendaryDao.findByPropertyEqual(userLegendaryFkMap);
        UserLegendary userLegendary2 = (UserLegendary)userLegendaryDao.getById(1);

        assertEquals(userLegendary1, userLegendary2);
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
