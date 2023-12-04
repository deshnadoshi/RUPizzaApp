package com.example.rupizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    private ArrayAdapter adapter;


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

    private void addOnTopping(){
        byoAddTopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(all_toppings.get(position) + " topping added!");
                all_toppings.remove(position);
                adapter.notifyDataSetChanged(); // notifies the adapter that the list has changed

            }
        });

    }

    /**
     * Populates the list of toppings in the Build Your Own screen.
     * @param toppings ArrayList of all of the available toppings.
     * @return An ArrayList with all of the available toppings added in
     */
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

    private void buildAlert(AlertDialog.Builder alert) {
        alert.setCancelable(true);
        alert.setPositiveButton( "Okay", (dialog, id) -> dialog.cancel());
        alert.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }




}
