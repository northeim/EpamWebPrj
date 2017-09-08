package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ValidationException;
import by.gsu.epamlab.model.beans.Order;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IOrderDao;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                    getUserDao().getUser(login, password);
            List<Order> userActuallyOrder = AbstractDaoFactory.
                    getDaoFactory(Constant.FACTORY).getOrderDao().
                    getOrderByUserId(user.getId(), IOrderDao.Status.NEW);
            List<Order> userArchiveOrder = AbstractDaoFactory.
                    getDaoFactory(Constant.FACTORY).getOrderDao().
                    getOrderByUserId(user.getId(), IOrderDao.Status.OLD);
            HttpSession session = req.getSession();
            session.setAttribute(Constant.Fields.USER, user);
            session.setAttribute(Constant.Fields.USER_ACTUALLY_ORDER,
                                 userActuallyOrder);
            session.setAttribute(Constant.Fields.USER_ARCHIVE_ORDER,
                                 userArchiveOrder);
            jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            jumpToError(e.getValue(), req, resp);
        } catch (DataBaseException e) {
            LOGGER.error(e.getMessage());
            jumpToError(e.getValue(), req, resp);
        }
    }

}
