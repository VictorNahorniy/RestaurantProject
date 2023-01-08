package com.restaurant.controllers.paginationStrategy;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.DishCategory;
import com.restaurant.database.javaBeans.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class CategoryDishStrategy implements PaginationStrategy {
    @Override
    public List<Dish> execute(int page, int recordsPerPage, HttpServletRequest request) {
        String category = (String) request.getSession().getAttribute("category");
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        if (category == null) throw new IllegalArgumentException();
        if (category.equals("All")) {
            return dishDao.findAllWithPagination((page - 1) * recordsPerPage, recordsPerPage);
        }
        return dishDao.findDishByCategory(Objects.requireNonNull(DishCategory.convertStringToCategory(category)),
                (page - 1) * recordsPerPage,
                recordsPerPage);
    }
}
