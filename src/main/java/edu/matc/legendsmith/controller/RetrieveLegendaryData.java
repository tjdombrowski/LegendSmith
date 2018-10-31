package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.entity.*;
import edu.matc.legendsmith.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * The type Search legendary.
 */
@WebServlet(
        urlPatterns = {"/legendary"}
)

public class RetrieveLegendaryData extends HttpServlet {

    /**
     * Used for retrieving all the relevant data of a legendary weapon to be displayed on the page.
     *
     * @param req the http servlet request
     * @param resp the http servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao legendaryDao = new GenericDao(Legendary.class);
        GenericDao primaryItemDao = new GenericDao(PrimaryItem.class);
        GenericDao taskDao = new GenericDao(Task.class);

        int legendaryId = 0;
        legendaryId = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("legendaryData", legendaryDao.getById(legendaryId));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/weapon.jsp");
        dispatcher.forward(req, resp);
    }
}
