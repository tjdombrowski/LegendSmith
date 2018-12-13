package edu.matc.legendsmith.controller;

import edu.matc.legendsmith.persistence.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/signup"}
)
public class SignUp extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> userDataMap = new HashMap<>();
        userDataMap.put("username", req.getParameter("username"));
        userDataMap.put("password1", req.getParameter("password1"));
        userDataMap.put("password2", req.getParameter("password2"));

        DataValidator dataValidator = new DataValidator();

        String errorMsg = dataValidator.validateAll(userDataMap);

        //If the error message is empty, then there were no issues with the validation.
        if (errorMsg.isEmpty()) {
            //Enter data in the db

            //Redirect to index to log in
        } else {
            //Forward to sign up page with error message
            req.setAttribute("errorMsg", errorMsg);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/signup.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
