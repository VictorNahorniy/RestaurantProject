package com.restaurant.database.javaBeans;

public enum OrderStatus {
    NEW("new"),
    COOKING("cooking"),
    DELIVERING("delivering"),
    COMPLETED("completed");

    private String status;
    OrderStatus(String status) {
        this.status = status;
    }

    public String getString() {
        return status;
    }

    public static OrderStatus convertStringToStatus(String status){
        for (OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.getString().equals(status)){
                return orderStatus;
            }
        }
        return null;
    }
}
