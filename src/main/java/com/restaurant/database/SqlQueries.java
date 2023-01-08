package com.restaurant.database;

public class SqlQueries {
    public class ClientQuery {
        public static final String FIND_ALL_CLIENTS = "select * from client_table";
        public static final String INSERT_INTO_CLIENTS = "insert into client_table(number, login, password, isManager, first_name, last_name) values(?, ?, ?, ?, ?, ?)";
        public static final String DELETE_BY_ID = "delete from client_table where id = ";
        public static final String UPDATE_CLIENT = "update client_table set number = ?, login = ?, password = ?, isManager = ?, first_name = ?, last_name = ? where id = ?";
    }

    public class AddressesQuery {
        public static final String FIND_ALL_ADDRESSES = "select * from adress";
        public static final String DELETE_BY_ID = "delete from adress where id = ";
        public static final String INSERT_INTO_ADDRESSES = "insert into adress(city,street,building_number) values(?,?,?)";
        public static final String UPDATE_ADDRESS = "update adress set city = ?, street = ?, building_number = ? where id = ?";
    }

    public class DishQuery {
        public static final String FIND_ALL_DISH = "select * from dish";
        public static final String DELETE_BY_ID = "delete from dish where id = ?";
        public static final String UPDATE_DISH = "update dish set  price = ?, name = ?, weight = ?, describing = ?, image_path = ?, category = ? where id = ?";
        public static final String INSERT_INTO_DISH = "insert into dish(price, name, weight, describing, image_path, category) values (?, ?, ?, ?, ?, ?)";
        public static final String SORT_DISHES_BY_NAME = "select * from dish order by name ";
        public static final String SORT_DISHES_BY_PRICE = "select * from dish order by price ";
        public static final String SORT_DISHES_BY_CATEGORY = "select * from dish order by category ";
        public static final String FIND_DISHES_BY_CATEGORY = "select * from dish where category = ? limit ?, ?";
        public static final String FIND_DISHES_COUNT_BY_CATEGORY = "select count(id) from dish where category = ?";
        public static final String FIND_ALL_DISH_PAGINATION = "select* from dish limit ?, ?";
        public static final String FIND_DISH_CONTENT_BY_LANG ="select dishName, dishDescribing, dishCategory from dish_content where dish_id = ? and language_id = ?";
    }

    public class OrderQuery {
        public static final String FIND_ALL_ORDERS = "select * from order_table";
        public static final String DELETE_BY_ID = "delete from order_table where id = ?";
        public static final String INSERT_INTO_ORDER = "insert into order_table(price, order_date, client_id, status, adress_id) values(?, ?, ?, ?, ?)";
        public static final String UPDATE_ORDER = "update order_table set price = ?, order_date = ?, client_id = ?, status = ?, adress_id = ? where id = ?";
        public static final String UPDATE_ORDER_STATUS = "update order_table set status = ? where id = ?";
    }

    public class OrderDishQuery {
        public static final String INSERT_INTO_ORDER_DISH = "insert into order_dish(order_id, dish_id, quantity) values(?,?,?)";
        public static final String SELECT_DISH_ID_WHERE_ORDER_ID = "select dish_id from order_dish where order_id = ?";
    }
}
