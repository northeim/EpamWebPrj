package by.gsu.epamlab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/registrat")
public class UserValidator implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(">> In filter");

        System.out.println(servletRequest.getParameter("login"));

        if (servletRequest.getParameter("login").equals("testName")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            PrintWriter out = servletResponse.getWriter();
            out.println("You have enter a wrong Login");
            out.flush();

            RequestDispatcher rs = servletRequest.getRequestDispatcher
                    ("registration.jsp");
            rs.forward(servletRequest, servletResponse);
        }




    }

    public void destroy() {

    }
}
