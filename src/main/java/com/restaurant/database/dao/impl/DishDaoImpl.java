package com.restaurant.database.dao.impl;

import com.restaurant.database.SqlQueries.DishQuery;
import com.restaurant.database.dao.DishDao;
import com.restaurant.database.javaBeans.Dish;
import com.restaurant.database.javaBeans.DishCategory;
import com.restaurant.database.javaBeans.Language;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishDaoImpl extends BaseImpl implements DishDao {
    private final Language language;

    public DishDaoImpl(Language language) {
        this.language = language;
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DishQuery.FIND_ALL_DISH);
            getDishByResultSet(dishList);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public boolean delete(Dish dish) {
        int deletedRows;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(DishQuery.DELETE_BY_ID + dish.getId());
            connection.close();
            statement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return deletedRows == 1;
    }

    @Override
    public boolean deleteByID(int id) {
        int deletedRows;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            deletedRows = statement.executeUpdate(DishQuery.DELETE_BY_ID + id);
            connection.close();
            statement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return deletedRows == 1;
    }

    @Override
    public boolean insert(Dish dish) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.INSERT_INTO_DISH, Statement.RETURN_GENERATED_KEYS);
            prepareDishStatement(dish);
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            dish.setId(resultSet.getInt(1));
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Dish update(Dish dish) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.UPDATE_DISH);
            prepareDishStatement(dish);
            preparedStatement.setInt(7, dish.getId());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return findEntityById(dish.getId());
    }

    private void prepareDishStatement(Dish dish) throws SQLException {
        preparedStatement.setBigDecimal(1, BigDecimal.valueOf(dish.getPrice()));
        preparedStatement.setString(2, dish.getName());
        preparedStatement.setInt(3, dish.getWeight());
        preparedStatement.setString(4, dish.getDescribing());
        preparedStatement.setString(5, dish.getImagePath());
        preparedStatement.setString(6, dish.getCategory().getString());
    }

    @Override
    public Dish findEntityById(int id) {
        Optional<Dish> dish = findAll().stream().filter(dish1 -> dish1.getId() == id).findFirst();
        if(dish.isEmpty()){
            log.error("Dish with id " + id + " not found");
            throw new IllegalArgumentException(String.valueOf(id));
        }
        return dish.get();
    }

    @Override
    public Dish findDishByName(String name) {
        Optional<Dish> dish = findAll().stream().filter(dish1 -> dish1.getName().equals(name)).findFirst();
        if(dish.isEmpty()){
            log.error("Dish with name " + name + " not found");
            throw new IllegalArgumentException(name);
        }
        return dish.get();
    }

    @Override
    public List<Dish> findDishByWeight(int weight) {
        return findAll().stream().filter(dish1 -> dish1.getWeight() == weight).toList();
    }

    @Override
    public List<Dish> findDishByPrice(float price) {
        return findAll().stream().filter(dish -> dish.getPrice() == price).toList();
    }

    @Override
    public List<Dish> findAllWithPagination(int offset, int noOfRecords) {
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.FIND_ALL_DISH_PAGINATION);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            resultSet = preparedStatement.executeQuery();
            getDishByResultSet(dishList);
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public List<Dish> findDishByCategory(DishCategory category, int offset, int noOfRecords) {
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.FIND_DISHES_BY_CATEGORY);
            preparedStatement.setString(1, category.getString());
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, noOfRecords);
            resultSet = preparedStatement.executeQuery();
            getDishByResultSet(dishList);
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public List<Dish> sortDishByName(boolean isSortingAscending, int offset, int noOfRecords) {
        String sortType = isSortingAscending ? "asc" : "desc";
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DishQuery.SORT_DISHES_BY_NAME + sortType
                    + " limit " + offset + ',' + noOfRecords);
            getDishByResultSet(dishList);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public List<Dish> sortDishByPrice(boolean isSortingAscending, int offset, int noOfRecords) {
        String sortType = isSortingAscending ? "asc" : "desc";
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DishQuery.SORT_DISHES_BY_PRICE + sortType
                    + " limit " + offset + ',' + noOfRecords);
            getDishByResultSet(dishList);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public List<Dish> sortDishByCategory(boolean isSortingAscending, int offset, int noOfRecords) {
        String sortType = isSortingAscending ? "asc" : "desc";
        List<Dish> dishList = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DishQuery.SORT_DISHES_BY_CATEGORY + sortType
                    + " limit " + offset + ',' + noOfRecords);
            getDishByResultSet(dishList);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return dishList;
    }

    @Override
    public int findNumOfDishByCategory(DishCategory category) {
        int numOfDishByCategory = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.FIND_DISHES_COUNT_BY_CATEGORY);
            preparedStatement.setString(1, category.getString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                numOfDishByCategory = resultSet.getInt(1);
            } else throw new IllegalArgumentException();
            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
        return numOfDishByCategory;
    }

    private void getDishByResultSet(List<Dish> dishList) throws SQLException {
        while (resultSet.next()) {
            Dish dish = new Dish();
            dish.setId(resultSet.getInt("id"));
            dish.setPrice(resultSet.getFloat("price"));
            //dish.setName(resultSet.getString("name"));
            //dish.setDescribing(resultSet.getString("describing"));
            dish.setImagePath(resultSet.getString("image_path"));
            dish.setWeight(resultSet.getInt("weight"));
            //dish.setCategory(DishCategory.convertStringToCategory(resultSet.getString("category")));
            setDishContentByLang(dish);
            dishList.add(dish);
        }
    }

    private void setDishContentByLang(Dish dish) {
        try {
            //connection = getConnection();
            preparedStatement = connection.prepareStatement(DishQuery.FIND_DISH_CONTENT_BY_LANG);
            preparedStatement.setInt(1, dish.getId());
            preparedStatement.setInt(2, language.getId());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            if (resultSet1.next()) {
                dish.setName(resultSet1.getString("dishName"));
                dish.setDescribing(resultSet1.getString("dishDescribing"));
                dish.setCategory(DishCategory.convertStringToCategory(resultSet1.getString("dishCategory")));
            }
            resultSet1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
