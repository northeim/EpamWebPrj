package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Order;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IOrderDao;
import by.gsu.epamlab.daoimp.database.EventDaoDataBase;
import by.gsu.epamlab.daoimp.database.FilmDaoDataBase;
import by.gsu.epamlab.daoimp.database.OrderDaoDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userInfo")
public class UserInfoController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(Constant.Fields.USER);

        if (user != null) {

            List<Order> userActualyOrder = new OrderDaoDataBase().getOrderByUserId(user.getId(), IOrderDao.Status.NEW);
            List<Order> userArchiveOrder = new OrderDaoDataBase().getOrderByUserId(user.getId(), IOrderDao.Status.OLD);

            session.setAttribute(Constant.Fields.USER_ACTUALY_ORDER, userActualyOrder);
            session.setAttribute(Constant.Fields.USER_ARCHIVE_ORDER, userArchiveOrder);
            req.setAttribute(Constant.Fields.FILM_LIST, new FilmDaoDataBase().getAll());
            req.setAttribute(Constant.Fields.EVENT_LIST, new EventDaoDataBase().getAllWithOld());

            jumpTo(Constant.Page.USER_PAGE, req, resp);
        } else {
            jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
        }
    }
}
