package com.example.restoranasdabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restoranasdabar.ui.FoodAdapter;

import java.util.ArrayList;

public class foodMenu extends AppCompatActivity {
    RecyclerView menu_list;
    ArrayList<FoodModel> food_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        menu_list = findViewById(R.id.menu_food_list);

        food_list = new ArrayList<>();
        food_list.add(new FoodModel("Pizza", "Cheese, tomato sauce, peperonni", "Main Foods", (float) 5.80));
        food_list.add(new FoodModel("Pizza", "Cheese, tomato sauce, peperonni", "Main Foods", (float) 5.80));
        food_list.add(new FoodModel("Pizza", "Cheese, tomato sauce, peperonni", "Main Foods", (float) 5.80));
        food_list.add(new FoodModel("Pizza", "Cheese, tomato sauce, peperonni", "Main Foods", (float) 5.80));

        FoodAdapter adapter = new FoodAdapter(this, food_list);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menu_list.setLayoutManager(manager);
        menu_list.setAdapter(adapter);
    }
}