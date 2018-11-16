package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;

public class LegendaryDataTracker {

    public void updateUserTaskStatus(int userId, int taskId) {
        GenericDao taskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        GenericDao primaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);
        GenericDao legendaryDao = new GenericDao(UserLegendary.class);

        //Retrieve the instance of an user's task and set its completion to 0 or 1 for incomplete or complete
        taskDao.

    }


}
