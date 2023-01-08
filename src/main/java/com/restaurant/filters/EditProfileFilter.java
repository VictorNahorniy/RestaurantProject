package com.restaurant.filters;

import com.restaurant.controllers.EditProfileServlet;
import com.restaurant.database.dao.impl.ClientDaoImpl;
import com.restaurant.database.javaBeans.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EditProfileFilter")
public class EditProfileFilter implements Filter {
    private static final Logger log = LogManager.getLogger(EditProfileServlet.class);

    static void getClientByRequest(HttpServletRequest request, Client client) {
        Client client1 = (Client) request.getSession().getAttribute("user");
        if (client1 == null) throw new NullPointerException();
        client.setId(client1.getId());
        client.setFirstName(request.getParameter("name"));
        client.setLastName(request.getParameter("last_name"));
        client.setNumber(request.getParameter("number"));
        client.setLogin(request.getParameter("login"));
        client.setPassword(request.getParameter("password"));
        log.info("Profile updated");
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Client client = (Client) ((HttpServletRequest)request).getSession().getAttribute("user");
        ClientDaoImpl clientDao = new ClientDaoImpl();
        getClientByRequest(((HttpServletRequest)request), client);
        try {
            clientDao.update(client);
            ((HttpServletResponse)response).sendRedirect("profile.jsp");
            chain.doFilter(request, response);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
            ((HttpServletResponse)response).sendRedirect("exceptions/edit-profile-error.jsp");
        }
    }
}
