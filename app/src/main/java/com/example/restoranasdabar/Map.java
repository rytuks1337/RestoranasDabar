package com.example.restoranasdabar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Map extends AppCompatActivity {

    ImageView imageView;
    ConstraintLayout mapview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapview = findViewById(R.id.map_container);
        imageView= findViewById(R.id.zoomable_map);
        Context ctx = getApplicationContext();
        JSONArray tableArr;

        GetDataFromWeb gt = new GetDataFromWeb();

        try {
            gt.setUrl(URLDecoder.decode(getIntent().getStringExtra("map_url"), StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Thread th = new Thread(gt);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(gt.getImage());
        try {
            tableArr = new JSONArray(getIntent().getStringExtra("table_json_string"));

            for(int i=0;i<tableArr.length();i++){
                Button button = new Button(getApplicationContext());
                button.setY(Float.parseFloat(tableArr.getJSONObject(i).getString("y"))*2f);
                button.setX(Float.parseFloat(tableArr.getJSONObject(i).getString("x"))*2f);
                button.setBackground(Drawable.createFromPath("?android:attr/selectableItemBackground"));
                button.setText(String.valueOf(i+1));
                button.setTextColor(getResources().getColor(R.color.black));
                button.setTextSize(30);
                button.setId(i+1);
                button.setWidth(Math.round(Float.parseFloat(tableArr.getJSONObject(i).getString("Width"))));
                button.setHeight(Math.round(Float.parseFloat(tableArr.getJSONObject(i).getString("Height"))));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which){
                                                    case DialogInterface.BUTTON_POSITIVE:

                                                        Intent intent = new Intent(Map.this, OrderFood.class);
                                                        intent.putExtra("table_json_string", getIntent().getStringExtra("table_json_string"));
                                                        intent.putExtra("Time", getIntent().getStringExtra("Time"));
                                                        intent.putExtra("Date", getIntent().getStringExtra("Date"));
                                                        intent.putExtra("Menu_info", getIntent().getStringExtra("Menu_info"));
                                                        intent.putExtra("Table_Num", button.getId());
                                                        Map.this.startActivity(intent);
                                                        break;

                                                    case DialogInterface.BUTTON_NEGATIVE:


                                                        break;
                                                }
                                            }
                                        };

                                        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);
                                        builder.setMessage("Do you wish to order food as well?").setPositiveButton("Yes", dialogClickListener)
                                                .setNegativeButton("No", dialogClickListener).show();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(Map.this);
                        builder.setMessage("Reserve table " + button.getId() + "?").setPositiveButton("Yes", dialogClickListener)
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
