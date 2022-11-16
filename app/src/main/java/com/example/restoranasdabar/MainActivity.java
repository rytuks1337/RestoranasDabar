package com.example.restoranasdabar;


import android.icu.lang.UCharacter;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.restoranasdabar.ui.TopRestaurantsAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mNavDrawer;
    RecyclerView top_res_pager;
    ArrayList<TopRestaurantsModel> restaurantlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        restaurantlist = new ArrayList<>();
        restaurantlist.add(new TopRestaurantsModel("Pirmas rest", "Laisves al. 62a", "+123123132", "8:00-19:00", R.drawable.res1, 4));
        restaurantlist.add(new TopRestaurantsModel("Pirmas rest", "Laisves al. 62a", "+123123132", "8:00-19:00", R.drawable.res1, 5));
        restaurantlist.add(new TopRestaurantsModel("Pirmas rest", "Laisves al. 62a", "+123123132", "8:00-19:00", R.drawable.res1, 3));


        top_res_pager = findViewById(R.id.top_res_pager);

        TopRestaurantsAdapter adapter = new TopRestaurantsAdapter(this, restaurantlist);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        top_res_pager.setAdapter(adapter);
        top_res_pager.setLayoutManager(manager);

        Toolbar toolbar=findViewById(R.id.main_Toolbar);
        setSupportActionBar(toolbar);

        mNavDrawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,mNavDrawer,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mNavDrawer.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    public void onBackPressed(){
        if(mNavDrawer.isDrawerOpen(GravityCompat.START)){
            mNavDrawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}