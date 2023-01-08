package com.restaurant.database.dao;

import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.DishCategory;

import java.util.List;

public interface DishDao extends BaseDao<Dish> {
    Dish findDishByName(String name);

    List<Dish> findDishByWeight(int weight);

    List<Dish> findDishByPrice(float price);

    List<Dish> findAllWithPagination(int offset, int noOfRecords);

    List<Dish> findDishByCategory(DishCategory category, int offset, int noOfRecords);

    List<Dish> sortDishByName(boolean isSortingAscending, int offset, int noOfRecords);

    List<Dish> sortDishByPrice(boolean isSortingAscending, int offset, int noOfRecords);

    List<Dish> sortDishByCategory(boolean isSortingAscending, int offset, int noOfRecords);

    int findNumOfDishByCategory(DishCategory category);
}
