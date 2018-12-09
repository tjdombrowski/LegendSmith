package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProgressTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private int totalTasks = 0;
    private int completedTasks = 0;

    /**
     * Updates the user's progress
     *
     */
    public void updateAllProgress(int userTaskId) {
        //Find the user's primary item with task id
        GenericDao userTaskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)userTaskDao.getById(userTaskId);

        UserLegendaryPrimaryItem userPrimaryItem = userTask.getUserPrimaryItem();

        //Update the user's progress with the primary item
        updatePrimaryItemProgress(userPrimaryItem);

        //Update the total progress of the user on the Legendary
        updateLegendaryProgress(userPrimaryItem.getLegendaryPrimaryItem().getId());
    }

    private void updateLegendaryProgress(int legendaryPrimaryItemId) {
        //Find the progress of all the user's primary items with this legendary
        double totalProgression = getUserPrimaryItemProgression(legendaryPrimaryItemId);


    }

    /**
     * Calculates the sum of all the progress of each UserLegendaryPrimaryItem associated with a given Legendary.
     *
     * @param legendaryPrimaryItemId the legendary primary item id
     * @return totalProgression the total progression of each primary item of the userlegendary
     */
    private double getUserPrimaryItemProgression(int legendaryPrimaryItemId) {
        double totalProgression = 0.0;
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems = userLegendaryDao.getByIntegerProperty(legendaryPrimaryItemId, "legendaryPrimaryItemId");

        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            totalProgression += userLegendaryPrimaryItem.getProgress();
        }

        logger.debug("Value of the totalProgression in getUserPrimaryItemProgression: " + totalProgression);

        return totalProgression;
    }

    /**
     * Updates an user's progress with a primary item, given the user's UserLegendaryPrimaryItem.
     * The progress is updated by retrieving the current progress (which is initiated at 0), and finding the number of
     * tasks associated with the primary item to find the increment percentage for each task step, since each task is
     * set one by one. (TODO Allow user to check multiple steps at once)
     *
     * @param userLegendaryPrimaryItem the UserLegendaryPrimaryItem
     */
    private void updatePrimaryItemProgress(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);

        double currentProgress = userLegendaryPrimaryItem.getProgress();
        int numberOfTasks = getNumberOfTasks(userLegendaryPrimaryItem);
        double progressIncrement = 1.0 / (double)numberOfTasks;
        double updatedProgress = currentProgress + progressIncrement;

        updatedProgress = roundUpdatedValue(updatedProgress);

        userLegendaryPrimaryItem.setProgress(updatedProgress);

        logger.debug("Rounded value of updatedProgress after calculations: " + updatedProgress);

        userPrimaryItemDao.saveOrUpdate(userLegendaryPrimaryItem);
    }

    /**
     * Returns the progress rounded to 2 decimal places or sets the value to 100 if it goes above 100.
     *
     * @param updatedProgress the updated progress of the user
     * @return updatedProgress the updated progress of the user
     */
    private double roundUpdatedValue(double updatedProgress) {
        if (updatedProgress > 1.0) {
            updatedProgress = 1.0;
        } else {
            updatedProgress = Math.round(updatedProgress * 100.0) / 100.0;
        }

        logger.debug("Value of updatedProgress in roundUpdatedValue: " + updatedProgress);

        return updatedProgress;
    }

    /**
     * Gets the number of tasks associated with a primary item based of the UserLegendaryPrimaryItem object.
     *
     * @param userLegendaryPrimaryItem the UserLegendaryPrimaryItem
     * @return numberOfTasks the number of tasks
     */
    private int getNumberOfTasks(UserLegendaryPrimaryItem userLegendaryPrimaryItem) {
        int numberOfTasks = 0;

        numberOfTasks = userLegendaryPrimaryItem.getLegendaryPrimaryItem().getTasks().size();

        logger.debug("Value of numberOfTasks in getNumberOfTasks: " + numberOfTasks);

        return numberOfTasks;
    }
}
