package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.LegendaryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
        urlPatterns = {"/searchLegendary"}
)

public class SearchLegendary extends HttpServlet {
}
