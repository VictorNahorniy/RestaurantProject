package com.restaurant.database.javaBeans;

import java.sql.Timestamp;
import java.util.Objects;

public class Order extends BaseEntity {
    private int id;
    private float price;
    private Timestamp orderDate;
    private int clientID;
    private OrderStatus status;
    private int addressesID;

    public Order() {
    }

    public Order(float price, Timestamp orderDate, int clientID, OrderStatus status, int addressesID) {
        this.id = 0;
        this.price = price;
        this.orderDate = orderDate;
        this.clientID = clientID;
        this.status = status;
        this.addressesID = addressesID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getAddressesID() {
        return addressesID;
    }

    public void setAddressesID(int addressesID) {
        this.addressesID = addressesID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId() == order.getId() && Float.compare(order.getPrice(), getPrice()) == 0 && getClientID() == order.getClientID() && getStatus().equals(order.getStatus()) && getAddressesID() == order.getAddressesID() && Objects.equals(getOrderDate(), order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getOrderDate(), getClientID(), getStatus(), getAddressesID());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", clientID=" + clientID +
                ", status=" + status +
                ", addressesID=" + addressesID +
                '}';
    }
}
