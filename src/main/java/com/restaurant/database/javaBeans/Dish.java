package com.restaurant.database.javaBeans;

import java.util.Objects;

public class Dish extends BaseEntity {
    private int id;
    private float price;
    private String name;
    private int weight;
    private String describing;
    private String imagePath;
    private DishCategory category;

    public Dish() {
    }

    public Dish(float price, String name, int weight, String describing, String imagePath, DishCategory category) {
        this.id = 0;
        this.price = price;
        this.name = name;
        this.weight = weight;
        this.describing = describing;
        this.imagePath = imagePath;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescribing() {
        return describing;
    }

    public void setDescribing(String describing) {
        this.describing = describing;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish dish)) return false;
        return getId() == dish.getId() && Float.compare(dish.getPrice(), getPrice()) == 0 && getWeight() == dish.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getName(), getWeight(), getDescribing(), getImagePath());
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", describing='" + describing + '\'' +
                ", image=" + imagePath +
                '}';
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public DishCategory getCategory() {
        return category;
    }
}
