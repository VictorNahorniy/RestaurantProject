package com.restaurant.controllers.paginationStrategy;

import com.restaurant.database.dao.impl.DishDaoImpl;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SortingDishStrategy implements PaginationStrategy {
    @Override
    public List<Dish> execute(int page, int recordsPerPage, HttpServletRequest request) {
        String sort = (String) request.getSession().getAttribute("sortType");
        List<Dish> dishList = null;
        Language lang = new Language((String) request.getSession().getAttribute("lang"));
        DishDaoImpl dishDao = new DishDaoImpl(lang);
        if (sort.equals("nonSort")) {
            dishList = dishDao.findAllWithPagination((page - 1) * recordsPerPage, recordsPerPage);
        }
        if (sort.equals("name")) {
            dishList = dishDao.sortDishByName(true, (page - 1) * recordsPerPage, recordsPerPage);
        }
        if (sort.equals("price")) {
            dishList = dishDao.sortDishByPrice(true, (page - 1) * recordsPerPage, recordsPerPage);
        }
        if (sort.equals("category")) {
            dishList = dishDao.sortDishByCategory(true, (page - 1) * recordsPerPage, recordsPerPage);
        }
        return dishList;
    }
}
