package com.example.rupizzaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class CurrentOrderActivity extends AppCompatActivity {
    private Button placeOrder;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private TextView order_number;
    private ListView pizzas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentorder);
        pizzas = findViewById(R.id.pizzas);
        Order currentOrder = Order.getInstance();
        ArrayList<String> allPizzas = currentOrder.toStringArray();
        ArrayAdapter<String> pizzaAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allPizzas);
        pizzas.setAdapter(pizzaAdaptor);
        updatePrices();
        order_number = findViewById(R.id.order_number);
        order_number.setText("Order number: " + currentOrder.getOrderNumber());
        pizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(allPizzas.get(position), allPizzas, pizzaAdaptor, position, currentOrder);
            }
        });
        placeOrder = findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order currentOrder = Order.getInstance();
                if (currentOrder.getAllOrders().size() > 0) {
                    StoreOrders currentStoreOrder = StoreOrders.getInstance();
                    currentStoreOrder.addOrder(currentOrder);
                    currentOrder.deleteOrder();
                    showToast("Order has been placed.");
                    updatePrices();
                    allPizzas.clear();
                    pizzaAdaptor.notifyDataSetChanged();
                } else {
                    showToast("You must add at least one pizza to your order.");
                }
            }
        });
    }

    // Method to show the alert dialog
    private void showAlertDialog(String selectedItem, ArrayList<String> allPizzas, ArrayAdapter<String> pizzaAdaptor, int position, Order currentOrder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Do you want to delete the following pizza: " + selectedItem)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Pizza removed.");
                        allPizzas.remove(position);
                        pizzaAdaptor.notifyDataSetChanged();
                        currentOrder.removePizza(position);
                        updatePrices();
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

    private void updatePrices() {
        Order order = Order.getInstance();
        ArrayList<Pizza> pizzas = order.getAllOrders();
        double total_pre_tax_price = 0.00;
        for (int i = 0; i < pizzas.size(); i++) {
            total_pre_tax_price += pizzas.get(i).price();
        }
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        subtotal.setText("Subtotal: " + String.format("%.2f", total_pre_tax_price));
        double salesTax = total_pre_tax_price * 0.06625;
        tax.setText("Tax: " + String.format("%.2f", salesTax));
        double priceTotal = salesTax + total_pre_tax_price;
        total.setText("Total: " + String.format("%.2f", priceTotal));
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
