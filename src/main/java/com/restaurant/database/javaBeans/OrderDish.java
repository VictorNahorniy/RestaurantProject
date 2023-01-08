package com.restaurant.database.javaBeans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@Setter
@Getter
public class OrderDish extends BaseEntity {
    Order order;
    HashMap<Dish, Integer> dishMap;

    public OrderDish() {
    }

    public OrderDish(Order order) {
        this.order = order;
        this.dishMap = new HashMap<>();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public HashMap<Dish, Integer> getDishMap() {
        return dishMap;
    }

    public void setDishMap(HashMap<Dish, Integer> dishMap) {
        this.dishMap = dishMap;
    }

    public boolean addDish(Dish dish) {
        if (dishMap.containsKey(dish)) {
            int quantity = dishMap.get(dish);
            dishMap.put(dish, ++quantity);
        } else dishMap.put(dish, 1);
        return true;
    }

    public boolean deleteDish(Dish dish) {
        int quantity = dishMap.get(dish);
        if (quantity > 1) {
            dishMap.put(dish, --quantity);
        } else dishMap.put(dish, 1);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDish orderDish)) return false;
        return Objects.equals(getOrder(), orderDish.getOrder()) && Objects.equals(getDishMap(), orderDish.getDishMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getDishMap());
    }

    @Override
    public String toString() {
        return "OrderDish{" +
                "order=" + order +
                ", dishMap=" + dishMap +
                '}';
    }

    public float getTotalPrice() {
        return (float) dishMap.entrySet().stream().mapToDouble(x -> x.getKey().getPrice() * x.getValue()).sum();
    }
}
