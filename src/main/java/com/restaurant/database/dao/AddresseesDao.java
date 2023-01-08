package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.Addresses;

import java.util.List;

public interface AddresseesDao extends BaseDao<Addresses> {
    List<Addresses> findAddressesByCity(String city);

    List<Addresses> findAddressesByStreet(String street);
}
