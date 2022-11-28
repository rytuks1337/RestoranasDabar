package com.example.restoranasdabar;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoranasdabar.ui.TopRestaurantsAdapter;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mNavDrawer;
    RecyclerView top_res_pager;
    ArrayList<TopRestaurantsModel> restaurantlist;
    SearchView search;
    Toolbar main;
    TopRestaurantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_res_pager = findViewById(R.id.top_res_pager);
        search = findViewById(R.id.main_SearchView);
        main = findViewById(R.id.main_Toolbar);
        //setSupportActionBar(main);


        restaurantlist = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(getAllRestaurants());
            GetDataFromWeb gt = new GetDataFromWeb();
            for(int i=0;i<jsonArray.length();i++){
                gt.setUrl(URLDecoder.decode(jsonArray.getJSONObject(i).getJSONArray("urls").getString(0), StandardCharsets.UTF_8.name()));
                Thread th = new Thread(gt);
                th.start();
                th.join();

                restaurantlist.add(new TopRestaurantsModel(jsonArray.getJSONObject(i).getString("Name"), jsonArray.getJSONObject(i).getString("Location"), jsonArray.getJSONObject(i).getString("Telephone"), jsonArray.getJSONObject(i).getString("Hours"), gt.getImage(), Float.parseFloat(jsonArray.getJSONObject(i).getString("Rating")), jsonArray.getJSONObject(i).getString("About"),jsonArray.getJSONObject(i).getJSONArray("urls").toString(),jsonArray.getJSONObject(i).getJSONArray("Menu").toString(),jsonArray.getJSONObject(i).getJSONArray("Tables").toString()));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        adapter = new TopRestaurantsAdapter(this, restaurantlist);
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

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    private void filterList(String text) {
        ArrayList<TopRestaurantsModel> filteredList = new ArrayList<>();
        for(TopRestaurantsModel item : restaurantlist){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.setFilteredList(filteredList);
        }

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
    public String getAllRestaurants(){
        String result = null;
        FetchData fetchData = new FetchData("http://185.80.130.112/restinfo.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                result = fetchData.getResult();
            }
        }
        return result;
    }


}