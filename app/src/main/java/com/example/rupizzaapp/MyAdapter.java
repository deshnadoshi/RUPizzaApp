package com.example.rupizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList <Item> specialityPizzaList;

    private View.OnClickListener clickListener;



    public MyAdapter(Context context, ArrayList <Item> specialityPizzaList){
        this.context = context;
        this.specialityPizzaList = specialityPizzaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pizzaNameView.setText(specialityPizzaList.get(position).getPizzaName());
        holder.toppingsView.setText(specialityPizzaList.get(position).getToppings());

        holder.itemView.setOnClickListener(clickListener);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return specialityPizzaList.size();
    }


    public void setOnItemClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
