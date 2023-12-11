package com.example.rupizzaapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generates the View for the RecyclerView object in Specialty Pizzas.
 * @author Deshna Doshi, Haejin Song
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView pizzaNameView, toppingsView;

    /**
     * Constructor used to populate the items in the RecyclerView.
     * @param itemView The View object that handles the layout of the RecyclerView.
     */
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaNameView = itemView.findViewById(R.id.pizzaName);
        toppingsView = itemView.findViewById(R.id.pizzaToppings);

    }


}
