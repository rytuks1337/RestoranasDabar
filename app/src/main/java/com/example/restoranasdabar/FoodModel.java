package com.example.restoranasdabar;

public class FoodModel {
    private String Name, Description;
    private float price;
    private boolean isSelected;

    public FoodModel(String name, String description, float price, boolean isSelected) {
        Name = name;
        Description = description;
        this.price = price;
        this.isSelected = isSelected;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
