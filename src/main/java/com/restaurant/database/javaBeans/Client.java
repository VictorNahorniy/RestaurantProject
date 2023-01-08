package com.restaurant.database.javaBeans;

import java.util.Objects;

public class Client extends BaseEntity {
    int id;
    String number;
    String login;
    String password;
    boolean isManager;
    String firstName;
    String lastName;

    public Client() {
    }


    public Client(String number, String login, String password, boolean isManager, String firstName, String lastName) {
        this.id = 0;
        this.number = number;
        this.login = login;
        this.password = password;
        this.isManager = isManager;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return getId() == client.getId()
                && isManager() == client.isManager()
                && Objects.equals(getNumber(), client.getNumber())
                && Objects.equals(getLogin(), client.getLogin())
                && Objects.equals(getPassword(), client.getPassword())
                && Objects.equals(getFirstName(), client.getFirstName())
                && Objects.equals(getLastName(), client.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getLogin(), getPassword(), isManager(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isManager=" + isManager +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
