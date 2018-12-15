package edu.matc.legendsmith.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        urlPatterns = {"/unauthorized"}
)
/**
 * This servlet is used to automatically log out an user than logged on unsuccessfully, as is the case with 403 errors.
 */
public class PreventLogIn extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session =  req.getSession();
        session.invalidate();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/unauthorized-error.jsp");
        dispatcher.forward(req, resp);
    }

}
