package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.daoimp.UserDaoImp;
import by.gsu.epamlab.exeptions.DataBaseExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteUser")
public class DeleteUserController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("In deleteUser Controller");

        try {
            User user = (User)req.getSession().getAttribute(Constant.Fields
                    .USER);

            if (user != null) {
                if (user.getRole().getLevelAccess() == 10) {
                    String id = req.getParameter("id");
                    if (id != null) {

                        new UserDaoImp().delete(Integer.parseInt(id));
                        req.setAttribute(Constant.Fields.USER_TABLE_STATUS,
                                Constant.Message.USER_DELETE_SUCCEFULL);
                        jumpTo(Constant.Controller.ADMIN_CONTROLLER, req, resp);

                    } else {
                        jumpToError(Constant.Errors.ERROR_INVALID_PARAMETER, req, resp);
                    }
                } else {
                    jumpToError(Constant.Errors.ERROR_NOT_ADMIN, req, resp);
                }
            } else {
                jumpToError(Constant.Errors.ERROR_NOT_ADMIN, req, resp);
            }
        }
        catch(DataBaseExeption e) {
            jumpToError(e.getValue(), req, resp);
        }
    }
}
