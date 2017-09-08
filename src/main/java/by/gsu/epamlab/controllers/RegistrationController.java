package by.gsu.epamlab.controllers;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import by.gsu.epamlab.model.modelUtils.security.PasswordCrypter;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            User userAdd =
                    new User(1,
                                 req.getParameter("login"),
                                 req.getParameter("firstName"),
                                 req.getParameter("secondName"),
                                 req.getParameter("email"),
                                 (new PasswordCrypter()).
                                         calculateHash(req.getParameter("password")),
                                 new Role(1, "User", 0));
            AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getUserDao().
                    insert(userAdd);
            req.setAttribute(Constant.Fields.USER_TABLE_STATUS,
                             Constant.Message.USER_ADD_SUCCESSFULLY);
            jumpTo(Constant.Page.REGISTRATION_PAGE, req, resp);
        } catch (DataBaseException e) {
            LOGGER.error(e.getMessage());
            jumpToError(e.getValue(), req, resp);
        }
    }

}
