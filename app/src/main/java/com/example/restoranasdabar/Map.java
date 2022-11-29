package com.example.restoranasdabar;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
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
        Context ctx = getApplicationContext();
        JSONArray tableArr;
        try {
            tableArr = new JSONArray(getIntent().getStringExtra("table_json_string"));

            for(int i=0;i<tableArr.length();i++){
                Button button = new Button(getApplicationContext());
                button.setY(Float.parseFloat(tableArr.getJSONObject(i).getString("Y")));
                button.setX(Float.parseFloat(tableArr.getJSONObject(i).getString("X")));
                button.setBackground(Drawable.createFromPath("?android:attr/selectableItemBackground"));
                button.setText(String.valueOf(i+1));
                button.setTextColor(R.color.black);
                button.setTextSize(30);
                button.setWidth(Integer.parseInt(tableArr.getJSONObject(i).getString("Width")));
                button.setHeight(Integer.parseInt(tableArr.getJSONObject(i).getString("Height")));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        /*Intent intent = new Intent(ctx, OrderFood.class);
                        intent.putExtra("table_json_string", getIntent().getStringExtra("table_json_string"));
                        intent.putExtra("Time", getIntent().getStringExtra("Time"));
                        intent.putExtra("Date", getIntent().getStringExtra("Date"));
                        intent.putExtra("Menu_info", getIntent().getStringExtra("Menu_info"));
                        ctx.startActivity(intent);*/
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        //Yes button clicked
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);
                        builder.setMessage("Reserve table ?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

                    }
                });

                mapview.addView(button);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
