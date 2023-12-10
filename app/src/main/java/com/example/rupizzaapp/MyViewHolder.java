package com.example.rupizzaapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView pizzaNameView, toppingsView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaNameView = itemView.findViewById(R.id.pizzaName);
        toppingsView = itemView.findViewById(R.id.pizzaToppings);

    }


}
