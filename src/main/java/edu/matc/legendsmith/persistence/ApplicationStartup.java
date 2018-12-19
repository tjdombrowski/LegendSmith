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

/**
 * The type Application startup.
 */
@WebServlet(
        name = "ApplicationStartup",
        urlPatterns = {"/applicationStartup"},
        loadOnStartup = 1
)
//Load on startup is set to 1, meaning this servlet will be the first servlet to load automatically without the need for user to go to this servlet
public class ApplicationStartup extends HttpServlet {

    /**
     * Loads the list of Legendaries for display on the home page.
     *
     * @throws ServletException the ServletException
     */
    public void init() throws ServletException {

        Logger logger = LogManager.getLogger(this.getClass());

        ServletContext context = getServletContext();

        GenericDao legendaryDao = new GenericDao(Legendary.class);

        List<Legendary> legendaries = legendaryDao.getAll();

        logger.info("Legendaries: " + legendaries);

        context.setAttribute("legendaries", legendaries);

    }

}
