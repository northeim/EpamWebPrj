package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Film;
import by.gsu.epamlab.beans.Order;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.daoimp.EventDaoImp;
import by.gsu.epamlab.daoimp.FilmDaoImp;
import by.gsu.epamlab.daoimp.OrderDaoImp;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = "/booking")
public class BookingController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User)req.getSession().getAttribute(Constant.Fields.USER);
        String idEvent = req.getParameter("id");

        // Первый вход на страницу
        if (idEvent != null) {
            Event event = new EventDaoImp().getById(Integer.parseInt(idEvent));
            Film film = new FilmDaoImp().getById(event.getFilmId());

            if (new Date().getTime() > event.getEventDate().getTime()) {
                req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
            }
            req.setAttribute(Constant.Fields.EVENT, event);
            req.setAttribute(Constant.Fields.EVENT_FILM, film);

            if (user == null) {
                String seatBusy = new OrderDaoImp().getAllBusyTicketByEventId(event.getId());
                req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
                req.setAttribute(Constant.Fields.HALL_SEAT_BUSY, seatBusy);
                req.setAttribute(Constant.Fields.HALL_SEAT_SELECTED, "[]");
            } else {
                String seatBusy = new OrderDaoImp().getAllBusyTicketByUserId(event.getId(), user.getId());
                String seatSelected = new OrderDaoImp().getAllSelectedTicketByUserId(event.getId(), user.getId());
                req.setAttribute(Constant.Fields.HALL_SEAT_BUSY, seatBusy);
                req.setAttribute(Constant.Fields.HALL_SEAT_SELECTED, seatSelected);
            }
            jumpTo(Constant.Page.HALL_PAGE, req, resp);
        }

        // Обработчик запроса AJAX
        String json = req.getParameter("ticketArray");
        idEvent = req.getParameter("idEvent");
        if (json != null) {
            try {

                if(!"[]".equals(json)) {

                    Order order = new Order(1, user.getId(), Integer.parseInt(idEvent), new Date(), json.replace("[", "").replace("]", ""));
                    new OrderDaoImp().insert(order);

                    String seatBusy = new OrderDaoImp().getAllBusyTicketByUserId(Integer.parseInt(idEvent), user.getId());
                    String seatSelected = new OrderDaoImp().getAllSelectedTicketByUserId(Integer.parseInt(idEvent), user.getId());

                    JSONObject JSON = new JSONObject();
                    JSON = JSON.put(Constant.Fields.HALL_SEAT_BUSY, seatBusy);
                    JSON = JSON.put(Constant.Fields.HALL_SEAT_SELECTED, seatSelected);
                    resp.getWriter().println(JSON.toString());
                }
            } catch (RuntimeException e) {
                jumpToError(Constant.Errors.ERROR_UNKNOWN, req, resp);
            }
        }
    }

}
