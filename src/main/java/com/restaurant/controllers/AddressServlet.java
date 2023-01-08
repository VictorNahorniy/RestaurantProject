package com.restaurant.controllers;

import com.restaurant.database.dao.impl.AddresseesDaoImpl;
import com.restaurant.database.javaBeans.Addresses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddressServlet", value = "/address")
public class AddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Addresses addresses = new Addresses();
        AddresseesDaoImpl addresseesDao = new AddresseesDaoImpl();
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        int buildingNumber = Integer.parseInt(request.getParameter("building-number"));
        addresses.setCity(city);
        addresses.setStreet(street);
        addresses.setBuildingNumber(buildingNumber);
        try {
            if (addresseesDao.getAddressFromDB(addresses) == null) {
                addresseesDao.insert(addresses);

            } else {
                addresses = addresseesDao.getAddressFromDB(addresses);
            }
            request.getSession().setAttribute("address", addresses);
            response.sendRedirect("finishOrder");
        } catch (RuntimeException e) {
            response.getWriter().println(e);
        }
    }
}
