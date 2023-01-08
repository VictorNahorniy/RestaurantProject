package com.restaurant.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortingServlet", value = "/sort")
public class SortingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("strategy", "sort");
        if (request.getParameter("sortType") != null)
            request.getSession().setAttribute("sortType", request.getParameter("sortType"));
        request.getRequestDispatcher("/PaginationServlet").forward(request, response);
    }

}
