package com.example.restoranasdabar;

import android.graphics.Bitmap;

import org.json.JSONArray;

public class TopRestaurantsModel {
    String name, location, number, schedule;
    Bitmap image;
    String urls, jsonMenu;//json string
    float rating;

    public TopRestaurantsModel(String name, String location, String number, String schedule, Bitmap image, float rating, String urls, String jsonMenu) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.urls=urls;
        this.schedule = schedule;
        this.image = image;
        this.rating = rating;
        this.jsonMenu=jsonMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getjsonMenu() {
        return jsonMenu;
    }

    public void setjsonMenu(String jsonMenu) {
        this.jsonMenu = jsonMenu;
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}