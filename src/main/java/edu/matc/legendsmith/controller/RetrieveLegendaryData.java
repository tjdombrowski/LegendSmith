package edu.matc.legendsmith.controller;

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

    }
}
