package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to track changes to the user's legendary data.
 *
 * The type Legendary data tracker.
 */
public class LegendaryDataTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Update user task status, changing it to 0 for incomplete or 1 for complete, depending on the current status.
     *
     * @param userTaskId the user's task's id
     */
    public void updateUserTaskStatus(int userTaskId) {
        GenericDao taskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);

        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)taskDao.getById(userTaskId);

        if (userTask == null) {
            logger.error("Failed to update task data: No userTask was retrieved. Given id: " + userTaskId);
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

    /**
     * Instantiate all user legendary data.
     *
     * @param userId      the user id
     * @param legendaryId the legendary id
     */
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

    /**
     * Instantiates the UserLegendary field.
     *
     * @param user the user
     * @param legendary the legendary
     */
    private void instantiateUserLegendary(User user, Legendary legendary) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        UserLegendary userLegendary = new UserLegendary(user, legendary, 0);
        userLegendaryDao.insert(userLegendary);

    }

    /**
     * Instantiates the UserLegendaryPrimaryItem field.
     *
     * @param user the user
     * @param legendaryId the legendary id
     */
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

    /**
     * Instantiates the UserLegendaryPrimaryItemTask field.
     *
     * @param user the user
     */
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
