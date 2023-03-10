package com.restaurant.database.dao.impl;

import com.restaurant.database.SqlQueries;
import com.restaurant.database.dao.ClientDao;
import com.restaurant.database.javaBeans.Client;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDaoImpl extends BaseImpl implements ClientDao {

    @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlQueries.ClientQuery.FIND_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setNumber(resultSet.getString("number"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setManager(resultSet.getInt("isManager") == 1);
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                clientList.add(client);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return clientList;
    }

    @Override
    public boolean delete(Client client) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeQuery(SqlQueries.ClientQuery.DELETE_BY_ID + client.getId());
            connection.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } finally {
            finishTransaction();
        }
        return true;
    }

    @Override
    public boolean deleteByID(int id) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(SqlQueries.ClientQuery.DELETE_BY_ID + id);
            connection.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } finally {
            finishTransaction();
        }
        return true;
    }

    @Override
    public boolean insert(Client client) {
        if (isLoginUsed(client.getLogin())) {
            return false;
        }
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.ClientQuery.INSERT_INTO_CLIENTS, Statement.RETURN_GENERATED_KEYS);
            prepareClientStatement(client);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                client.setId(resultSet.getInt(1));
                return true;
            }
            return false;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public Client update(Client client) {
        if (!isLoginUpdatable(client)) throw new IllegalArgumentException();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.ClientQuery.UPDATE_CLIENT);
            prepareClientStatement(client);
            preparedStatement.setInt(7, client.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            return findEntityById(client.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareClientStatement(Client client) throws SQLException {
        preparedStatement.setString(1, client.getNumber());
        preparedStatement.setString(2, client.getLogin());
        preparedStatement.setString(3, client.getPassword());
        preparedStatement.setInt(4, client.isManager() ? 1 : 0);
        preparedStatement.setString(5, client.getFirstName());
        preparedStatement.setString(6, client.getLastName());
    }

    @Override
    public Client findEntityById(int id) throws SQLException {
        Optional<Client> client = findAll().stream().filter(client1 -> client1.getId() == id).findFirst();
        if(client.isEmpty()){
            log.error(id + "not found");
            throw new SQLException();
        }
        return client.get();
    }

    @Override
    public Client findClientByNumber(String number) throws SQLException {
        Optional<Client> client = findAll().stream().filter(client1 -> client1.getNumber().equals(number)).findFirst();
        if(client.isEmpty()){
            log.error(number + " not found");
            throw new SQLException();
        }
        return client.get();
    }

    @Override
    public Client findClientByLogin(String login) throws SQLException {
        Optional<Client> client = findAll().stream().filter(client1 -> client1.getLogin().equals(login)).findFirst();
        if(client.isEmpty()){
            log.error(login + " not found");
            throw new SQLException();
        }
        return client.get();
    }

    @Override
    public List<Client> findClientByLastname(String lastname) {
        return findAll().stream().filter(client -> client.getLastName().equals(lastname)).toList();
    }

    @Override
    public List<Client> findManagers() {
        return findAll().stream().filter(Client::isManager).toList();
    }

    @Override
    public List<Client> findUsers() {
        return findAll().stream().filter(client -> !client.isManager()).toList();
    }

    @Override
    public Client checkClientLogin(String login, String password) {
        Optional<Client> client = findAll().stream()
                        .filter(client1 -> client1.getLogin().equals(login) && client1.getPassword().equals(password))
                        .findFirst();
        if(client.isEmpty()){
            log.error("User not found");
            throw new IllegalArgumentException("User not found");
        }
        return client.get();
    }

    @Override
    public boolean isLoginUsed(String login) {
        return !findAll().stream().filter(client -> client.getLogin().equals(login)).toList().isEmpty();
    }

    @Override
    public boolean isLoginUpdatable(Client user) {
        for (Client client : findAll()) {
            if (client.getLogin().equals(user.getLogin()) && client.getId() != user.getId()) {
                log.error("Login is used, can not update profile");
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean validate(String login) {
        for (Client client : findAll()) {
            if (client.getLogin().equals(login)) return false;
        }
        return true;
    }
}
