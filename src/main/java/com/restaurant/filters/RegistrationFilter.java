package com.restaurant.filters;

import com.restaurant.database.dao.impl.ClientDaoImpl;
import com.restaurant.database.javaBeans.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
    public static final Logger log = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Client client = new Client();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        try {
            client.setFirstName(request.getParameter("name"));
            client.setLastName(request.getParameter("last_name"));
            client.setNumber(request.getParameter("number"));
            client.setLogin(request.getParameter("login"));
            client.setPassword(request.getParameter("password"));
            if (clientDao.insert(client)) {
                ((HttpServletRequest) request).getSession().setAttribute("user", client);
                ((HttpServletResponse) response).sendRedirect("/RestaurantProject/HomeServlet");
                chain.doFilter(request, response);
            } else {
                log.info("failed registration");
                ((HttpServletResponse) response).sendRedirect("/RestaurantProject/exceptions/login-is-used.jsp");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }
}
