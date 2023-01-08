package com.restaurant.filters;

import com.restaurant.database.javaBeans.OrderDish;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ConfirmFilter")
public class ConfirmFilter implements javax.servlet.Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws javax.servlet.ServletException {
        javax.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.FilterChain chain) throws IOException, javax.servlet.ServletException {
        boolean isLogged = Boolean.parseBoolean(request.getParameter("isLogged"));
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        OrderDish orderDish = (OrderDish) req.getSession().getAttribute("orderDish");
        if (!isLogged) {
            resp.sendRedirect("exceptions/confirm-order-exception.jsp");
        } else if (orderDish.getDishMap().size() == 0) {
            resp.sendRedirect("exceptions/empty-cart.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

}
