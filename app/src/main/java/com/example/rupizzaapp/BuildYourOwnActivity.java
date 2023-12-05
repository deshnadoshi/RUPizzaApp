package com.example.rupizzaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Creates and manages the Build Your Own Screen.
 * @author Deshna Doshi, Haejin Song
 */
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
    private CheckBox byoExtraSauce;
    private CheckBox byoExtraCheese;

    private ArrayList<String> all_toppings;
    private ArrayList <String> on_pizza_toppings;
    private ArrayAdapter adapterAdd;
    private ArrayAdapter adapterRemove;
    private Pizza new_order = null;


    /**
     * Initializes the Build Your Own Screen.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        byoPrice = findViewById(R.id.byoPrice);

        byoPrice.setText(R.string.initial_price);
        byoAddTopping = findViewById(R.id.byoAddTopping);
        byoRemoveTopping = findViewById(R.id.byoRemoveTopping);
        all_toppings = new ArrayList<>();
        all_toppings = populateToppings(all_toppings);
        on_pizza_toppings = new ArrayList<>();

        byoAddTopping = findViewById(R.id.byoAddTopping);
        adapterAdd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, all_toppings);
        byoAddTopping.setAdapter(adapterAdd);

        byoRemoveTopping = findViewById(R.id.byoRemoveTopping);
        adapterRemove = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, on_pizza_toppings);
        byoRemoveTopping.setAdapter(adapterRemove);

        if (selectedSize() == null || selectedSauce() == null){
            byoAddToOrder = findViewById(R.id.byoAddToOrder);
            byoAddToOrder.setEnabled(false);
        }

        addOnTopping();
        removePizzaTopping();
        updatePriceOnClick();

    }

    /**
     * Handles adding a topping to the pizza.
     */
    private void addOnTopping(){
        byoAddTopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(all_toppings.get(position) + " topping: Updated.");

                String selectedTopping = all_toppings.get(position);
                on_pizza_toppings.add(selectedTopping);
                adapterRemove.notifyDataSetChanged();

                all_toppings.remove(position);
                adapterAdd.notifyDataSetChanged(); // notifies the adapter that the list has changed
                calculatePrice();
                if (on_pizza_toppings.size() < 3){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(false);
                }

                if (on_pizza_toppings.size() > 7){
                    buildAlert("Cannot choose more than 7 toppings. Please remove any excess toppings.");
                    byoAddToOrder.setEnabled(false);
                }
                if (selectedSauce() != null && selectedSize() != null && on_pizza_toppings.size() >= 3 && on_pizza_toppings.size() <= 7){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(true);
                }

            }

        });

    }

    /**
     * Handles removing a topping from the pizza.
     */
    private void removePizzaTopping(){
        byoRemoveTopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(on_pizza_toppings.get(position) + " topping: Updated.");

                String selectedTopping = on_pizza_toppings.get(position);
                all_toppings.add(selectedTopping);
                adapterRemove.notifyDataSetChanged();
                on_pizza_toppings.remove(position);
                adapterAdd.notifyDataSetChanged(); // notifies the adapter that the list has changed
                calculatePrice();
                if (on_pizza_toppings.size() < 3){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(false);
                }
                if (on_pizza_toppings.size() > 7){
                    buildAlert("Cannot choose more than 7 toppings. Please remove any excess toppings.");
                    byoAddToOrder.setEnabled(false);
                }
                if (selectedSauce() != null && selectedSize() != null && on_pizza_toppings.size() >= 3 && on_pizza_toppings.size() <= 7){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(true);
                }

            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void calculatePrice(){
        double pizzaPrice = 0.0;
        byoSize = findViewById(R.id.byoSize);
        byoSauce = findViewById(R.id.byoSauce);
        byoExtraCheese = findViewById(R.id.byoExtraCheese);
        byoExtraSauce = findViewById(R.id.byoExtraSauce);
        int selectedSizeId = byoSize.getCheckedRadioButtonId();
        int selectedSauceId = byoSauce.getCheckedRadioButtonId();
        RadioButton selectedSize = findViewById(selectedSizeId);
        RadioButton selectedSauce = findViewById(selectedSauceId);
        if (selectedSize != null && selectedSauce != null){
            new_order = PizzaMaker.createPizza("BuildYourOwn");
        }
        if (new_order != null && selectedSize != null && selectedSauce != null){
            new_order.setPizzaSize(selectedSize());
            new_order.setSauce(selectedSauce());
            pizzaPrice += new_order.advancedPrice();
        }
        if (on_pizza_toppings.size() > 3){
            for (int i = 4; i <= on_pizza_toppings.size(); i++){
                pizzaPrice += 1.49;
            }
        }
        if (byoExtraCheese.isChecked() && new_order != null){
            new_order.setExtraCheese(true);
            pizzaPrice += 1;
        } else if (!byoExtraCheese.isChecked() && new_order != null){
            new_order.setExtraCheese(false);
        }
        if (byoExtraSauce.isChecked() && new_order != null){
            new_order.setExtraSauce(true);
            pizzaPrice += 1;
        } else if (!byoExtraSauce.isChecked() && new_order != null){
            new_order.setExtraSauce(false);
        }
        if (new_order != null){
            new_order.setPrice(pizzaPrice);
        }
        byoPrice = findViewById(R.id.byoPrice);
        byoPrice.setText(String.format("%.2f", pizzaPrice));
    }

    /**
     * Updates the price of the pizza in real time.
     */
    private void updatePriceOnClick(){
        RadioGroup byoSize = findViewById(R.id.byoSize);
        byoSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (selectedSauce() != null && selectedSize() != null && on_pizza_toppings.size() >= 3 && on_pizza_toppings.size() <= 7){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(true);
                }
                calculatePrice();
            }
        });
        RadioGroup byoSauce = findViewById(R.id.byoSauce);
        byoSauce.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (selectedSauce() != null && selectedSize() != null && on_pizza_toppings.size() >= 3 && on_pizza_toppings.size() <= 7){
                    byoAddToOrder = findViewById(R.id.byoAddToOrder);
                    byoAddToOrder.setEnabled(true);
                }
                calculatePrice();
            }
        });
        CheckBox byoExtraCheese = findViewById(R.id.byoExtraCheese); // Replace with your CheckBox ID
        CheckBox byoExtraSauce = findViewById(R.id.byoExtraSauce); // Replace with your CheckBox ID
        byoExtraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculatePrice();
            }
        });
        byoExtraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculatePrice();
            }
        });

    }

    /**
     * Determines the Size enum corresponding to the selected size on the Build Your Own screen.
     * @return Size of the pizza.
     */
    private Size selectedSize(){
        byoSize = findViewById(R.id.byoSize);
        int selectedSizeId = byoSize.getCheckedRadioButtonId();
        RadioButton selectedSize = findViewById(selectedSizeId);

        if (selectedSize != null){
            String sizeName = selectedSize.getText().toString();
            if (sizeName.equals("small")){
                return Size.SMALL;
            } else if (sizeName.equals("medium")){
                return Size.MEDIUM;
            } else if (sizeName.equals("large")){
                return Size.LARGE;
            }
        }
        return null;
    }

    /**
     * Determines the Sauce enum corresponding to the selected sauce on the Build Your Own screen.
     * @return Sauce on the pizza.
     */
    private Sauce selectedSauce(){
        byoSauce = findViewById(R.id.byoSauce);
        int selectedSauceId = byoSauce.getCheckedRadioButtonId();
        RadioButton selectedSauce = findViewById(selectedSauceId);

        if (selectedSauce != null){
            String sizeName = selectedSauce.getText().toString();
            if (sizeName.equals("tomato sauce")){
                return Sauce.TOMATO;
            } else if (sizeName.equals("alfredo sauce")){
                return Sauce.ALFREDO;
            }
        }
        return null;
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

    /**
     * Builds the alert message.
     * @param message Text to display on the alert message.
     */
    private void buildAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this); // Use 'this' as the context
        alert.setCancelable(true);
        alert.setMessage(message);
        alert.setPositiveButton( "Okay", (dialog, id) -> dialog.cancel());
        alert.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        alert.show();
    }

    /**
     * Builds the Toast message.
     * @param message Text to display on the Toast message.
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }




}
