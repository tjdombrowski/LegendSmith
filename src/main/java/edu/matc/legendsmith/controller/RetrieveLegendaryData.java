package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.persistence.GenericDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Search legendary.
 */
@WebServlet(
        urlPatterns = {"/legendary"}
)

public class RetrieveLegendaryData extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Used for retrieving all the relevant data of a legendary weapon to be displayed on the page.
     * There are two pieces of data that are sent over: the user's legendary data (UserLegendary), which is used to
     * retrieve legendary info and track progress with the legendary, and the user's primary item data (UserLegendaryPrimaryItem),
     * which is used to retrieve primary item and task data, as well as the user's specific progress with those features.
     *
     * @param req the http servlet request
     * @param resp the http servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //User data
        String username = req.getUserPrincipal().getName();

        int legendaryId = 0;
        legendaryId = Integer.parseInt(req.getParameter("id"));

        if (req.getUserPrincipal() == null || username.isEmpty()) {

        } else {
            //Retrieve User
            GenericDao userDao = new GenericDao(User.class);
            List<User> users = userDao.getByProperty(username, "username");
            User user = users.get(0);
            int userId = user.getId();

            //Retrieve Legendary data - used for displaying
            GenericDao legendaryDao = new GenericDao(Legendary.class);
            Legendary legendary = (Legendary)legendaryDao.getById(legendaryId);

            req.getQueryString("legendaryData", legendary;

            //Retrieve User Legendary data
            GenericDao userLegendaryDao = new GenericDao(UserLegendary.class);
            Map<String, Integer> userLegendaryFkMap = new HashMap<>();
            userLegendaryFkMap.put("legendary", legendaryId);
            userLegendaryFkMap.put("user", userId);

            UserLegendary userLegendary = (UserLegendary) userLegendaryDao.findByPropertyEqual(userLegendaryFkMap);

            req.setAttribute("userLegendaryData", userLegendary);

            //Retrieve User Primary Item data
            List<UserLegendaryPrimaryItem> userPrimaryItems = user.getUserPrimaryItems();

            req.setAttribute("primaryItemData", userPrimaryItems);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/weapon.jsp");
        dispatcher.forward(req, resp);
    }
}
