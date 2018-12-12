package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.persistence.*;

import edu.matc.legendsmith.persistence.LegendaryDataTracker;
import edu.matc.legendsmith.persistence.UserLegendaryDataHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Search legendary.
 */
@WebServlet(
        urlPatterns = {"/legendarytask"}
)

public class TrackLegendaryProgress extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Used for retrieving all the relevant data of a legendary weapon to be displayed on the page.
     *
     * @param req the http servlet request
     * @param resp the http servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LegendaryDataTracker legendaryDataTracker = new LegendaryDataTracker();

        int userTaskId = 0;
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        if (req.getParameter("userTaskId").isEmpty()) {
            //If there is no user task data, assume that the user has not yet started this legendary and instantiate everything
            //Retrieve User id
            String username = req.getUserPrincipal().getName();
            GenericDao userDao = new GenericDao(User.class);
            List<User> users = userDao.getByProperty(username, "username");
            User user = users.get(0);

            //Retrieve Legendary id
            int legendaryId = Integer.parseInt(req.getParameter("legendaryId"));

            //Instantiate everything using the user id and legendary id
            legendaryDataTracker.instantiateAllUserLegendaryData(user.getId(), legendaryId);

            //Retrieve and set the taskId
            List<UserLegendaryPrimaryItem> userPrimaryItems = user.getUserPrimaryItems();
            GenericDao taskDao = new GenericDao(Task.class);
            Task task = (Task)taskDao.getById(taskId);
            int userItemId = 0;

            //Finds the UserLegendaryPrimaryItemTask
            for (UserLegendaryPrimaryItem userPrimaryItem : userPrimaryItems) {
                if (userPrimaryItem.getLegendaryPrimaryItem().getId() == task.getLegendaryPrimaryItem().getId()) {
                    userItemId = userPrimaryItem.getId();
                }
            }

            UserLegendaryDataHandler dataHandler = new UserLegendaryDataHandler(UserLegendaryPrimaryItemTask.class);
            UserLegendaryPrimaryItemTask userTask = (UserLegendaryPrimaryItemTask) dataHandler.returnEntityByForeignKeys("task", taskId, "userPrimaryItem", userItemId);

            userTaskId = userTask.getId();

        } else {
            //Set the taskId from the parameter
            userTaskId = Integer.parseInt(req.getParameter("userTaskId"));
        }
        //Update user task data
        legendaryDataTracker.updateUserTaskStatus(userTaskId);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/legendary");
        dispatcher.forward(req, resp);
    }
}
