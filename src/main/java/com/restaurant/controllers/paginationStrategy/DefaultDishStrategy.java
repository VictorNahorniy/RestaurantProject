package com.restaurant.controllers.paginationStrategy;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DefaultDishStrategy implements PaginationStrategy {
    @Override
    public List<Dish> execute(int page, int recordsPerPage, HttpServletRequest request) {
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        return new DishDaoImpl(lang).findAllWithPagination((page - 1) * recordsPerPage, recordsPerPage);
    }
}
