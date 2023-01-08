package com.restaurant.controllers;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.Language;
import com.restaurant.database.javaBeans.OrderDish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    public static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession httpSession = request.getSession();
        OrderDish orderDish = new OrderDish();
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Dish dish = dishDao.findEntityById(id);
            orderDish.setDishMap((HashMap<Dish, Integer>) httpSession.getAttribute("orderDishMap"));
            BuyNowServlet.addDishToOrder(request, dish, orderDish);
            httpSession.setAttribute("orderDishMap", orderDish.getDishMap());
            response.sendRedirect("/RestaurantProject/PaginationServlet");
        }
    }
}
