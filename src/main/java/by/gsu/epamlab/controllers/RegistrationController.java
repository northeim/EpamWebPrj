package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.daoimp.database.UserDaoImp;
import by.gsu.epamlab.exeptions.DataBaseExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User userAdd = new User(1,
                    req.getParameter("login"),
                    req.getParameter("firstName"),
                    req.getParameter("secondName"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    new Role(1, "User", 0));
            new UserDaoImp().insert(userAdd);
            req.setAttribute(Constant.Fields.USER_TABLE_STATUS,
                    Constant.Message.USER_ADD_SUCCEFULL);

            jumpTo(Constant.Page.REGISTRATION_PAGE, req, resp);

        } catch (DataBaseExeption e) {
            jumpToError(e.getValue(), req, resp);
        }

    }
}
