package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.persistence.LegendaryDao;

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
        urlPatterns = {"/searchLegendary"}
)

public class SearchLegendary extends HttpServlet {

    /**
     * Retrieves a list of legendaries, either a complete list or a list depending on the search term.
     *
     * @param req the http servlet request
     * @param resp the http servlet response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LegendaryDao legendaryDao = new LegendaryDao();

        String searchTerm = req.getParameter("name");

        //TODO I'm going to want to make sure this retrieves everything by default
        req.setAttribute("legendaries", legendaryDao.getLegendariesByName(searchTerm));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/search.jsp");
        dispatcher.forward(req, resp);
    }
}
