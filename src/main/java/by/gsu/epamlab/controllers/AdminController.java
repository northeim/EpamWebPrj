package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import by.gsu.epamlab.model.modelUtils.ITableOperation;
import by.gsu.epamlab.model.modelUtils.TableFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/admin")
@MultipartConfig
public class AdminController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = (User)req.getSession().getAttribute(Constant.Fields.USER);
            if (user != null) {
                if (user.getRole().getLevelAccess() == 10) {
                    String action = req.getParameter(Constant.Action.ACTION);
                    String table = req.getParameter(Constant.DBTables.TABLE);
                    if (Constant.Action.ADD.equals(action)) {
                        printEnum(req);
                        ITableOperation obj = TableFactory.getITableObj(table);
                        obj.addRecord(req);
                    } else if (Constant.Action.DELETE.equals(action)) {
                        printEnum(req);
                        ITableOperation obj = TableFactory.getITableObj(table);
                        obj.deleteRecord(req);
                    } else if (Constant.Action.EDIT.equals(action)) {
                        printEnum(req);
                        ITableOperation obj = TableFactory.getITableObj(table);
                        obj.editRecord(req);
                    }
                    req.setAttribute(Constant.Fields.USER_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getUserDao().getAll());
                    req.setAttribute(Constant.Fields.ROLE_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().getAll());
                    req.setAttribute(Constant.Fields.AUTHOR_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getAuthorDao().getAll());
                    req.setAttribute(Constant.Fields.EVENT_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().getAllWithOld());
                    req.setAttribute(Constant.Fields.ORDER_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().getAll());
                    req.setAttribute(Constant.Fields.FILM_LIST,
                            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().getAll());
                    jumpTo(Constant.Page.ADMIN_PAGE, req, resp);
                } else {
                    jumpToError(Constant.Errors.ERROR_NOT_ADMIN, req, resp);
                }
            } else {
                jumpToError(Constant.Errors.ERROR_NOT_ADMIN, req, resp);
            }
        } catch (DataBaseException e) {
            jumpToError(e.getValue(), req, resp);
        }

    }

    /**
     * Show All Parameters And Them Value;
     * @param req
     */
    void printEnum(HttpServletRequest req) {

        Enumeration<String> enumeration = req.getParameterNames();

        System.out.println("=================================");
        while (enumeration.hasMoreElements()) {
            String param = enumeration.nextElement();
            System.out.println(param + " = " + req.getParameter(param));
        }
        System.out.println("=================================");
    }
}
