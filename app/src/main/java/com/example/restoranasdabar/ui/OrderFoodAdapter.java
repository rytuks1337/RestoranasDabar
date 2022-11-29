package com.example.restoranasdabar.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoranasdabar.FoodModel;
import com.example.restoranasdabar.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodAdapter.ViewHolder> {

    Context ctx;
    ArrayList<FoodModel> foodArray;

    public OrderFoodAdapter(Context ctx, ArrayList<FoodModel> foodArray) {
        this.ctx = ctx;
        this.foodArray = foodArray;
    }

    @NonNull
    @Override
    public OrderFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View resView = LayoutInflater.from(ctx.getApplicationContext()).inflate(R.layout.food_card, null, false);

        return new OrderFoodAdapter.ViewHolder(resView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderFoodAdapter.ViewHolder holder, int position) {
        FoodModel model = foodArray.get(position);

        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.price.setText(String.valueOf(model.getPrice()));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.card.isChecked()){
                    holder.card.setChecked(true);
                }
                else{
                    holder.card.setChecked(false);
                }

            }
        });

        holder.card.setOnCheckedChangeListener(new MaterialCardView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MaterialCardView card, boolean isChecked) {
                if(isChecked){
                    model.setSelected(true);
                }
                else{
                    model.setSelected(false);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return foodArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, description, price;
        MaterialCardView card;


        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            description = itemView.findViewById(R.id.Ingrediants);
            price = itemView.findViewById(R.id.Price);
            card = itemView.findViewById(R.id.card);

        }
    }

}

