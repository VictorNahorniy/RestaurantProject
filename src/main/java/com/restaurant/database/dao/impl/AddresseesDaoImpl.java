package com.restaurant.database.dao.impl;

import com.restaurant.database.SqlQueries;
import com.restaurant.database.dao.AddresseesDao;
import com.restaurant.database.javaBeans.Addresses;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddresseesDaoImpl extends BaseImpl implements AddresseesDao {

    @Override
    public List<Addresses> findAddressesByCity(String city) {
        List<Addresses> addressesList = new ArrayList<>();
        for (Addresses addresses : findAll()) {
            if (city.equals(addresses.getCity())) {
                addressesList.add(addresses);
            }
        }
        return addressesList;
    }

    @Override
    public List<Addresses> findAddressesByStreet(String street) {
        List<Addresses> addressesList = new ArrayList<>();
        for (Addresses addresses : findAll()) {
            if (street.equals(addresses.getStreet())) {
                addressesList.add(addresses);
            }
        }
        return addressesList;
    }

    @Override
    public List<Addresses> findAll() {
        List<Addresses> addressesList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlQueries.AddressesQuery.FIND_ALL_ADDRESSES);
            while (resultSet.next()) {
                Addresses addresses = new Addresses();
                addresses.setId(resultSet.getInt("id"));
                addresses.setCity(resultSet.getString("city"));
                addresses.setStreet(resultSet.getString("street"));
                addresses.setBuildingNumber(resultSet.getInt("building_number"));
                addressesList.add(addresses);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return addressesList;
    }

    public Addresses getAddressFromDB(Addresses addresses) {
        return findAll().stream().filter(x -> x.equals(addresses)).findFirst().orElse(null);
    }

    @Override
    public boolean delete(Addresses addresses) {
        return deleteByID(addresses.getId());
    }

    @Override
    public boolean deleteByID(int id) {
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SqlQueries.AddressesQuery.DELETE_BY_ID + id);
            connection.close();
            statement.close();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Addresses addresses) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.AddressesQuery.INSERT_INTO_ADDRESSES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, addresses.getCity());
            preparedStatement.setString(2, addresses.getStreet());
            preparedStatement.setInt(3, addresses.getBuildingNumber());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                addresses.setId(resultSet.getInt(1));
                connection.close();
                preparedStatement.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Addresses update(Addresses addresses) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SqlQueries.AddressesQuery.UPDATE_ADDRESS);
            preparedStatement.setString(1, addresses.getCity());
            preparedStatement.setString(2, addresses.getStreet());
            preparedStatement.setInt(3, addresses.getBuildingNumber());
            preparedStatement.setInt(4, addresses.getId());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return findEntityById(addresses.getId());
    }

    @Override
    public Addresses findEntityById(int id) {
        for (Addresses addresses : findAll()) {
            if (id == addresses.getId()) {
                return addresses;
            }
        }
        log.error(id + " not found");
        throw new IllegalArgumentException(String.valueOf(id));
    }
}
