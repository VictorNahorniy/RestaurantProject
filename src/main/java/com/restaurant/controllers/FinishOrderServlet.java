package com.restaurant.controllers;

import com.restaurant.database.dao.impl.OrderDaoImpl;
import com.restaurant.database.dao.impl.OrderDishDaoImpl;
import com.restaurant.database.javaBeans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "FinishOrderServlet", value = "/finishOrder")
public class FinishOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        OrderDaoImpl orderDao = new OrderDaoImpl();
        HttpSession session = request.getSession();
        Addresses addresses = (Addresses) session.getAttribute("address");
        Client client = (Client) session.getAttribute("user");
        OrderDish orderDish = (OrderDish) session.getAttribute("orderDish");

        setOrder(order, orderDish, client, addresses);
        orderDish.setOrder(order);
        orderDao.insert(order);
        new OrderDishDaoImpl().insertOrderDish(orderDish);
        session.setAttribute("order", order);
        response.sendRedirect("profile.jsp");
    }

    private void setOrder(Order order, OrderDish orderDish, Client client, Addresses addresses) {
        order.setPrice(orderDish.getTotalPrice());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setClientID(client.getId());
        order.setStatus(OrderStatus.NEW);
        order.setAddressesID(addresses.getId());
    }
}
