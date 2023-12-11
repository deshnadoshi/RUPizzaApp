package com.example.rupizzaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Button deleteOrder;
    private Spinner orderNumber;
    private ListView pizzaOrder;
    private TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorders);
        orderNumber = findViewById(R.id.orderNumber);
        StoreOrders storeOrder = StoreOrders.getInstance();
        ArrayList<Order> allOrders = storeOrder.getStore_orders();
        ArrayList<String> allOrderNumbers = storeOrder.getOrderNumbers();
        orderNumber.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter orderNumbersAdaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, allOrderNumbers);
        orderNumbersAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumber.setAdapter(orderNumbersAdaptor);

    }

    private void showAlertDialog(int position, ArrayAdapter<String> pizzaAdaptor, ArrayList<String> allOrderNumbers) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        StoreOrders storeOrder = StoreOrders.getInstance();
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete the following order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Order removed.");
                        storeOrder.deleteOrder(position);
                        allOrderNumbers.remove(position);
                        pizzaAdaptor.notifyDataSetChanged();
                        ArrayAdapter orderNumbersAdaptor = (ArrayAdapter) orderNumber.getAdapter();
                        orderNumbersAdaptor.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        StoreOrders storeOrder = StoreOrders.getInstance();
        ArrayList<Order> allOrders = storeOrder.getStore_orders();
        ArrayList<String> allOrderNumbers = storeOrder.getOrderNumbers();
        pizzaOrder = findViewById(R.id.pizzaOrder);
        ArrayList<String> allPizzas = allOrders.get(position).toStringArray();
        ArrayAdapter<String> pizzaAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allPizzas);
        pizzaOrder.setAdapter(pizzaAdaptor);
        updatePrices(allOrders.get(position));

        deleteOrder = findViewById(R.id.deleteOrder);
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(position, pizzaAdaptor, allOrderNumbers);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updatePrices(Order order) {
        ArrayList<Pizza> pizzas = order.getAllOrders();
        double total_pre_tax_price = 0.00;
        for (int i = 0; i < pizzas.size(); i++) {
            total_pre_tax_price += pizzas.get(i).price();
        }
        total = findViewById(R.id.total);
        double salesTax = total_pre_tax_price * 0.06625;
        double priceTotal = salesTax + total_pre_tax_price;
        total.setText("Order Total: " + String.format("%.2f", priceTotal));
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
