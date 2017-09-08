package by.gsu.epamlab.filters;

import by.gsu.epamlab.controllers.Constant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/booking", "/event"})
public class ParamCheckFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ParamCheckFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        String id = request.getParameter("id");
        String json = request.getParameter("ticketArray");
        if ("/booking".equals(((HttpServletRequest)request).getRequestURI())) {
            LOGGER.info(((HttpServletRequest) request).getHeader("Content-Type"));
            if ((id != null && !"".equals(id))) {
                request.setAttribute(Constant.Fields.BOOK_PROC_METHOD, "GET");
                chain.doFilter(request, response);
            } else if (json != null) {
                request.setAttribute(Constant.Fields.BOOK_PROC_METHOD, "AJAX");
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher(Constant.Controller.EVENTS_CONTROLLER).
                        forward(request, response);
            }
        } else if ("/event".equals(((HttpServletRequest)request).getRequestURI())) {
            if ((id == null || "".equals(id))) {
                request.getRequestDispatcher(Constant.Controller.EVENTS_CONTROLLER).
                        forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
