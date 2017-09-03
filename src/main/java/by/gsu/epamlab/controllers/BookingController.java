package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.beans.Film;
import by.gsu.epamlab.model.beans.Order;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
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
            Event event =
                    AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().getById(Integer.parseInt(idEvent));
            Film film =
                    AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().getById(event.getFilmId());

            if (new Date().after(event.getEventDate())) {
                req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
            }
            req.setAttribute(Constant.Fields.EVENT, event);
            req.setAttribute(Constant.Fields.EVENT_FILM, film);

            if (user == null) {
                String seatBusy =
                        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().getAllBusyTicketByEventId(event.getId());
                req.setAttribute(Constant.Fields.HALL_ORDER_DISABLE, true);
                req.setAttribute(Constant.Fields.HALL_SEAT_BUSY, seatBusy);
                req.setAttribute(Constant.Fields.HALL_SEAT_SELECTED, "[]");
            } else {
                String seatBusy =
                        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().getAllBusyTicketByUserId(event.getId(), user.getId());

                String seatSelected =
                        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().getAllSelectedTicketByUserId(event.getId(), user.getId());
                req.setAttribute(Constant.Fields.HALL_SEAT_BUSY, seatBusy);
                req.setAttribute(Constant.Fields.HALL_SEAT_SELECTED, seatSelected);
            }
            jumpTo(Constant.Page.HALL_PAGE, req, resp);
        }
        performAjaxReq(req, resp, user);

    }

    private void performAjaxReq(HttpServletRequest req, HttpServletResponse resp, User user) throws IOException, ServletException {
        // Обработчик запроса AJAX
        String json = req.getParameter("ticketArray");
        String idEvent = req.getParameter("idEvent");
        if (json != null) {
            try {
                if(!"[]".equals(json)) {
                    Order order = new Order(1, user.getId(), Integer.parseInt(idEvent), new Date(),
                            json.replace("[", "").replace("]", ""));
                    AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().insert(order);
                    String seatBusy = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().
                            getAllBusyTicketByUserId(Integer.parseInt(idEvent), user.getId());
                    String seatSelected = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().
                            getAllSelectedTicketByUserId(Integer.parseInt(idEvent), user.getId());
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
