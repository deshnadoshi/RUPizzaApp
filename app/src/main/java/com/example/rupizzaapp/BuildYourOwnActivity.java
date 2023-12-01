package com.example.rupizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class BuildYourOwnActivity extends AppCompatActivity {

    private Button byoRetHome;
    private Button byoAddPizzaTopping;
    private Button byoRemovePizzaTopping;
    private RecyclerView byoAddTopping;
    private RecyclerView byoRemoveTopping;
    private RadioGroup byoSize;
    private RadioGroup byoSauce;
    private Button byoAddToOrder;
    private TextView byoPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        byoPrice = findViewById(R.id.byoPrice);
        // byoPrice.setText();
    }



}
