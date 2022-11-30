package com.example.restoranasdabar;

import android.graphics.Bitmap;

public class TopRestaurantsModel {
    String name, location, number, schedule, about;
    Bitmap image;
    String urls;//json string
    String jsonMenu;//json string
    String mapUrl;//json string
    String jsonTable;//json string
    float rating;

    public TopRestaurantsModel(String name, String location, String number, String schedule, Bitmap image, float rating, String about, String urls, String jsonMenu, String jsonTable, String map_url) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.about = about;
        this.urls=urls;
        this.schedule = schedule;
        this.image = image;
        this.jsonTable=jsonTable;
        this.rating = rating;
        this.mapUrl=map_url;
        this.jsonMenu=jsonMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getJsonTable() {
        return jsonTable;
    }

    public void setJsonTable(String jsonTable) {
        this.jsonTable = jsonTable;
    }

    public String getJsonMenu() {
        return jsonMenu;
    }

    public void setJsonMenu(String jsonMenu) {
        this.jsonMenu = jsonMenu;
    }
}