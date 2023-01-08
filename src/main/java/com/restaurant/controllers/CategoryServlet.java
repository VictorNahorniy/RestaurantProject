package com.restaurant.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("strategy", "category");
        if (request.getParameter("category") != null)
            request.getSession().setAttribute("category", request.getParameter("category"));
        request.getRequestDispatcher("/PaginationServlet").forward(request, response);
    }

}
