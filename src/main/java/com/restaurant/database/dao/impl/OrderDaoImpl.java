package com.restaurant.database.dao.impl;

import com.restaurant.database.SqlQueries;
import com.restaurant.database.dao.OrderDao;
import com.restaurant.database.javaBeans.Order;
import com.restaurant.database.javaBeans.OrderStatus;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoImpl extends BaseImpl implements OrderDao {
    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlQueries.OrderQuery.FIND_ALL_ORDERS);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPrice(resultSet.getFloat("price"));
                order.setOrderDate(resultSet.getTimestamp("order_date"));
                order.setClientID(resultSet.getInt("client_id"));
                order.setStatus(OrderStatus.convertStringToStatus(resultSet.getString("status")));
                order.setAddressesID(resultSet.getInt("adress_id"));
                orderList.add(order);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return orderList;
    }

    @Override
    public boolean delete(Order order) {
        return deleteByID(order.getId());
    }

    @Override
    public boolean deleteByID(int id) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderQuery.DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Order order) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderQuery.INSERT_INTO_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, order.getPrice());
            preparedStatement.setTimestamp(2, order.getOrderDate());
            preparedStatement.setInt(3, order.getClientID());
            preparedStatement.setString(4, order.getStatus().getString());
            preparedStatement.setInt(5, order.getAddressesID());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (!resultSet.next()) return false;
            order.setId(resultSet.getInt(1));
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public Order update(Order order) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderQuery.UPDATE_ORDER);
            preparedStatement.setFloat(1, order.getPrice());
            preparedStatement.setTimestamp(2, order.getOrderDate());
            preparedStatement.setInt(3, order.getClientID());
            preparedStatement.setString(4, order.getStatus().getString());
            preparedStatement.setInt(5, order.getAddressesID());
            preparedStatement.setInt(6, order.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return findEntityById(order.getId());
    }

    @Override
    public Order findEntityById(int id) {
        for (Order order : findAll()) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

    @Override
    public List<Order> findOrderByPrice(float price) {
        return findAll()
                .stream()
                .filter(order -> order.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByDate(Date date) {
        return findAll()
                .stream()
                .filter(order -> order.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByClientID(int clientID) {
        return findAll()
                .stream()
                .filter(order -> order.getClientID() == clientID)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByAddressID(int address) {
        return findAll()
                .stream()
                .filter(order -> order.getAddressesID() == address)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findOrderByStatus(OrderStatus status) {
        return findAll()
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateStatus(Order order, OrderStatus orderStatus) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.OrderQuery.UPDATE_ORDER_STATUS);
            preparedStatement.setString(1, orderStatus.getString());
            preparedStatement.setInt(2, order.getId());
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

}
