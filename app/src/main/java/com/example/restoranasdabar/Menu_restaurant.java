package com.example.restoranasdabar;
import com.googlecode.flickrjandroid.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Menu_restaurant extends AppCompatActivity {
    ImageSlider imageSlider;
    TextView location, time, phone, name;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);
        imageSlider = findViewById(R.id.menu_imageslider);
        name = findViewById(R.id.menu_rest_name);
        phone = findViewById(R.id.textNumb);
        location = findViewById(R.id.textLocation);
        time = findViewById(R.id.textSchedule);
        rating = findViewById(R.id.menu_rating_rating_bar1);

        String name_position = getIntent().getStringExtra("topRestName");
        String location_position = getIntent().getStringExtra("topRestLoc");
        String schedule_position = getIntent().getStringExtra("topRestTime");
        String phone_position = getIntent().getStringExtra("topRestNumb");
        int rating_position = getIntent().getIntExtra("topRestRate", 0);
        int image_position = getIntent().getIntExtra("tophotelsimage",0);


        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.res1, null));
        images.add(new SlideModel(R.drawable.res2, null));
        images.add(new SlideModel(R.drawable.res3, null));
        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);

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