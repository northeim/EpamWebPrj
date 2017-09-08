package by.gsu.epamlab.controllers;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        controllerLogic(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        controllerLogic(req, resp);
    }

    protected abstract void controllerLogic(HttpServletRequest req,
                                            HttpServletResponse resp)
            throws ServletException, IOException;

    public void jumpTo(String url, HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    public void jumpToError(String errorMessage, HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(Constant.Fields.ERROR_MESSAGE, errorMessage);
        jumpTo(Constant.Page.ERROR_PAGE, req, resp);
    }

    public void jumpToError(String errorMessage, String page,
                            HttpServletRequest req,
                            HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(Constant.Fields.ERROR_MESSAGE, errorMessage);
        req.setAttribute(Constant.Fields.PREV_PAGE, page);
        jumpTo(Constant.Page.ERROR_PAGE, req, resp);
    }

}
