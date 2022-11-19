package com.example.restoranasdabar;

import android.graphics.Bitmap;

public class TopRestaurantsModel {
    String name, location, number, schedule;
    Bitmap image;
    float rating;

    public TopRestaurantsModel(String name, String location, String number, String schedule, Bitmap image, float rating) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.schedule = schedule;
        this.image = image;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
