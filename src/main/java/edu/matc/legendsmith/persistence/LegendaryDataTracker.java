package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;

public class LegendaryDataTracker {

    public void updateUserTaskStatus() {
        GenericDao taskDao = new GenericDao(UserLegendaryPrimaryItemTask.class);
        GenericDao primaryItemDao = new GenericDao(UserLegendaryPrimaryItem.class);
        GenericDao legendaryDao = new GenericDao(UserLegendary.class);


    }


}
