package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/error")
public class ErrorController extends AbstractController {
    @Override
    protected void controllerLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Throwable throwable = (Throwable)req.getAttribute("javax.servlet.error.exception");
        Class<Throwable> throwableType = (Class<Throwable>)req.getAttribute("javax.servlet.error.exception_type");
        String message = (String)req.getAttribute("javax.servlet.error.message");
        String req_uri = (String)req.getAttribute("javax.servlet.error.request_uri");
        Integer statusCode = (int)req.getAttribute("javax.servlet.error.status_code");

        String errorMessage = "<br />Server Error: " + statusCode + "<br />";
        errorMessage += "Server Message: " + message + "<br />";

        if(throwable != null) {
            errorMessage += "Exception : " + throwableType.getName() + "<br />";
            errorMessage += "Exception message : " + throwable.getMessage() + "<br />";
        }

        req.setAttribute(Constant.Fields.PREV_PAGE, req_uri);
        req.setAttribute(Constant.Fields.ERROR_MESSAGE, errorMessage);
        jumpTo(Constant.Page.ERROR_PAGE, req, resp);
    }
}
