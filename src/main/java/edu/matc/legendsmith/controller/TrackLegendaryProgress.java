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
        urlPatterns = {"/taskMarkOff"}
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
        //User data
        int userTaskId = Integer.parseInt(req.getParameter("userTaskId"));

        LegendaryDataTracker legendaryDataTracker = new LegendaryDataTracker();

        if (userTaskId != 0) {
            //Update user task data
            legendaryDataTracker.updateUserTaskStatus(userTaskId);

        } else {
            //If there is no user task data, assume that the user has not yet started this legendary and instantiate everything

            //Retrieve User
            String username = req.getUserPrincipal().getName();
            GenericDao userDao = new GenericDao(User.class);
            List<User> users = userDao.getByProperty(username, "username");
            User user = users.get(0);

            int legendaryId = Integer.parseInt(req.getParameter("legendaryId"));

            //Instantiate everything using the user id and legendary id
            legendaryDataTracker.instantiateAllUserLegendaryData(user.getId(), legendaryId);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/legendary");
        dispatcher.forward(req, resp);
    }
}
