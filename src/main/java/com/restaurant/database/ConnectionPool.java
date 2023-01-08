package com.restaurant.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConnectionPool extends MysqlDataSource {
    public static final Logger log = LogManager.getLogger();
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/restaurant";
    private static final String dbUserLogin = "root";
    private static final String dbUserPassword = "root";
    private static ConnectionPool connectionPool;
    private final int POOL_SIZE = 10;
    private final Queue<Connection> connectionQueue;

    private ConnectionPool(String url, String username, String password) {
        super();
        setUrl(url);
        setUser(username);
        setPassword(password);
        connectionQueue = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                var connection = new ConnectionProxy(super.getConnection(), connectionQueue);
                connectionQueue.add(connection);
            } catch (SQLException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(connectionUrl, dbUserLogin, dbUserPassword);
        }
        return connectionPool;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionQueue.poll();
    }
}
