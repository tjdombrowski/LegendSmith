package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegendaryDataTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void updateUserTaskStatus(int userPrimaryItemId, int taskId) {

        GenericDao taskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);

        UserLegendaryDataHandler userLegendaryDataHandler = new UserLegendaryDataHandler(UserLegendaryPrimaryItemTask.class);

        UserLegendaryPrimaryItemTask userTask;

        userTask = (UserLegendaryPrimaryItemTask)userLegendaryDataHandler.returnEntityByForeignKeys("userPrimaryItem", userPrimaryItemId, "task", taskId);


        if (userTask == null) {
            logger.error("Failed to update task data: No userTask was retrieved. userPrimaryItemId: " + userPrimaryItemId);
            //Insert operation

        } else {
            int completion = userTask.getCompletion();

            switch(completion) {
                case 1:
                    userTask.setCompletion(0);
                    break;
                case 0:
                    userTask.setCompletion(1);
                    break;
                default:
                    logger.error("Task completion status out of bounds. Value is: " + completion);
                    break;
            }

            taskDao.saveOrUpdate(userTask);
        }

    }

    //TODO GET THIS WORKING AAAAAAA
    public void instantiateAllUserLegendaryData(int userId, int legendaryId) {
        GenericDao userDao = new GenericDao(User.class);
        GenericDao legendaryDao = new GenericDao(Legendary.class);

        User user = (User) userDao.getById(userId);
        Legendary legendary = (Legendary) legendaryDao.getById(legendaryId);

        //Add UserLegendary data
        instantiateUserLegendary(user, legendary);

        //Add UserLegendaryPrimaryItem data
        instantiateUserLegendaryPrimaryItem(user, legendary.getId());

        //Add UserLegendaryPrimaryItemTask data


    }

    private void instantiateUserLegendary(User user, Legendary legendary) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        UserLegendary userLegendary = new UserLegendary(user, legendary, 0);
        userLegendaryDao.insert(userLegendary);


    }

    private void instantiateUserLegendaryPrimaryItem(User user, int legendaryId) {
        GenericDao legendaryPrimaryItemDao = new GenericDao(LegendaryPrimaryItem.class);
        List<LegendaryPrimaryItem> primaryItems = legendaryPrimaryItemDao.getByIntegerProperty(legendaryId,"legendaryId");

        for (LegendaryPrimaryItem primaryItem : primaryItems) {
            int primaryItemId = primaryItem.getId();

            GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

            UserLegendaryPrimaryItem userLegendaryPrimaryItem = new UserLegendaryPrimaryItem(user, primaryItem, 0);

            userPrimaryItemDao.insert(userLegendaryPrimaryItem);

        }

    }




}
