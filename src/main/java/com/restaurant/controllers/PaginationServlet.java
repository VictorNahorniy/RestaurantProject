package com.restaurant.controllers;

import com.restaurant.controllers.paginationStrategy.CategoryDishStrategy;
import com.restaurant.controllers.paginationStrategy.DefaultDishStrategy;
import com.restaurant.controllers.paginationStrategy.PaginationStrategy;
import com.restaurant.controllers.paginationStrategy.SortingDishStrategy;
import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.DishCategory;
import com.restaurant.database.javaBeans.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "PaginationServlet", value = "/PaginationServlet")
public class PaginationServlet extends HttpServlet {
    public static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 3;
        PaginationStrategy strategy = null;
        List<Dish> dishList = null;
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        HttpSession session = request.getSession();
        int noOfPages = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page", page);
        }
        if (session.getAttribute("page") != null) page = (int) session.getAttribute("page");
        if (session.getAttribute("strategy").equals("category")) {
            strategy = new CategoryDishStrategy();
            DishCategory dishCategory = DishCategory.convertStringToCategory((String) session.getAttribute("category"));
            assert dishCategory != null;
            if (dishCategory.equals(DishCategory.ALL))
                noOfPages = (int) Math.ceil(dishDao.findAll().size() * 1.0 / recordsPerPage);
            else
                noOfPages = (int) Math.ceil((dishDao.findNumOfDishByCategory(Objects.requireNonNull(dishCategory)) * 1.0) / recordsPerPage);
        }
        if (session.getAttribute("strategy").equals("sort")) {
            strategy = new SortingDishStrategy();
            noOfPages = (int) Math.ceil(dishDao.findAll().size() * 1.0 / recordsPerPage);
        }
        if (session.getAttribute("strategy").equals("default")) {
            strategy = new DefaultDishStrategy();
            noOfPages = (int) Math.ceil(dishDao.findAll().size() * 1.0 / recordsPerPage);
        }
        try {
            assert strategy != null;
            dishList = strategy.execute(page, recordsPerPage, request);
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("dishList", dishList);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("home.jsp");
        view.forward(request, response);
    }
}
