package com.example.restoranasdabar.ui;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoranasdabar.FoodModel;
import com.example.restoranasdabar.MainActivity;
import com.example.restoranasdabar.Map;
import com.example.restoranasdabar.Menu_restaurant;
import com.example.restoranasdabar.Menu_selector;
import com.example.restoranasdabar.R;
import com.example.restoranasdabar.TimeTable;
import com.example.restoranasdabar.TopRestaurantsModel;

import java.sql.Time;
import java.util.ArrayList;

public class AvailableTimesAdapter extends RecyclerView.Adapter<AvailableTimesAdapter.ViewHolder>{
    Context ctx;
    ArrayList<TimeTable> timeList;
    public AvailableTimesAdapter(Context ctx, ArrayList<TimeTable> timeList) {
        this.ctx = ctx;
        this.timeList = timeList;
    }
    @NonNull
    @Override
    public AvailableTimesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View btnView = LayoutInflater.from(ctx.getApplicationContext()).inflate(R.layout.time_btn, null, false);

        return new AvailableTimesAdapter.ViewHolder(btnView);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableTimesAdapter.ViewHolder holder, int position) {
        TimeTable model = timeList.get(position);
        holder.name.setText(model.getTime());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = Menu_selector.getDate();
                if(data != null && model.getTime() != null) {
                    Menu_selector.makeVisable();
                    Menu_selector.setData(model.getTime());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.btntext);
            card = itemView.findViewById(R.id.timeBtn);

        }
    }
}