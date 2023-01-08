package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.Order;
import com.restaurant.database.javaBeans.OrderDish;

public interface OrderDishDao {

    OrderDish findAllDishInOrder(Order order);

    boolean insertOrderDish(OrderDish orderDish);
}
