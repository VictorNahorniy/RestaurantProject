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

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public static final Logger log = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Client client;
        ClientDaoImpl clientDao = new ClientDaoImpl();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            client = clientDao.checkClientLogin(login, password);
            req.getSession().setAttribute("user", client);
            chain.doFilter(request, response);
            resp.sendRedirect("/RestaurantProject/HomeServlet");
        } catch (IllegalArgumentException e) {
            log.error(e);
            resp.sendRedirect("/RestaurantProject/exceptions/user-not-found.jsp");
        }
    }
}
