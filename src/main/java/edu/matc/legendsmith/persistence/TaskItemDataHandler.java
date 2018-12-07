package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * The type Task item data handler.
 */
public class TaskItemDataHandler {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Used to calculate the cost of a Task, represented by an ItemPrice object.
     * Returns as a null if there is no price associated with the Task.
     *
     * @param task the task
     */
    private ItemPrice generateTaskCost(Task task) {
        ItemPrice taskCost = null;

        List<TaskItem> taskItems = task.getTaskItems();
        // Retrieve each item and item quantity for this task
        //Do nothing if there are no items to be priced with this Task
        if (taskItems.size() > 0) {
            int totalTaskCost = 0;
            Gw2ApiUser gw2ApiUser = new Gw2ApiUser();

            for (TaskItem taskItem : taskItems) {
                // Call API and retrieve the cost of the individual item
                int sellOrderPrice = gw2ApiUser.getSellOrderPrice(taskItem.getItem().getGw2ItemId());

                // Multiply the cost by the item quantity
                totalTaskCost += sellOrderPrice * taskItem.getQuantity();
            }
            // Multiply the total by the task quantity
            totalTaskCost = totalTaskCost * task.getQuantity();

            // Create the task cost
            taskCost = new ItemPrice();
            taskCost.setDenominationValues(totalTaskCost);
        }

        logger.debug("taskCost result: " + taskCost);

        return taskCost;
    }


}
