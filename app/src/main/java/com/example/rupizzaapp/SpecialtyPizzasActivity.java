package com.example.rupizzaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rupizzaapp.MyAdapter.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;

public class SpecialtyPizzasActivity extends AppCompatActivity {
    private RecyclerView specialtyPizzaType;
    private TextView specialtySauce;
    private TextView specialtyPrice;
    private TextView specialtyTopping;
    private ImageView specialtyImage;
    private RadioGroup specialtySize;
    private CheckBox specialtyExtraCheese;
    private CheckBox specialtyExtraSauce;
    private Button specialtyAdd;
    private TextView pizzaImageName;
    private Spinner specialtyQty;

    private ArrayList<Pizza> my_pizzas = new ArrayList<>();
    private Order current_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        specialtyPizzaType = findViewById(R.id.specialtyPizzaType);
        specialtyPizzaType.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(getApplicationContext(), populateRecyclerView());
        specialtyPizzaType.setAdapter(adapter);
        setIDs();
        setSpinnerIDs();
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item selectedItem = populateRecyclerView().get(specialtyPizzaType.getChildAdapterPosition(view));
                setPizzaImage(selectedItem.getPizzaName());
                calculatePrice(specialtyPizzaType.getChildAdapterPosition(view));
                specialtySize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        calculatePrice(specialtyPizzaType.getChildAdapterPosition(view));
                    }
                });
                specialtyExtraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        calculatePrice(specialtyPizzaType.getChildAdapterPosition(view));
                    }
                });
                specialtyExtraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        calculatePrice(specialtyPizzaType.getChildAdapterPosition(view));
                    }
                });
            }
        });
        specialtyAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder();
            }
        });

    }

    private ArrayList<Item> populateRecyclerView(){
        ArrayList<Item> allSpecialtyPizzas = new ArrayList<>();
        allSpecialtyPizzas.add(new Item("Deluxe", "$14.99; Tomato Sauce; Sausage, Pepperoni, GreenPepper, Onion, Mushroom"));
        allSpecialtyPizzas.add(new Item("Hawaiian", "$12.99; Alfredo Sauce; Pineapple, BlackOlives, Ham, Pepperoni"));
        allSpecialtyPizzas.add(new Item("Meatzza", "$16.99; Tomato Sauce; Sausage, Pepperoni, Beef, Ham"));
        allSpecialtyPizzas.add(new Item("Mediterranean", "$14.99; Tomato Sauce; BlackOlives, Onion, Mushroom, Chicken"));
        allSpecialtyPizzas.add(new Item("Pepperific", "$16.99; Tomato Sauce; GreenPepper, Pepperoni, Sausage, Mushroom"));
        allSpecialtyPizzas.add(new Item("Pepperoni", "$10.99; Tomato Sauce; Pepperoni"));
        allSpecialtyPizzas.add(new Item("Seafood", "$17.99; Alfredo Sauce; Shrimp, Squid, CrabMeats"));
        allSpecialtyPizzas.add(new Item("Supreme", "$15.99; Tomato Sauce; Sausage, Pepperoni, Ham, GreenPepper, Onion, BlackOlives, Mushroom"));
        allSpecialtyPizzas.add(new Item("Tropizest", "$14.99; Alfredo Sauce; Pineapple, BlackOlives, Ham, Pepperoni"));
        allSpecialtyPizzas.add(new Item("Veggievista", "$13.99; Tomato Sauce; GreenPepper, Onion, BlackOlives, Mushroom"));
        return allSpecialtyPizzas;
    }

    private void setPizzaImage(String pizzaName){
        if (pizzaName.equals("Deluxe")){
            chooseImage("deluxe");
        } else if (pizzaName.equals("Hawaiian")){
            chooseImage("hawaiian");
        } else if (pizzaName.equals("Meatzza")){
            chooseImage("meatzza");
        } else if (pizzaName.equals("Mediterranean")){
            chooseImage("mediterranean");
        } else if (pizzaName.equals("Pepperific")){
            chooseImage("pepperific");
        } else if (pizzaName.equals("Pepperoni")){
            chooseImage("pepperoni");
        } else if (pizzaName.equals("Seafood")){
            chooseImage("seafood");
        } else if (pizzaName.equals("Supreme")){
            chooseImage("supreme");
        } else if (pizzaName.equals("Tropizest")){
            chooseImage("tropizest");
        } else if (pizzaName.equals("Veggievista")){
            chooseImage("veggievista");

        }

    }

    private void chooseImage(String imageName) {
        pizzaImageName = findViewById(R.id.pizzaImageName);
        pizzaImageName.setText(imageName.toUpperCase());
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resourceId != 0)
            specialtyImage.setImageResource(resourceId);
    }

    /**
     * Calculate the price of a specialty pizza.
     */
    private void calculatePrice(int position){
        Item selectedItem = populateRecyclerView().get(position);
        String pizzaType = selectedItem.getPizzaName();
        specialtySize = findViewById(R.id.specialtySize);
        int selectedSizeId = specialtySize.getCheckedRadioButtonId();
        RadioButton selectedSize = findViewById(selectedSizeId);
        specialtyExtraCheese = findViewById(R.id.specialtyExtraCheese);
        specialtyExtraSauce = findViewById(R.id.specialtyExtraSauce);
        double pizzaPrice = 0;
        Pizza temp = null;
        if (selectedSize == null){
            specialtyAdd.setEnabled(false);
        } else {
            specialtyAdd.setEnabled(true);
        }
        if (selectedSize != null){
            temp = PizzaMaker.createPizza(pizzaType);
        }
        if (temp != null){
            temp.setPizzaSize(selectedSize());
            pizzaPrice = temp.price();
        }
        if (specialtyExtraCheese.isChecked() && temp != null){
            temp.setExtraCheese(true);
            pizzaPrice += 1;
        }
        if (specialtyExtraSauce.isChecked() && temp != null){
            temp.setExtraSauce(true);
            pizzaPrice += 1;
        }
        specialtyPrice = findViewById(R.id.specialtyPrice);
        specialtyPrice.setText("" + String.format("%.2f", pizzaPrice));
    }

    private void addToOrder(){
        specialtyQty = findViewById(R.id.specialtyQty);
        int quantity = (int) specialtyQty.getSelectedItem();

        specialtyAdd = findViewById(R.id.specialtyAdd);
        int countAlert = 0;

        pizzaImageName = findViewById(R.id.pizzaImageName);
        String pizzaType = pizzaImageName.getText().toString();

        if (!pizzaType.isEmpty())
            pizzaType = Character.toUpperCase(pizzaType.charAt(0)) + pizzaType.substring(1).toLowerCase();
        for (int i = 0; i < quantity; i++){
            Pizza new_pizza = PizzaMaker.createPizza(pizzaType);

            if (new_pizza != null){
                new_pizza.setExtraSauce(selectedExtraSauce());
                new_pizza.setExtraCheese(selectedExtraCheese());
                new_pizza.setPizzaSize(selectedSize());

                Double price = Double.parseDouble(specialtyPrice.getText().toString());
                new_pizza.setPrice(price);

                my_pizzas.add(new_pizza);

                current_order = Order.getInstance();
                current_order.addPizza(new_pizza);

                if (quantity == 1 && countAlert < 1) {
                    buildAlert("\n" + new_pizza.toString() + "\nThis pizza was added to your order!" + "\nHere is your complete order: " + current_order.allOrdersToString());
                    countAlert++;
                } else if (quantity > 1 && i == (quantity - 1) && countAlert < 1){
                    buildAlert("\n" + quantity + " " + new_pizza.toString() + " of these pizzas were added to your order!" + "\nHere is your complete order: " + current_order.allOrdersToString());
                    countAlert++;
                }
            }





        }




    }


    private boolean selectedExtraCheese(){
        specialtyExtraCheese = findViewById(R.id.specialtyExtraCheese);
        return specialtyExtraCheese.isChecked();
    }

    private boolean selectedExtraSauce(){
        specialtyExtraSauce = findViewById(R.id.specialtyExtraSauce);
        return specialtyExtraSauce.isChecked();
    }

    private Size selectedSize(){
        specialtySize = findViewById(R.id.specialtySize);
        int selectedSizeId = specialtySize.getCheckedRadioButtonId();
        RadioButton selectedSize = findViewById(selectedSizeId);

        if (selectedSize != null){
            String sizeName = selectedSize.getText().toString();
            if (sizeName.equals("Small")){
                return Size.SMALL;
            } else if (sizeName.equals("Medium")){
                return Size.MEDIUM;
            } else if (sizeName.equals("Large")){
                return Size.LARGE;
            }
        }
        return null;
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
        alert.setNegativeButton("Close", (dialog, id) -> dialog.cancel());
        alert.show();
    }


    private void setIDs(){
        specialtyImage = findViewById(R.id.specialtyImage);
        specialtySize = findViewById(R.id.specialtySize);
        specialtyAdd = findViewById(R.id.specialtyAdd);
        specialtyExtraCheese = findViewById(R.id.specialtyExtraCheese);
        specialtyExtraSauce = findViewById(R.id.specialtyExtraSauce);
    }

    private void setSpinnerIDs(){
        List<Integer> pizzaQuantities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            pizzaQuantities.add(i);
        }

        Spinner specialtyQty = findViewById(R.id.specialtyQty);
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaQuantities);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialtyQty.setAdapter(spinnerAdapter);
    }
}

