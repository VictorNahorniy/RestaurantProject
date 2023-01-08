package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends BaseDao<Client> {
    Client findClientByNumber(String number) throws SQLException;

    Client findClientByLogin(String login) throws SQLException;

    List<Client> findClientByLastname(String lastname);

    List<Client> findManagers();

    List<Client> findUsers();

    Client checkClientLogin(String login, String password);

    boolean isLoginUsed(String login);

    boolean isLoginUpdatable(Client user);

    boolean validate(String login);
}
