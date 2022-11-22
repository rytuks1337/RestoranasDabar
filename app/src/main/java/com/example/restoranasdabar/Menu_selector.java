package com.example.restoranasdabar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Calendar;

public class Menu_selector extends BottomSheetDialogFragment {
    Context ctx1;
    public Menu_selector(){

    }
    @Override
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved){

        View view = inflater.inflate(R.layout.activity_menu_selector, container, false);
        CalendarView date = (CalendarView) view.findViewById(R.id.calendarView);
        date.setMinDate(System.currentTimeMillis() - 1000);
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                /*TableLayout layout = (TableLayout) view.findViewById(R.id.buttons);

                //set the properties for button
                Button btnTag = new Button(ctx1);
                btnTag.setLayoutParams(new TableLayout.LayoutParams());
                btnTag.setText("Button");
                btnTag.setId();

                //add button to the layout
                layout.addView(btnTag);*/
            }
        });
        return view;
    }


}
