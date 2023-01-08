package com.restaurant.database.javaBeans;

import java.util.Objects;

public class Addresses extends BaseEntity {
    int id;
    String city;
    String street;
    int buildingNumber;

    public Addresses() {
    }

    public Addresses(String city, String street, int buildingNumber) {
        this.id = 0;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Addresses addresses)) return false;
        return getBuildingNumber() == addresses.getBuildingNumber() && Objects.equals(getCity(), addresses.getCity()) && Objects.equals(getStreet(), addresses.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getStreet(), getBuildingNumber());
    }

    @Override
    public String toString() {
        return city + ' ' + street + ' ' + buildingNumber;
    }
}
