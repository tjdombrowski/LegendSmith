package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
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
        //Add UserLegendary data
        instantiateUserLegendary(userId, legendaryId);

        //Add UserLegendaryPrimaryItem data


        //Add UserLegendaryPrimaryItemTask data

    }

    private void instantiateUserLegendary(int userId, int legendaryId) {
        GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);

        UserLegendaryDataHandler dataHandler = new UserLegendaryDataHandler(UserLegendary.class);

        if (dataHandler.checkIfEntryAlreadyExists("user", userId,"legendary", legendaryId)) {
            //Do nothing if an entry already exists
        } else {
            GenericDao userDao = new GenericDao(User.class);
            GenericDao legendaryDao = new GenericDao(Legendary.class);

            User user = (User) userDao.getById(userId);
            Legendary legendary = (Legendary) legendaryDao.getById(legendaryId);

            UserLegendary userLegendary = new UserLegendary(user, legendary, 0);
            userLegendaryDao.insert(userLegendary);
        }

    }

    private void instantiateUserLegendaryPrimaryItem() {
        GenericDao userPrimaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);


    }



}
