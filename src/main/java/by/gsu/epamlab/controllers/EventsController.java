package by.gsu.epamlab.controllers;

import by.gsu.epamlab.daoimp.EventDaoImp;
import by.gsu.epamlab.daoimp.FilmDaoImp;
import by.gsu.epamlab.exeptions.DataBaseExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/events")
public class EventsController extends AbstractController{

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute(Constant.Fields.EVENT_LIST, new EventDaoImp().getAll());
            req.setAttribute(Constant.Fields.FILM_LIST, new FilmDaoImp().getAll());
            jumpTo(Constant.Page.INDEX_PAGE, req, resp);
        } catch (DataBaseExeption e) {
            jumpToError(e.getValue(), req, resp);
        }

    }

}
