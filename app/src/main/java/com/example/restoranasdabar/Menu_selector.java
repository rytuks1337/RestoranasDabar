package com.example.restoranasdabar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Menu_selector extends BottomSheetDialogFragment {

    public Menu_selector(){

    }
    @Override
    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved){

        View view = inflater.inflate(R.layout.activity_menu_selector, container, false);

        return view;
    }



}
