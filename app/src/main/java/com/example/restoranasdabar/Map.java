package com.example.restoranasdabar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Map extends AppCompatActivity {

    ImageView imageView;
    ConstraintLayout mapview;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapview = findViewById(R.id.map_container);
        imageView= findViewById(R.id.zoomable_map);
        JSONArray tableArr;

        bundle = new Bundle();
        bundle.putString("time", getIntent().getStringExtra("Time"));
        bundle.putString("date",getIntent().getStringExtra("Date"));

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
            ArrayList<Rect> mAreas = new ArrayList<>();
            float x,y;
            int w,h;
            for (int i = 0; i < tableArr.length(); i++) {
                Button button = new Button(getApplicationContext());
                x=Float.parseFloat(tableArr.getJSONObject(i).getString("x"));
                y=Float.parseFloat(tableArr.getJSONObject(i).getString("y"));
                w=Math.round(Float.parseFloat(tableArr.getJSONObject(i).getString("Width")));
                h=Math.round(Float.parseFloat(tableArr.getJSONObject(i).getString("Height")));

                if(tableArr.getJSONObject(i).getString("Shape").equals("C")){
                    button.setBackgroundResource(R.drawable.circle_button);
                    button.setX(x*2-w);
                    button.setY(y*2-h);
                    button.setLayoutParams(new ConstraintLayout.LayoutParams(w*2,h*2));

                }else{
                    button.setX(x*2);
                    button.setY(y*2-(h/2));
                    button.setLayoutParams(new ConstraintLayout.LayoutParams(w*2,h*2));
                }

                button.setBackground(Drawable.createFromPath("@drawable/ripple_anim"));

                button.setId(i + 1);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which) {
                                                    case DialogInterface.BUTTON_POSITIVE:

                                                        Intent intent = new Intent(Map.this, OrderFood.class);
                                                        intent.putExtra("table_json_string", getIntent().getStringExtra("table_json_string"));
                                                        intent.putExtra("Time", getIntent().getStringExtra("Time"));
                                                        intent.putExtra("Date", getIntent().getStringExtra("Date"));
                                                        intent.putExtra("Menu_info", getIntent().getStringExtra("Menu_info"));
                                                        intent.putExtra("Table_Num",String.valueOf( button.getId()));
                                                        intent.putExtra("map_url",getIntent().getStringExtra("map_url"));
                                                        Map.this.startActivity(intent);
                                                        break;

                                                    case DialogInterface.BUTTON_NEGATIVE:

                                                        bundle.putString("table", String.valueOf(button.getId()));


                                                        Complete_registration bottomSheet = new Complete_registration();
                                                        bottomSheet.setArguments(bundle);
                                                        bottomSheet.show(getSupportFragmentManager(), "TAG");

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
