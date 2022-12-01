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
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restoranasdabar.ui.AvailableTimesAdapter;
import com.example.restoranasdabar.ui.FoodAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Complete_registration extends BottomSheetDialogFragment {

    ArrayList<FoodModel> orderList;
    TextView res_time;
    Button makeOrdBtn;
    TextView res_date;
    TextView price;
    TextView table_id;
    TextView ordered;
    ArrayAdapter<String> adapter;
    BottomSheetBehavior<View> behavior;
    float sum = 0;
    ListView list;
    ArrayList<String> names;


    public Complete_registration(){}
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved){
        View view = inflater.inflate(R.layout.activity_complete_registration, container, false);

        orderList = (ArrayList<FoodModel>) this.getArguments().getSerializable("orders");

        names = new ArrayList<String>();
        makeOrdBtn= view.findViewById(R.id.makeordBtn);
        res_time = view.findViewById(R.id.reservation_time);
        res_date = view.findViewById(R.id.reservation_date);
        price = view.findViewById(R.id.price);
        ordered = view.findViewById(R.id.ordered_food);
        table_id = view.findViewById(R.id.tableid);
        list = view.findViewById(R.id.foodOrder);

        if(orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {
                names.add(orderList.get(i).getName());
                sum += (float) orderList.get(i).getPrice();
            }
            price.setVisibility(View.VISIBLE);
            ordered.setVisibility(View.VISIBLE);
        }

        makeOrdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(getContext(),"Reservation Complete",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        res_time.setText("Time: "+this.getArguments().getString("time"));
        res_date.setText("Date: "+this.getArguments().getString("date"));
        table_id.setText("Table: "+this.getArguments().getString("table"));
        price.setText(String.valueOf(sum));

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);

        list.setAdapter(adapter);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saved){
        super.onViewCreated(view, saved);

        behavior = BottomSheetBehavior.from((View) view.getParent());
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}