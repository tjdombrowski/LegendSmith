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
        double totalProgression = getUserPrimaryItemProgression(userLegendaryPrimaryItemId);

        //There are always 4 primary items, so divide the total progress by 4
        double legendaryProgression = totalProgression / 4;

        //Round the value
        legendaryProgression = roundUpdatedValue(legendaryProgression);

        logger.debug("legendaryProgression value (rounded): " + legendaryProgression);

        //Update progress in UserLegendary
        UserLegendary userLegendary = getUserLegendary(userLegendaryPrimaryItem);
        userLegendary.setProgress(legendaryProgression);

        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
        userLegendaryDao.saveOrUpdate(userLegendary);
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

        UserLegendary userLegendary = (UserLegendary) dataHandler.returnEntityByForeignKeys("legendaryId", legendaryId, "userId", userId);

        logger.debug("UserLegendary data in getUserLegendary: " + userLegendary);

        return userLegendary;
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
