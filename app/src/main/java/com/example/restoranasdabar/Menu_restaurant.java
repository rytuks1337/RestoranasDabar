package com.example.restoranasdabar;
import com.example.restoranasdabar.ui.FoodAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.googlecode.flickrjandroid.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Menu_restaurant extends AppCompatActivity {
    ViewFlipper imageSlider;
    TextView location, time, phone, name;
    RecyclerView menu_list;
    Toolbar toolbar;
    CollapsingToolbarLayout head;
    RatingBar rating;
    ArrayList<FoodModel> food_list;
    JSONArray image_urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);



        imageSlider = findViewById(R.id.menu_viewFlipper);
        name = findViewById(R.id.menu_rest_name);
        phone = findViewById(R.id.textNumb);
        location = findViewById(R.id.textLocation);
        toolbar = findViewById(R.id.menu_toolbar);
        time = findViewById(R.id.textSchedule);
        rating = findViewById(R.id.menu_rating_rating_bar1);


        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        setSupportActionBar(toolbar);
        head = findViewById(R.id.menu_CollapsingToolbarLayout);

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
        imageSlider.setFlipInterval(4000);
        imageSlider.setInAnimation(this, R.anim.animation_in);
        imageSlider.setOutAnimation(this, R.anim.animation_out);
        imageSlider.startFlipping();

        name.setText(name_position);
        head.setTitle(name_position);
        time.setText(schedule_position);
        location.setText(location_position);
        phone.setText(phone_position);
        rating.setRating(rating_position);

        head.setCollapsedTitleTextColor(Color.WHITE);
        head.setExpandedTitleColor(Color.WHITE);


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