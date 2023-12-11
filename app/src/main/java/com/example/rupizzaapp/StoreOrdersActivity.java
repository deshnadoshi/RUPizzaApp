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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Handles and creates the Store Orders screen.
 * @author Haejin Song, Deshna Doshi
 */

public class StoreOrdersActivity extends AppCompatActivity implements OnItemSelectedListener {

    private Button deleteOrder;
    private Spinner orderNumber;
    private ListView pizzaOrder;
    private TextView total;

    /**
     * Initializes the Store Orders screen.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorders);
        orderNumber = findViewById(R.id.orderNumber);
        StoreOrders storeOrder = StoreOrders.getInstance();
        ArrayList<Order> allOrders = storeOrder.getStore_orders();
        ArrayList<String> allOrderNumbers = storeOrder.getOrderNumbers();
        orderNumber.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<String> orderNumbersAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allOrderNumbers);
        orderNumbersAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumber.setAdapter(orderNumbersAdaptor);
    }

    /**
     * Displays the AlertDialog.
     * @param position The position of the selected order.
     * @param allPizzas The list of all orders.
     * @param pizzaAdaptor The adapter controlling the list.
     * @param allOrderNumbers The list of all order numbers
     */
    private void showAlertDialog(int position, ArrayAdapter<String> pizzaAdaptor, ArrayList<String> allOrderNumbers, ArrayList<String> allPizzas) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        StoreOrders storeOrder = StoreOrders.getInstance();
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete the following order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Order removed.");
                        storeOrder.deleteOrder(position);
                        allPizzas.clear();
                        allOrderNumbers.remove(position);
                        ArrayAdapter<String> orderNumbersAdapter = (ArrayAdapter<String>) orderNumber.getAdapter();
                        orderNumbersAdapter.clear();
                        orderNumbersAdapter.addAll(allOrderNumbers);
                        orderNumbersAdapter.notifyDataSetChanged();
                        ArrayList<String> allPizzas = StoreOrders.getInstance().getStore_orders().get(0).toStringArray();
                        pizzaAdaptor.clear();
                        pizzaAdaptor.addAll(allPizzas);
                        pizzaAdaptor.notifyDataSetChanged();
                        if (!allOrderNumbers.isEmpty()) {
                            orderNumber.setSelection(0);
                        }
                        updatePrices(null);
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

    /**
     * Handles deleting an order.
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
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
                showAlertDialog(position, pizzaAdaptor, allOrderNumbers, allPizzas);
            }
        });
    }

    /**
     * Handles no selection.
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Updates prices based on changes in the Store Orders screen.
     * @param order The Order that changed.
     */
    private void updatePrices(Order order) {
        if (order == null) {
            total.setText("Order Total:");
        } else {
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
    }

    /**
     * Builds and displays a Toast.
     * @param message Message to be displayed on the toast.
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
