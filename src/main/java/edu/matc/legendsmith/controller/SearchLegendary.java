package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.entity.Legendary;
import edu.matc.legendsmith.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        GenericDao dao = new GenericDao(Legendary.class);

        String searchTerm = "";
        searchTerm = req.getParameter("name");

        if (searchTerm.isEmpty()) {
            req.setAttribute("legendaries", dao.getAll());
        } else {
            req.setAttribute("legendaries", dao.getByName(searchTerm));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}
