package com.restaurant.controllers.paginationStrategy;

import com.restaurant.database.javaBeans.Dish;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaginationStrategy {
    List<Dish> execute(int page, int recordsPerPage, HttpServletRequest request);
}
