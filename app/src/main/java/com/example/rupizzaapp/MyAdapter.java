package com.example.rupizzaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Adapter class to handle the RecyclerView Object in Specialty Pizzas.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList <Item> specialityPizzaList;

    private View.OnClickListener clickListener;

    /**
     * Initializes the adapter to be used in the RecyclerView.
     * @param context Context object to pass information into the RecyclerView
     * @param specialityPizzaList List of all 10 specialty pizzas.
     */
    public MyAdapter(Context context, ArrayList <Item> specialityPizzaList){
        this.context = context;
        this.specialityPizzaList = specialityPizzaList;
    }

    /**
     * Creates the View for the RecyclerView object.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return the new ViewHolder object that sets-up the layout of RecyclerView.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    /**
     * Creates the set-up for the RecyclerView to populate pizza information.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pizzaNameView.setText(specialityPizzaList.get(position).getPizzaName());
        holder.toppingsView.setText(specialityPizzaList.get(position).getToppings());

        holder.itemView.setOnClickListener(clickListener);
        holder.itemView.setTag(position);

    }

    /**
     * Determines the number of items in the RecyclerView.
     * @return The number of specialty pizzas.
     */
    @Override
    public int getItemCount() {
        return specialityPizzaList.size();
    }

    /**
     * Handles a click on the RecyclerView.
     * @param listener Checks for a click on the RecyclerView.
     */
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    /**
     * An interface to help handle clicks on the RecyclerView.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
