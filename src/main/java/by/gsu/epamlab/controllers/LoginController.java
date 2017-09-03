package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Order;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IOrderDao;
import by.gsu.epamlab.daoimp.OrderDaoImp;
import by.gsu.epamlab.daoimp.UserDaoImp;
import by.gsu.epamlab.exeptions.DataBaseExeption;
import by.gsu.epamlab.exeptions.ValidationExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = new UserDaoImp().getUser(login, password);
            List<Order> userActualyOrder = new OrderDaoImp().getOrderByUserId(user.getId(), IOrderDao.Status.NEW);
            List<Order> userArchiveOrder = new OrderDaoImp().getOrderByUserId(user.getId(), IOrderDao.Status.OLD);

            HttpSession session = req.getSession(true);
            session.setAttribute(Constant.Fields.USER, user);
            session.setAttribute(Constant.Fields.USER_ACTUALY_ORDER, userActualyOrder);
            session.setAttribute(Constant.Fields.USER_ARCHIVE_ORDER, userArchiveOrder);
            //Todo: debug LoginController;
            System.out.println(user);

            jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
        } catch (ValidationExeption e) {
            System.out.println(e.getValue());
            jumpToError(e.getValue(), req, resp);
        } catch (DataBaseExeption e) {
            System.out.println(e.getValue());
            jumpToError(e.getValue(), req, resp);
        }
    }
}
