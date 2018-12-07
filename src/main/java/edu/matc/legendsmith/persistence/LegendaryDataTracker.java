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
     *
     */
    private void updateTotalProgress(User user, Legendary legendary) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        int totalProgress = 0;

        //Get a count of the total number of tasks for this Legendary and completed tasks
        //The count is accessed from UserLegendaryPrimaryItems, where legendaryPrimaryItemId = LegendaryPrimaryItem.id (Make this an list)
        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = findUserPrimaryItemsForThisLegendary(user, legendary);

        //Then loop through each UserLegendaryPrimaryItem, counting each task AND counting each completed task separately



        //Divide and round up

        //Set progress for the primaryItem

        //Calculate the total progress based off the primary items x4 / 4, rounded up

        //Set the total progress

    }

    /**
     * This method finds all UserLegendaryPrimaryItems associated with a given Legendary.
     *
     * @param user
     * @param legendary
     * @return userPrimaryItemsForThisLegendary
     */
    private List<UserLegendaryPrimaryItem> findUserPrimaryItemsForThisLegendary(User user, Legendary legendary) {
        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = user.getUserPrimaryItems();
        List<UserLegendaryPrimaryItem> userPrimaryItemsForThisLegendary = new ArrayList<>();

        for (LegendaryPrimaryItem legendaryPrimaryItem : legendary.getPrimaryItems()) {
            for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
                if (userLegendaryPrimaryItem.getLegendaryPrimaryItem().getId() == legendaryPrimaryItem.getId()) {
                    userPrimaryItemsForThisLegendary.add(userLegendaryPrimaryItem);
                } //else do nothing
            }
        }

        logger.debug("UserLegendaryPrimaryItem List generated in findUserPrimaryItemsForThisLegendary: " + userLegendaryPrimaryItems);

        return userPrimaryItemsForThisLegendary;
    }

    private int calculateTotalNumberOfTasks(Legendary legendary) {
        int totalNumberOfTasks = 0;

        for (LegendaryPrimaryItem primaryItem : legendary.getPrimaryItems()) {
            for (Task task : primaryItem.getTasks()) {
                totalNumberOfTasks = totalNumberOfTasks + 1;
            }
        }

        return totalNumberOfTasks;
    }

    private int calculateCompletedNumberOfTasks(List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems) {
        int completedNumberOfTasks = 0;

        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            for (UserLegendaryPrimaryItemTask userTask : userLegendaryPrimaryItem.getUserTasks()) {
                completedNumberOfTasks = completedNumberOfTasks +1;
            }
        }

        return completedNumberOfTasks;
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
     * @param user
     * @param legendary
     */
    private void instantiateUserLegendary(User user, Legendary legendary) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        UserLegendary userLegendary = new UserLegendary(user, legendary, 0);
        userLegendaryDao.insert(userLegendary);

    }

    /**
     * Instantiates the UserLegendaryPrimaryItem field.
     *
     * @param user
     * @param legendaryId
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
     * @param user
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
