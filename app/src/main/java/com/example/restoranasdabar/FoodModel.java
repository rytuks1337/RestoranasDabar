package com.example.restoranasdabar;

public class FoodModel {
    String Name, Description, Category;
    float price;

    public FoodModel(String name, String description, String category, float price) {
        Name = name;
        Category = category;
        Description = description;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
