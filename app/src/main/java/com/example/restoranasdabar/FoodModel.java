package com.example.restoranasdabar;

public class FoodModel {
    private String Name, Description;
    private float price;

    public FoodModel(String name, String description, float price) {
        Name = name;
        Description = description;
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
