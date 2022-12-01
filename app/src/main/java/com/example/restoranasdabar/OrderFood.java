package com.example.restoranasdabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restoranasdabar.ui.OrderFoodAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class OrderFood extends AppCompatActivity {
    RecyclerView menu_list;
    ArrayList<FoodModel> food_list, order_list;
    Button orderbtn;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);


        menu_list = findViewById(R.id.menu_food_list);
        orderbtn = findViewById(R.id.makeordBtn);
        orderbtn.setVisibility(View.VISIBLE);

        bundle = new Bundle();
        bundle.putString("time", getIntent().getStringExtra("Time"));
        bundle.putString("date", getIntent().getStringExtra("Date"));
        bundle.putString("table",getIntent().getStringExtra("Table_Num") );


        food_list = new ArrayList<>();
        JSONArray jsonmenu = null;
        try {
            jsonmenu= new JSONArray(getIntent().getStringExtra("Menu_info"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsonmenu!=null) {
            for (int i = 0; i < jsonmenu.length(); i++) {

                try {
                    food_list.add(new FoodModel(jsonmenu.getJSONObject(i).getString("name"), jsonmenu.getJSONObject(i).getString("description"), (float) jsonmenu.getJSONObject(i).getDouble("cost"), false));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



        OrderFoodAdapter adapter = new OrderFoodAdapter(this, food_list);
        menu_list.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        menu_list.setLayoutManager(manager);

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order_list = new ArrayList<>();
                for(FoodModel item : food_list){
                    if(item.isSelected()) {
                        order_list.add(new FoodModel(item.getName(), item.getDescription(), item.getPrice(), true));
                    }
                }
                bundle.putSerializable("orders", order_list);
                Complete_registration bottomSheet = new Complete_registration();
                bottomSheet.setArguments(bundle);
                bottomSheet.show(getSupportFragmentManager(), "TAG");
                System.out.println(order_list.size());
            }

        });



    }

}