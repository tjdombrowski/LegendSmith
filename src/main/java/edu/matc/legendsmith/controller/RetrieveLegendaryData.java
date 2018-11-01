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
import java.util.List;

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
     *
     * @param req the http servlet request
     * @param resp the http servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao legendaryDao = new GenericDao(Legendary.class);

        int legendaryId = 0;
        legendaryId = Integer.parseInt(req.getParameter("id"));

        Legendary legendary = (Legendary) legendaryDao.getById(legendaryId);
        List<LegendaryPrimaryItem> primaryItems = legendary.getPrimaryItems();

        int count = 0;

        for (LegendaryPrimaryItem legendaryPrimaryItem : primaryItems) {
            count++;

            String primaryItemName = "primaryItem" + count;
            req.setAttribute(primaryItemName, legendaryPrimaryItem.getPrimaryItem());

            logger.debug("The name of the primaryItem object to be accessed in weapon.jsp: " + primaryItemName);
            logger.debug("In controller RetrieveLegendaryData, the value of legendaryPrimaryItem.getPrimaryItem.getName(): "
                    + legendaryPrimaryItem.getPrimaryItem().getName());
        }

        req.setAttribute("legendaryData", legendary);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/weapon.jsp");
        dispatcher.forward(req, resp);
    }
}
