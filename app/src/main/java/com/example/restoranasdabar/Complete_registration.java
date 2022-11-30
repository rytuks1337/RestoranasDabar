package com.example.restoranasdabar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.restoranasdabar.ui.AvailableTimesAdapter;
import com.example.restoranasdabar.ui.FoodAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class Complete_registration extends BottomSheetDialogFragment {

    ArrayList<FoodModel> orderList;
    TextView res_time, res_date, table_id;
    ArrayAdapter<String> adapter;
    ListView list;
    ArrayList<String> names;


    public Complete_registration(){

    }
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved){
        View view = inflater.inflate(R.layout.activity_complete_registration, container, false);


        orderList = (ArrayList<FoodModel>) this.getArguments().getSerializable("orders");
        names = new ArrayList<String>();


        for(int i = 0; i < orderList.size(); i++)
        {
            names.add(orderList.get(i).getName());
        }

        res_time = view.findViewById(R.id.reservation_time);
        res_date = view.findViewById(R.id.reservation_date);
        table_id = view.findViewById(R.id.tableid);
        list = view.findViewById(R.id.foodOrder);


        res_time.setText(this.getArguments().getString("time"));
        res_date.setText(this.getArguments().getString("date"));
        table_id.setText(this.getArguments().getString("table_id"));

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);

        list.setAdapter(adapter);

        return view;
    }
}