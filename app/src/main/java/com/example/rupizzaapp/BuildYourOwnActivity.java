package com.example.rupizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BuildYourOwnActivity extends AppCompatActivity {

    private Button byoRetHome;
    private Button byoAddPizzaTopping;
    private Button byoRemovePizzaTopping;
    private ListView byoAddTopping;
    private ListView byoRemoveTopping;
    private RadioGroup byoSize;
    private RadioGroup byoSauce;
    private Button byoAddToOrder;
    private TextView byoPrice;
    private ArrayList<String> all_toppings;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        byoPrice = findViewById(R.id.byoPrice);

        byoPrice.setText(R.string.initial_price);
        byoAddTopping = findViewById(R.id.byoAddTopping);
        byoRemoveTopping = findViewById(R.id.byoRemoveTopping);
        ArrayList <String> all_toppings = new ArrayList<>();
        all_toppings = populateToppings(all_toppings);

        byoAddTopping = findViewById(R.id.byoAddTopping);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, all_toppings);
        byoAddTopping.setAdapter(adapter);


    }

    private ArrayList <String> populateToppings(ArrayList<String> toppings){
        toppings.add("GreenPepper");
        toppings.add("Onion");
        toppings.add("Pineapple");
        toppings.add("BlackOlives");
        toppings.add("Mushroom");
        toppings.add("Sausage");
        toppings.add("Chicken");
        toppings.add("Beef");
        toppings.add("Ham");
        toppings.add("CrabMeats");
        toppings.add("Pepperoni");
        toppings.add("Shrimp");
        toppings.add("Squid");

        return toppings;
    }


}
