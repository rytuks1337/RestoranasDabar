package com.example.restoranasdabar;
import com.googlecode.flickrjandroid.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Menu_restaurant extends AppCompatActivity {
    ViewFlipper imageSlider;
    TextView location, time, phone, name;
    RatingBar rating;
    JSONArray image_urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);
        imageSlider = findViewById(R.id.menu_viewFlipper);
        name = findViewById(R.id.menu_rest_name);
        phone = findViewById(R.id.textNumb);
        location = findViewById(R.id.textLocation);
        time = findViewById(R.id.textSchedule);
        rating = findViewById(R.id.menu_rating_rating_bar1);

        String name_position = getIntent().getStringExtra("topRestName");
        String location_position = getIntent().getStringExtra("topRestLoc");
        String schedule_position = getIntent().getStringExtra("topRestTime");
        String phone_position = getIntent().getStringExtra("topRestNumb");
        Float rating_position = getIntent().getExtras().getFloat("topRestRate", 0F);
        try {
            image_urls = new JSONArray(getIntent().getStringExtra("urlsInJsonString"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ArrayList<SlideModel> images = new ArrayList<>();
        GetDataFromWeb gt = null;
        for(int i=0;i<image_urls.length();i++){

            try {
                gt = new GetDataFromWeb(URLDecoder.decode(image_urls.getString(i), StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Thread th = new Thread(gt);
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ImageView newImage= new ImageView(this);
            newImage.setImageBitmap(gt.getImage());
            imageSlider.addView(newImage);

        }
        imageSlider.startFlipping();

        name.setText(name_position);
        time.setText(schedule_position);
        location.setText(location_position);
        phone.setText(phone_position);
        rating.setRating(rating_position);

    }

    public void openMenuSelector(View view){
        Menu_selector bottomSheet = new Menu_selector();
        bottomSheet.show(getSupportFragmentManager(), "TAG");
    }
    public void onClose(View view)
    {
        finish();
    }
}