package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Author;
import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Film;
import by.gsu.epamlab.daoimp.AuthorDaoImp;
import by.gsu.epamlab.daoimp.EventDaoImp;
import by.gsu.epamlab.daoimp.FilmDaoImp;
import by.gsu.epamlab.exeptions.ValidationExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/event")
public class EventController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            // todo -- validation
            if (id != null && !"".equals(id)) {
                Event event = new EventDaoImp().getById(Integer.parseInt(id));
                Film film = new FilmDaoImp().getById(event.getFilmId());
                Author author = new AuthorDaoImp().getById(film.getAuthorId());
                if (new Date().getTime() > event.getEventDate().getTime()) {
                    req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
                }
                req.setAttribute(Constant.Fields.EVENT, event);
                req.setAttribute(Constant.Fields.EVENT_AUTHOR, author);
                req.setAttribute(Constant.Fields.EVENT_FILM, film);
                jumpTo(Constant.Page.EVENT_PAGE, req, resp);
            } else {
                jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
            }
        } catch (ValidationExeption e) {
            jumpToError(e.getValue(), Constant.Controller.EVENTS_CONTROLLER,
                    req, resp);
        }

    }

}
