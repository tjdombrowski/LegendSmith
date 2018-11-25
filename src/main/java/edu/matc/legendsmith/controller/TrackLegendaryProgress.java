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
        int userId = Integer.parseInt(req.getParameter("userId"));
        int primaryItemId = Integer.parseInt(req.getParameter("primaryItemId"));
        int legendaryId = Integer.parseInt(req.getParameter("legendaryId"));
        int taskId = Integer.parseInt(req.getParameter("taskId"));

        UserLegendaryDataHandler legPrimaryItemHandler = new UserLegendaryDataHandler(LegendaryPrimaryItem.class);
        LegendaryPrimaryItem legendaryPrimaryItem = (LegendaryPrimaryItem)legPrimaryItemHandler.returnEntityByForeignKeys("legendary", legendaryId, "primaryItem", primaryItemId);

        if (legendaryPrimaryItem != null) {
            int legendaryPrimeItemId = legendaryPrimaryItem.getId();

            UserLegendaryDataHandler userPrimaryItemHandler = new UserLegendaryDataHandler(UserLegendaryPrimaryItem.class);
            UserLegendaryPrimaryItem userPrimaryItem = (UserLegendaryPrimaryItem)userPrimaryItemHandler.returnEntityByForeignKeys("user", userId, "legendaryPrimaryItem", legendaryPrimeItemId);

            LegendaryDataTracker legendaryDataTracker = new LegendaryDataTracker();

            //If there is no user primary item, assume that the user has not yet started this legendary and instantiate everything
            if (userPrimaryItem == null) {
                legendaryDataTracker.instantiateAllUserLegendaryData(userId, legendaryId);
            }

            //Update user task data
            legendaryDataTracker.updateUserTaskStatus(userPrimaryItem.getId(),taskId);

        } else {
            //
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/legendary");
        dispatcher.forward(req, resp);
    }
}
