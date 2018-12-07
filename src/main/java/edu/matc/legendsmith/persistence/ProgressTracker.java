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

    private void calculateNumberOfTasks(List<UserLegendaryPrimaryItem> userLegendaryPrimaryItems) {
        for (UserLegendaryPrimaryItem userLegendaryPrimaryItem : userLegendaryPrimaryItems) {
            for (UserLegendaryPrimaryItemTask userTask : userLegendaryPrimaryItem.getUserTasks()) {
                totalTasks = totalTasks + 1;
                if (userTask.getCompletion() == 1) {
                    completedTasks = completedTasks + 1;
                }
            }
        }
    }
}
