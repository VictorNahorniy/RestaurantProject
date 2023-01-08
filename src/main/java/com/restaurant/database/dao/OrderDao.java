package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.Order;
import com.restaurant.database.javaBeans.OrderStatus;

import java.util.Date;
import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    List<Order> findOrderByPrice(float price);

    List<Order> findOrderByDate(Date date);

    List<Order> findOrderByClientID(int clientID);

    List<Order> findOrderByAddressID(int address);

    List<Order> findOrderByStatus(OrderStatus status);

    boolean updateStatus(Order order, OrderStatus orderStatus);
}
