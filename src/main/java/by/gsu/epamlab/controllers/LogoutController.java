package by.gsu.epamlab.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(LogoutController.class);

    protected void controllerLogic(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        LOGGER.info("Logout user");
        jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
    }

}
