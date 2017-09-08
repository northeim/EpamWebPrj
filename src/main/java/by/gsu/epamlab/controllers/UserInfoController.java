package by.gsu.epamlab.controllers;

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

@WebServlet(urlPatterns = "/userInfo")
public class UserInfoController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(UserInfoController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(Constant.Fields.USER);
        LOGGER.info(user);
        List<Order> userActuallyOrder = AbstractDaoFactory.
                getDaoFactory(Constant.FACTORY).getOrderDao().
                getOrderByUserId(user.getId(), IOrderDao.Status.NEW);
        List<Order> userArchiveOrder = AbstractDaoFactory.
                getDaoFactory(Constant.FACTORY).getOrderDao().
                getOrderByUserId(user.getId(), IOrderDao.Status.OLD);
        session.setAttribute(Constant.Fields.USER_ACTUALLY_ORDER,
                             userActuallyOrder);
        session.setAttribute(Constant.Fields.USER_ARCHIVE_ORDER,
                             userArchiveOrder);
        req.setAttribute(Constant.Fields.FILM_LIST,
                         AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                                 getFilmDao().getAll());
        req.setAttribute(Constant.Fields.EVENT_LIST,
                         AbstractDaoFactory.getDaoFactory(Constant.FACTORY).
                                 getEventDao().getAllWithOld());
        jumpTo(Constant.Page.USER_PAGE, req, resp);
    }

}
