package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class LegendaryDataTracker {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void updateUserTaskStatus(int userId, int taskId) {

        GenericDao taskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);

        //Retrieve the instance of an user's task and set its completion to 0 or 1 for incomplete or complete
        Map<String, Object> propertyMap = new HashMap<>();

        propertyMap.put("userId", userId);
        propertyMap.put("taskId", taskId);

        UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask)taskDao.findByPropertyEqual(propertyMap);

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
