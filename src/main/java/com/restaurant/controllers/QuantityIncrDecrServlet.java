package com.restaurant.controllers;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.Language;
import com.restaurant.database.javaBeans.OrderDish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "QuantityIncrDecrServlet", value = "/quantity")
public class QuantityIncrDecrServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        OrderDish orderDish = new OrderDish();
        Dish dish = dishDao.findEntityById(id);
        orderDish.setDishMap((HashMap<Dish, Integer>) request.getSession().getAttribute("orderDishMap"));
        if (action == null || id <= 0) {
            response.getWriter().println("bad..");
            return;
        }
        if (action.equals("incr")) {
            orderDish.addDish(dish);
        } else {
            orderDish.deleteDish(dish);
        }
        request.getSession().setAttribute("orderDish", orderDish);
        for (Dish dish3 : orderDish.getDishMap().keySet()) {
            response.getWriter().println("<h6>" + dish3.getId() + " " + dish3.getName() + "</h6>");
        }
        response.sendRedirect("cart.jsp");
    }
}
