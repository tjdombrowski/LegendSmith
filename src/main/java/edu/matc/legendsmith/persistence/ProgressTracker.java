package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Updates the user's progress for PrimaryItems and Legendaries.
     *
     * @param userTaskId the UserLegendaryPrimaryItem taskId
     */
    public void updateAllProgress(int userTaskId) {
        //Find the user's primary item with task id
        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)userTaskDao.getById(userTaskId);

        UserLegendaryPrimaryItem userPrimaryItem = userTask.getUserPrimaryItem();

        //Update the user's progress with the primary item
        updatePrimaryItemProgress(userPrimaryItem);

        //Update the total progress of the user on the Legendary
        updateLegendaryProgress(userPrimaryItem);
    }

    /**
     * Updates the total progress on a legendary based on the values of each UserLegendaryPrimaryItem.
     *
     * @param userLegendaryPrimaryItem the UserLegendaryPrimaryItem
     */
    public void updateLegendaryProgress(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        //Find the progress of all the user's primary items with this legendary
        int userLegendaryPrimaryItemId = userLegendaryPrimaryItem.getLegendaryPrimaryItem().getId();
        double totalProgression = getUserPrimaryItemProgression(userLegendaryPrimaryItem);

        //There are always 4 primary items, so divide the total progress by 4
        double legendaryProgress = determineLegendaryProgress(totalProgression);

        logger.info("legendaryProgression value (rounded): " + legendaryProgress);

        //Update progress in UserLegendary
        UserLegendary userLegendary = getUserLegendary(userLegendaryPrimaryItem);
        userLegendary.setProgress(legendaryProgress);

        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        userLegendaryDao.saveOrUpdate(userLegendary);
    }

    /**
     * Determines and returns the progress of a legendary, rounded.
     *
     * @param totalPrimaryItemProgress the total primary item progress
     * @return progress the progress
     */
    public double determineLegendaryProgress(double totalPrimaryItemProgress) {
        double numberOfPrimaryItems = 4.0; // this is always 4
        double progress = totalPrimaryItemProgress / numberOfPrimaryItems;

        progress = roundProgress(progress);

        logger.info("Value of progress in determineLegendaryProgress: " + progress);

        return progress;
    }

    /**
     * Finds the UserLegendary object associated with the given UserLegendaryPrimaryItem.
     *
     * @param userPrimaryItem the UserLegendaryPrimaryItem
     * @return userLegendary the UserLegendary
     */
    private UserLegendary getUserLegendary(UserLegendaryPrimaryItem userPrimaryItem) {

        int userId = userPrimaryItem.getUser().getId();
        int legendaryId = userPrimaryItem.getLegendaryPrimaryItem().getLegendary().getId();

        UserLegendaryDataHandler dataHandler = new UserLegendaryDataHandler(UserLegendary.class);

        UserLegendary userLegendary = (UserLegendary) dataHandler.returnEntityByForeignKeys("legendary", legendaryId, "user", userId);

        logger.info("UserLegendary data in getUserLegendary: " + userLegendary);

        return userLegendary;
    }

    /**
     * Calculates the sum of all the progress of each UserLegendaryPrimaryItem associated with a given Legendary.
     *
     * @param userPrimaryItem the user legendary primary item
     * @return totalProgression the total progression of each primary item of the userlegendary
     */
    private double getUserPrimaryItemProgression(UserLegendaryPrimaryItem userPrimaryItem) {
        double totalProgression = 0.0;

        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = getUserPrimaryItems(userPrimaryItem);

        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            totalProgression += userLegendaryPrimaryItem.getProgress();
        }

        logger.info("Value of the totalProgression in getUserPrimaryItemProgression: " + totalProgression);

        return totalProgression;
    }

    /**
     * Retrieves the 4 UserLegendaryPrimaryItem objects associated with the current Legendary and User.
     *
     * @param userPrimaryItem the UserLegendaryPrimaryItem
     * @return userLegendaryPrimaryItems the list of UserLegendaryPrimaryItem objects
     */
    private List<UserLegendaryPrimaryItem> getUserPrimaryItems(UserLegendaryPrimaryItem userPrimaryItem) {
        GenericDao legendaryPrimaryItemDao = new GenericDao(LegendaryPrimaryItem.class);
        int legendaryId = userPrimaryItem.getLegendaryPrimaryItem().getLegendary().getId();

        User user = userPrimaryItem.getUser();

        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = user.getUserPrimaryItems();
        List<UserLegendaryPrimaryItem> userPrimaryItemsForThisLegendary = new ArrayList<>();

        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            int currentLegendaryId = userLegendaryPrimaryItem.getLegendaryPrimaryItem().getLegendary().getId();

            if (currentLegendaryId == legendaryId) {
                userPrimaryItemsForThisLegendary.add(userLegendaryPrimaryItem);
            }
        }

        logger.info("Size of the list of UserLegendaryPrimaryItems: " + userLegendaryPrimaryItems.size());

        return userLegendaryPrimaryItems;
    }

    /**
     * Updates an user's progress with a primary item, given the user's UserLegendaryPrimaryItem.
     *
     * @param userLegendaryPrimaryItem the UserLegendaryPrimaryItem
     */
    private void updatePrimaryItemProgress(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

        double progress = determinePrimaryItemProgress(userLegendaryPrimaryItem);

        userLegendaryPrimaryItem.setProgress(progress);

        logger.info("Rounded value of progress after calculations: " + progress);

        userPrimaryItemDao.saveOrUpdate(userLegendaryPrimaryItem);
    }

    /**
     * Determines the progress of an user's primary item, by retrieving the total number of tasks for the item,
     * and by getting the number of completed tasks in each one. The progress is rounded and returned.
     *
     * @param userLegendaryPrimaryItem the UserLegendaryPrimaryItem
     * @return progress the progress
     */
    private double determinePrimaryItemProgress(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        List<UserLegendaryPrimaryItemTask> userTasks = userLegendaryPrimaryItem.getUserTasks();
        int totalTasks = userTasks.size(); //The amount of tasks here should always be the amount of tasks for that primary item
        int completedTasks = 0;

        for (UserLegendaryPrimaryItemTask userTask : userTasks) {
            if (checkIfTaskComplete(userTask.getCompletion())) {
                completedTasks += 1;
            }
        }

        double progress = calculateProgress(totalTasks, completedTasks);

        return progress;
    }

    /**
     * Checks whether the task has been completed by the user. If the value is 1, then it has been completed, if it's
     * a 0 (by default), then it has not.
     *
     * @param taskCompletion the taskCompletion status
     * @return completion true if completed, false if not
     */
    private boolean checkIfTaskComplete(int taskCompletion) {
        boolean completion = false;

        if (taskCompletion == 1) {
            completion = true;
        } else if (taskCompletion == 0) {
            // do nothing, value is already 0
        } else {
            logger.error("Task completion status out of bounds in checkIfTaskComplete. Value of taskCompletion: " + taskCompletion);
        }

        return completion;
    }

    /**
     * Performs a simple calculation for the progress of an item, where completed tasks is divided by total, and the
     * result is rounded before being returned.
     *
     * @param totalTasks
     * @param completedTasks
     * @return
     */
    private double calculateProgress(int totalTasks, int completedTasks) {
        double progress = (double)completedTasks / (double)totalTasks;

        logger.info("completedTasks" + completedTasks);
        logger.info("totalTasks" + totalTasks);

        progress = roundProgress(progress);

        return progress;
    }

    /**
     * Returns the progress rounded to 2 decimal places or sets the value to 100 if it goes above 100.
     *
     * @param progress the updated progress of the user
     * @return progress the updated progress of the user
     */
    private double roundProgress(double progress) {
        if (progress > 1.0) {
            progress = 1.0;
        } else if (progress < 0) {
            progress = 0.0;
        } else {
            progress = Math.round(progress * 100.0) / 100.0;
        }

        logger.info("Value of updatedProgress in roundUpdatedValue: " + progress);

        return progress;
    }

}
