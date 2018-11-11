package edu.matc.legendsmith.persistence;

import edu.matc.legendsmith.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "ApplicationStartup",
        urlPatterns = {"/applicationStartup"},
        loadOnStartup = 1
)
//Load on startup is set to 1, meaning this servlet will be the first servlet to load automatically without the need for user to go to this servlet
public class ApplicationStartup extends HttpServlet {

    public void init() throws ServletException {

        Logger logger = LogManager.getLogger(this.getClass());

        ServletContext context = getServletContext();

        GenericDao legendaryDao = new GenericDao(Legendary.class);

        List<Legendary> legendaries = legendaryDao.getAll();

        context.setAttribute("legendaries", legendaries);
        //Do all the stuff you want to do when the application starts here.
        //Servlet context can be used to store application-wide variable like
        // context.setAttribute("name", "object"); the same like we do it with sessions.

    }

}
