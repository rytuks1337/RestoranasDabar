package com.example.restoranasdabar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.restoranasdabar.ui.AvailableTimesAdapter;
import com.example.restoranasdabar.ui.TopRestaurantsAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Menu_selector extends BottomSheetDialogFragment {

    int startTime, endTime;

    ArrayList<TimeTable> timeList;
    AvailableTimesAdapter adapter;
    RecyclerView buttonlist;

    public Menu_selector(){

    }
    @Override
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved){
        View view = inflater.inflate(R.layout.activity_menu_selector, container, false);
        CalendarView date = (CalendarView) view.findViewById(R.id.calendarView);

        buttonlist = (RecyclerView) view.findViewById(R.id.buttons);

        timeList= new ArrayList<TimeTable>();
        String myValue = this.getArguments().getString("time");
        int[] time = TimeParser.timeInMin(myValue);
        startTime = time[0]*60 + time[1];
        endTime = (time[2]*60 + time[3])-60;
        date.setMinDate(System.currentTimeMillis() - 1000);

        
        createBtns();

        adapter = new AvailableTimesAdapter(getContext(), timeList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        buttonlist.setAdapter(adapter);
        buttonlist.setLayoutManager(manager);

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

            }
        });
        return view;
    }

    private void createBtns() {
        for(int i = startTime; i < endTime; i+=15)
        {
            String j= String.valueOf(i%60);
            if((i%60) == 0){
                j = "00";
            }
            timeList.add(new TimeTable(String.valueOf(i/60) + ":" + j));
        }

    }


}
