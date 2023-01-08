package com.restaurant.controllers;

import com.restaurant.database.dao.impl.ClientDaoImpl;
import com.restaurant.database.javaBeans.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeUserStatusServlet", value = "/ChangeUserStatus")
public class ChangeUserStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Client client = new ClientDaoImpl().findEntityById(Integer.parseInt(request.getParameter("id")));
            client.setManager(!Boolean.parseBoolean(request.getParameter("isManager")));
            new ClientDaoImpl().update(client);
            response.sendRedirect("profile.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
