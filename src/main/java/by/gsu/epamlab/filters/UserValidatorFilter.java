package by.gsu.epamlab.filters;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/userInfo"})
public class UserValidatorFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(UserValidatorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        User user = (User) ((HttpServletRequest)servletRequest).getSession().
                getAttribute(Constant.Fields.USER);
        LOGGER.info(user);
        if (user == null) {
            servletRequest.getRequestDispatcher(Constant.Page.LOGIN_PAGE).
                    forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

}
