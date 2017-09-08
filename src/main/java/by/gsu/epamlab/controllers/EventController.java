package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exeptions.ValidationException;
import by.gsu.epamlab.model.beans.Author;
import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.beans.Film;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/event")
public class EventController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(EventController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            Event event = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                    getEventDao().getById(Integer.parseInt(id));
            Film film = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                    getFilmDao().getById(event.getFilmId());
            Author author = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                    getAuthorDao().getById(film.getAuthorId());
            if (new Date().after(event.getEventDate())) {
                req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
            }
            req.setAttribute(Constant.Fields.EVENT, event);
            req.setAttribute(Constant.Fields.EVENT_AUTHOR, author);
            req.setAttribute(Constant.Fields.EVENT_FILM, film);
            jumpTo(Constant.Page.EVENT_PAGE, req, resp);
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            jumpToError(e.getValue(), Constant.Controller.EVENTS_CONTROLLER,
                        req, resp);
        }
    }

}
