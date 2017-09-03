package by.gsu.epamlab.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionLogListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session Created::ID=" + httpSessionEvent
                .getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session Destroyed::ID=" + httpSessionEvent
                .getSession().getId());
    }

}
