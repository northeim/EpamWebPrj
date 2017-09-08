package by.gsu.epamlab.listener;

import by.gsu.epamlab.controllers.Constant;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(InitContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Context Initialized");
        String imgServerRepo = sce.getServletContext().
                getInitParameter(Constant.Param.IMG_SERVER_REPO);
        sce.getServletContext().setAttribute(Constant.Param.IMG_SERVER_REPO,
                                             imgServerRepo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context Destroyed");
    }

}
