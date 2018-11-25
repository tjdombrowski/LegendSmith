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

    private void updateProgress() {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        int totalProgress = 0;

        //Retrieve a total list of the tasks for a primary item

        //Retrieve a total of all the tasks the user has completed for that item

        //Divide and round up

        //Set progress for the primaryItem

        //Calculate the total progress based off the primary items x4 / 4, rounded up

        //Set the total progress


    }


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
        instantiateUserLegendaryPrimaryItemTask(user);

    }

    private void instantiateUserLegendary(User user, Legendary legendary) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        UserLegendary userLegendary = new UserLegendary(user, legendary, 0);
        userLegendaryDao.insert(userLegendary);

    }

    private void instantiateUserLegendaryPrimaryItem(User user, int legendaryId) {
        GenericDao legendaryPrimaryItemDao = new GenericDao(LegendaryPrimaryItem.class);
        List<LegendaryPrimaryItem> primaryItems = legendaryPrimaryItemDao.getByIntegerProperty(legendaryId,"legendary");

        for (LegendaryPrimaryItem primaryItem : primaryItems) {
            int primaryItemId = primaryItem.getId();

            GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

            UserLegendaryPrimaryItem userLegendaryPrimaryItem = new UserLegendaryPrimaryItem(user, primaryItem, 0);

            userPrimaryItemDao.insert(userLegendaryPrimaryItem);

        }

    }

    private void instantiateUserLegendaryPrimaryItemTask(User user) {
        List<UserLegendaryPrimaryItem> primaryItems = user.getUserPrimaryItems();
        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);

        //Retrieve each primary item to extract tasks from
        for (UserLegendaryPrimaryItem userPrimaryItem : primaryItems) {
            //retrieve each task item for insertion
            List<Task> tasks = userPrimaryItem.getLegendaryPrimaryItem().getTasks();

            for (Task task : tasks) {
                UserLegendaryPrimaryItemTask userTask = new UserLegendaryPrimaryItemTask(userPrimaryItem, task);

                userTaskDao.insert(userTask);
            }

        }
    }



}
