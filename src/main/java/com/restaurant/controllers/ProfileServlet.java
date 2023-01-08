package com.restaurant.controllers;

import com.restaurant.database.dao.impl.AddresseesDaoImpl;
import com.restaurant.database.dao.impl.ClientDaoImpl;
import com.restaurant.database.dao.impl.OrderDaoImpl;
import com.restaurant.database.javaBeans.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("orderDaoImpl", new OrderDaoImpl());
        request.getSession().setAttribute("clientDaoImpl", new ClientDaoImpl());
        request.getSession().setAttribute("addressesDaoImpl", new AddresseesDaoImpl());
        request.getSession().setAttribute("Completed", OrderStatus.COMPLETED);
        response.sendRedirect("profile.jsp");
    }
}
