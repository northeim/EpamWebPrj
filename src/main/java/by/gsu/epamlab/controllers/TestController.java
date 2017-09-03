package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Pasha_1 on 05.08.2017.
 */
@WebServlet(urlPatterns = "/test")
public class TestController extends AbstractController {
    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printEnum(req);
        jumpTo(Constant.Controller.ADMIN_CONTROLLER, req, resp);
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
