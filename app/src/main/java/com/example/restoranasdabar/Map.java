package com.example.restoranasdabar;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;

public class Map extends AppCompatActivity {
    int x=200;
    int y=7;
    ConstraintLayout mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapview = findViewById(R.id.map_container);

        JSONArray tableArr;
        try {
            tableArr = new JSONArray(getIntent().getStringExtra("table_json_string"));
            for(int i=0;i<tableArr.length();i++){
                Button button = new Button(getApplicationContext());
                button.setY(Float.parseFloat(tableArr.getJSONObject(i).getString("Y")));
                button.setX(Float.parseFloat(tableArr.getJSONObject(i).getString("X")));
                button.setWidth(Integer.parseInt(tableArr.getJSONObject(i).getString("Width")));
                button.setHeight(Integer.parseInt(tableArr.getJSONObject(i).getString("Height")));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(), OrderFood.class);
                        getApplicationContext().startActivity(intent);
                    }
                });
                mapview.addView(button);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
