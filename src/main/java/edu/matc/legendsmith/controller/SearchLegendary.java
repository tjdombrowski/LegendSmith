package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.persistence.LegendaryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/searchLegendary"}
)

public class SearchLegendary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LegendaryDao legendaryDao = new LegendaryDao();

        req.setAttribute("legendaries", legendaryDao.getAllLegendaries());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/search.jsp");
        dispatcher.forward(req, resp);
    }
}
