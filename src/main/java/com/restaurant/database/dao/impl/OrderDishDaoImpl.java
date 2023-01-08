package com.restaurant.database.dao.impl;

import com.restaurant.database.SqlQueries;
import com.restaurant.database.dao.OrderDishDao;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.Language;
import com.restaurant.database.javaBeans.Order;
import com.restaurant.database.javaBeans.OrderDish;

import java.sql.SQLException;

public class OrderDishDaoImpl extends BaseImpl implements OrderDishDao {

    @Override
    public OrderDish findAllDishInOrder(Order order) {
        OrderDish orderDish;
        try {
            orderDish = new OrderDish(order);
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderDishQuery.SELECT_DISH_ID_WHERE_ORDER_ID);
            preparedStatement.setInt(1, order.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderDish.addDish(new DishDaoImpl(new Language("en")).findEntityById(resultSet.getInt("dish_id")));
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return orderDish;
    }

    @Override
    public boolean insertOrderDish(OrderDish orderDish) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderDishQuery.INSERT_INTO_ORDER_DISH);
            for (Dish dish : orderDish.getDishMap().keySet().stream().toList()) {
                preparedStatement.setInt(1, orderDish.getOrder().getId());
                preparedStatement.setInt(2, dish.getId());
                preparedStatement.setInt(3, orderDish.getDishMap().get(dish));
                preparedStatement.executeUpdate();
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

}
