package com.restaurant.controllers;

import com.restaurant.database.javaBeans.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet", value = "/edit")
public class EditProfileServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("user " + ((Client) request.getSession().getAttribute("user")).getLogin() + " successfully updated profile");
    }

}
