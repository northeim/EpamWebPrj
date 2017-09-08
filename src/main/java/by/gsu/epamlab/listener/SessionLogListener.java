package by.gsu.epamlab.listener;

import by.gsu.epamlab.controllers.Constant;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionLogListener implements HttpSessionListener {

    private static final Logger LOGGER = Logger.getLogger(SessionLogListener.class);
    private Integer userCount = 0;

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        synchronized (userCount) {
            userCount++;
        }
        LOGGER.info("Enter New User On WebSite. Total Users = " + userCount);
        httpSessionEvent.getSession().
                setAttribute(Constant.Fields.TOTAL_USER_ON_SITE, userCount);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        synchronized (userCount) {
            userCount--;
        }
        LOGGER.info("Logout User From WebSite. Total Users = " + userCount);
    }

}
