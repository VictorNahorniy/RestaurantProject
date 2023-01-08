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

@WebServlet(name = "BuyNowServlet", value = "/BuyNow")
public class BuyNowServlet extends HttpServlet {
    static void addDishToOrder(HttpServletRequest request, Dish dish, OrderDish orderDish) {
        if (orderDish.getDishMap() == null) {
            orderDish.setDishMap(new HashMap<>());
            orderDish.addDish(dish);
        } else {
            boolean isDishAdded = orderDish.getDishMap().containsValue(dish);
            if (!isDishAdded) {
                orderDish.addDish(dish);
            }
        }
        request.getSession().setAttribute("orderDish", orderDish);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        Dish dish = dishDao.findEntityById(Integer.parseInt(request.getParameter("id")));
        OrderDish orderDish = new OrderDish();
        orderDish.setDishMap((HashMap<Dish, Integer>) request.getSession().getAttribute("orderDishMap"));
        addDishToOrder(request, dish, orderDish);
        request.getSession().setAttribute("orderDishMap", orderDish.getDishMap());
        response.sendRedirect("cart.jsp");
    }
}
