package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    List<T> findAll();

    boolean delete(T t);

    boolean deleteByID(int id);

    boolean insert(T t);

    T update(T t);

    T findEntityById(int id) throws SQLException;

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //log
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //log
        }
    }
}
