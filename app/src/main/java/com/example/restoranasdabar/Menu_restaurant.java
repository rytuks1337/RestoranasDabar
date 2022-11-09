package com.example.restoranasdabar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Menu_restaurant extends AppCompatActivity {
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);
        imageSlider = findViewById(R.id.menu_imageslider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.res1, null));
        images.add(new SlideModel(R.drawable.res2, null));
        images.add(new SlideModel(R.drawable.res3, null));
        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);
    }
}