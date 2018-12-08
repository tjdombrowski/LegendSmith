package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private int totalTasks = 0;
    private int completedTasks = 0;

    /**
     * Updates the user's progress
     *
     */
    private void updatePrimaryItemProgress(int userTaskId) {
        //Find the user's primary item with task id
        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)userTaskDao.getById(userTaskId);

        UserLegendaryPrimaryItem userPrimaryItem = userTask.getUserPrimaryItem();

        //Update progress
        updateProgress(userPrimaryItem);

    }

    private void updateProgress(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

        double currentProgress = userLegendaryPrimaryItem.getProgress();
        int numberOfTasks = getNumberOfTasks(userLegendaryPrimaryItem);
        double progressIncrement = 1.0 / (double)numberOfTasks;
        double updatedProgress = 0;

        userLegendaryPrimaryItem.setProgress(updatedProgress);

        userPrimaryItemDao.saveOrUpdate(userLegendaryPrimaryItem);
    }

    private int getNumberOfTasks(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        int numberOfTasks = 0;

        numberOfTasks = userLegendaryPrimaryItem.getLegendaryPrimaryItem().getTasks().size();

        return numberOfTasks;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method finds all UserLegendaryPrimaryItems associated with a given Legendary.
     *
     * @param user the user
     * @param legendary the legendary
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

    /**
     * Generates the total and completed task values for calculating the progress percentage.
     * TODO Break this method up a bit?
     *
     * @param userLegendaryPrimaryItems the UserLegendaryPrimaryItem list (of items that correspond to a given Legendary)
     */
    private void calculateNumberOfTasks(List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems) {
        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            int tasksForThisPrimaryItem = userLegendaryPrimaryItem.getUserTasks().size();
            int completedTasksForThisPrimaryItem = 0;

            for (UserLegendaryPrimaryItemTask userTask : userLegendaryPrimaryItem.getUserTasks()) {
                totalTasks = totalTasks + 1;
                if (userTask.getCompletion() == 1) {
                    completedTasks = completedTasks + 1;
                    completedTasksForThisPrimaryItem += 1;
                }
            }

            setPrimaryItemProgress(completedTasksForThisPrimaryItem, tasksForThisPrimaryItem, userLegendaryPrimaryItem.getId());
        }
    }

    private void setPrimaryItemProgress(int completedPrimaryItemTasks, int totalPrimaryItemTasks, int userPrimaryItemId) {
        int primaryItemProgress = 0;

        primaryItemProgress = Math.round(completedPrimaryItemTasks / totalPrimaryItemTasks);

    }
}
