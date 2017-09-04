package by.gsu.epamlab.listener;

import by.gsu.epamlab.controllers.Constant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String imgServerRepo = sce.getServletContext().getInitParameter(Constant.Param.IMG_SERVER_REPO);
        sce.getServletContext().setAttribute(Constant.Param.IMG_SERVER_REPO, imgServerRepo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
