package com.restaurant.database.javaBeans;

public enum DishCategory {
    ALL("All"),
    PIZZA("Pizza"),
    DRINK("Drink"),
    SUSHI("Sushi"),
    SOUP("Soup"),
    HOT_MEAL("Hot_meal"),
    SALAD("Salad"),
    DESSERT("Dessert");


    private String string;

    DishCategory(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static DishCategory convertStringToCategory(String category) {
        for (DishCategory category1 : DishCategory.values()) {
            if (category1.string.equals(category)) {
                return category1;
            }
        }
        return null;
    }

}
