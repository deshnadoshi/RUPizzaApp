package com.example.rupizzaapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SpecialtyPizzasActivity extends AppCompatActivity {

    private RecyclerView specialtyPizzaType;
    private TextView specialtySauce;
    private TextView specialtyPrice;
    private TextView specialtyTopping;
    private ImageView specialtyImage;
    private RadioGroup specialtySize;
    private CheckBox specialtyExtraCheese;
    private CheckBox specialtyExtraSauce;
    private Button specialtyAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
    }
}
