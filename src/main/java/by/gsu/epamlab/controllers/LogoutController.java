package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends AbstractController {

    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        jumpTo(Constant.Controller.EVENTS_CONTROLLER, req, resp);
    }
}
