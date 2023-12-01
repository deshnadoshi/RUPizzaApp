package com.example.rupizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button byoMain;
    private Button coMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openBYO(savedInstanceState);
        openCurrentOrder(savedInstanceState);

    }

    protected void openBYO(Bundle savedInstanceState){
        byoMain = findViewById(R.id.byoMain);
        byoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuildYourOwnActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void openCurrentOrder(Bundle savedInstanceState){
        coMain = findViewById(R.id.coMain);
        coMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }




}