package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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
        UserLegendaryPrimaryItem userLegendaryPrimaryItem = (UserLegendaryPrimaryItem)userPrimaryItemDao.getById(4);
        UserLegendaryPrimaryItemTask userLegendaryPrimaryItemTask = userLegendaryPrimaryItem.getUserTasks().get(0);

        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)userTaskDao.getById(1);

        assertEquals(userTask, userLegendaryPrimaryItemTask);
    }

    @Test
    void updateUserTaskStatusSuccess() {
        legendaryDataTracker.updateUserTaskStatus(2);
        GenericDao dao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userLegendaryPrimaryItemTask = (UserLegendaryPrimaryItemTask)dao.getById(2);

        //Assert when the value is already 0
        assertEquals(1, userLegendaryPrimaryItemTask.getCompletion());

        legendaryDataTracker.updateUserTaskStatus(2);
        userLegendaryPrimaryItemTask = (UserLegendaryPrimaryItemTask)dao.getById(2);

        //Assert when the value is already 1
        assertEquals(0, userLegendaryPrimaryItemTask.getCompletion());
    }

    /**
     *  Tests whether instantiating user data in UserLegendary, UserLegendaryPrimaryItem, and UserLegendaryPrimaryItemTask is successful.
     */
    @Test
    void instantiateUserSuccess() {
        int userId = 4;
        int legendaryId = 2;

        legendaryDataTracker.instantiateAllUserLegendaryData(userId, legendaryId);

        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        GenericDao legendaryDao = new GenericDao(Legendary.class);
        GenericDao userDao = new GenericDao(User.class);
        GenericDao primaryItemDao = new GenericDao(LegendaryPrimaryItem.class);
        GenericDao taskDao = new GenericDao(Task.class);

        UserLegendaryDataHandler dataHandler = new UserLegendaryDataHandler(UserLegendary.class);
        UserLegendary userLegendary = (UserLegendary)dataHandler.returnEntityByForeignKeys("user", userId, "legendary",legendaryId);

        assertNotNull(userLegendary);
        assertEquals(legendaryDao.getById(legendaryId), userLegendary.getLegendary());
        assertEquals(userDao.getById(userId), userLegendary.getUser());

        dataHandler = new UserLegendaryDataHandler(UserLegendaryPrimaryItem.class);
        UserLegendaryPrimaryItem userPrimaryItem = (UserLegendaryPrimaryItem)dataHandler.returnEntityByForeignKeys("user", userId, "legendaryPrimaryItem", 3);

        assertNotNull(userPrimaryItem);
        assertEquals(primaryItemDao.getById(3), userPrimaryItem.getLegendaryPrimaryItem());
        assertEquals(userDao.getById(userId), userPrimaryItem.getUser());

        dataHandler = new UserLegendaryDataHandler(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask task = (UserLegendaryPrimaryItemTask)dataHandler.returnEntityByForeignKeys("task", 1, "userPrimaryItem", userPrimaryItem.getId());

        assertNotNull(task);
        assertEquals(taskDao.getById(1), task.getTask());
    }
}
