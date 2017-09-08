package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/events")
public class EventsController extends AbstractController{

    private static final Logger LOGGER = Logger.getLogger(EventsController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute(Constant.Fields.EVENT_LIST,
                    AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                            getEventDao().getAll());
            req.setAttribute(Constant.Fields.FILM_LIST,
                    AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                            getFilmDao().getAll());
            jumpTo(Constant.Page.INDEX_PAGE, req, resp);
        } catch (DataBaseException e) {
            LOGGER.error(e.getMessage());
            jumpToError(e.getValue(), req, resp);
        }
    }

}
