package com.example.restoranasdabar.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoranasdabar.MainActivity;
import com.example.restoranasdabar.Menu_restaurant;
import com.example.restoranasdabar.R;
import com.example.restoranasdabar.TopRestaurantsModel;

import java.util.ArrayList;


public class TopRestaurantsAdapter extends RecyclerView.Adapter<TopRestaurantsAdapter.ViewHolder> {

    Context ctx;
    ArrayList<TopRestaurantsModel> restaurantsArray;


    public TopRestaurantsAdapter(Context ctx, ArrayList<TopRestaurantsModel> restaurantsArray){

        this.ctx = ctx;
        this.restaurantsArray = restaurantsArray;

    }

    public void setFilteredList(ArrayList<TopRestaurantsModel> filteredList){
        this.restaurantsArray = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View topResView = LayoutInflater.from(ctx.getApplicationContext()).inflate(R.layout.top_restaurants, null, false);
        return new ViewHolder(topResView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopRestaurantsModel model = restaurantsArray.get(position);

        holder.name.setText(model.getName());
        holder.location.setText(model.getLocation());
        holder.schedule.setText(model.getSchedule());
        holder.number.setText(model.getNumber());
        holder.image.setImageBitmap(model.getImage());
        holder.rating.setRating(model.getRating());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, Menu_restaurant.class);
                intent.putExtra("topRestName", model.getName());
                intent.putExtra("topRestLoc", model.getLocation());
                intent.putExtra("topRestTime", model.getSchedule());
                intent.putExtra("topRestNumb", model.getNumber());
                intent.putExtra("topRestRate", model.getRating());
                intent.putExtra("urlsInJsonString",model.getUrls());
                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurantsArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, location, schedule, number;
        CardView time, phone, pin;
        ImageView image;
        RatingBar rating;
        CardView card;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.top_res_name);
            location = itemView.findViewById(R.id.top_res_location);
            schedule = itemView.findViewById(R.id.top_res_time);
            number = itemView.findViewById(R.id.top_res_numb);
            image = itemView.findViewById(R.id.imageView5);
            pin = itemView.findViewById(R.id.ic1);
            phone = itemView.findViewById(R.id.ic2);
            time = itemView.findViewById(R.id.ic3);
            rating = itemView.findViewById(R.id.top_rating);
            card = itemView.findViewById(R.id.top_res_info);



        }
    }

}