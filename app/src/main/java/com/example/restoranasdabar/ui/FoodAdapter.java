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

import com.example.restoranasdabar.FoodModel;
import com.example.restoranasdabar.Menu_restaurant;
import com.example.restoranasdabar.R;
import com.example.restoranasdabar.TopRestaurantsModel;
import com.example.restoranasdabar.foodMenu;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context ctx;
    ArrayList<FoodModel> foodArray;

    public FoodAdapter(Context ctx, ArrayList<FoodModel> foodArray) {
        this.ctx = ctx;
        this.foodArray = foodArray;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resView = LayoutInflater.from(ctx.getApplicationContext()).inflate(R.layout.food_card, null, false);
        return new FoodAdapter.ViewHolder(resView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodModel model = foodArray.get(position);

        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(String.valueOf(model.getPrice()));
    }

    @Override
    public int getItemCount() {
        return foodArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, description, price, category;
        ImageView currency;
        CardView card;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            description = itemView.findViewById(R.id.Ingrediants);
            price = itemView.findViewById(R.id.Price);
            card = itemView.findViewById(R.id.card);

        }
    }
}
