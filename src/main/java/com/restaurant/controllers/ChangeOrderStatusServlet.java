package com.restaurant.controllers;

import com.restaurant.database.dao.impl.OrderDaoImpl;
import com.restaurant.database.javaBeans.Order;
import com.restaurant.database.javaBeans.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

@WebServlet(name = "ChangeOrderStatusServlet", value = "/change-status")
public class ChangeOrderStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        Order order = new OrderDaoImpl().findEntityById(Integer.parseInt(request.getParameter("id")));
        Iterator<OrderStatus> iterator = Arrays.stream(OrderStatus.values()).iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getString().equals(status)) {
                order.setStatus(iterator.next());
            }
        }
        if (order != null) {
            new OrderDaoImpl().update(order);
            response.getWriter().println(new OrderDaoImpl().findEntityById(order.getId()).getStatus().getString() + " " + order.getStatus());
            response.sendRedirect("profile.jsp");
        } else throw new IllegalArgumentException();

    }
}
