package com.restaurant.controllers;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Language;
import com.restaurant.database.javaBeans.OrderDish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveFromCartServlet", value = "/remove")
public class RemoveFromCartServlet extends HttpServlet {
    static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDish orderDish = (OrderDish) request.getSession().getAttribute("orderDish");
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        orderDish.getDishMap().remove(dishDao.findEntityById(id));
        request.getSession().setAttribute("orderDish", orderDish);
        response.sendRedirect("cart.jsp");
    }
}
