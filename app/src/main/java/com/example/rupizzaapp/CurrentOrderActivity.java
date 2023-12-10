package com.example.rupizzaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CurrentOrderActivity extends AppCompatActivity {

    private Button deleteOrder;
    private Button placeOrder;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private ListView pizzas;
    private String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentorder);

        pizzas = findViewById(R.id.pizzas);

        Order currentOrder = Order.getInstance();
        ArrayList<String> allPizzas = currentOrder.toStringArray();

        ArrayAdapter<String> pizzaAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allPizzas);
        pizzas.setAdapter(pizzaAdaptor);

        pizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) pizzas.getItemAtPosition(position);

                System.out.println(selectedItem);
            }
        });

    }

    private void deleteOrder() {

    }
}
