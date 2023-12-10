package com.example.rupizzaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rupizzaapp.MyAdapter.OnItemClickListener;


import java.util.ArrayList;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        specialtyPizzaType = findViewById(R.id.specialtyPizzaType);
        specialtyPizzaType.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(getApplicationContext(), populateRecyclerView());
        specialtyPizzaType.setAdapter(adapter);
        specialtyImage = findViewById(R.id.specialtyImage);
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = specialtyPizzaType.getChildAdapterPosition(view);
                Item selectedItem = populateRecyclerView().get(position);
                String pizzaName = selectedItem.getPizzaName();
                setPizzaImage(pizzaName);
            }
        });

    }

    private ArrayList<Item> populateRecyclerView(){
        ArrayList<Item> allSpecialtyPizzas = new ArrayList<>();
        allSpecialtyPizzas.add(new Item("Deluxe", "Tomato Sauce; Sausage, Pepperoni, GreenPepper, Onion, Mushroom"));
        allSpecialtyPizzas.add(new Item("Hawaiian", "Alfredo Sauce; Pineapple, BlackOlives, Ham, Pepperoni"));
        allSpecialtyPizzas.add(new Item("Meatzza", "Tomato Sauce; Sausage, Pepperoni, Beef, Ham"));
        allSpecialtyPizzas.add(new Item("Mediterranean", "Tomato Sauce; BlackOlives, Onion, Mushroom, Chicken"));
        allSpecialtyPizzas.add(new Item("Pepperific", "Tomato Sauce; GreenPepper, Pepperoni, Sausage, Mushroom"));
        allSpecialtyPizzas.add(new Item("Pepperoni", "Tomato Sauce; Pepperoni"));
        allSpecialtyPizzas.add(new Item("Seafood", "Alfredo Sauce; Shrimp, Squid, CrabMeats"));
        allSpecialtyPizzas.add(new Item("Supreme", "Tomato Sauce; Sausage, Pepperoni, Ham, GreenPepper, Onion, BlackOlives, Mushroom"));
        allSpecialtyPizzas.add(new Item("Tropizest", "Alfredo Sauce; Pineapple, BlackOlives, Ham, Pepperoni"));
        allSpecialtyPizzas.add(new Item("Veggievista", "Tomato Sauce; GreenPepper, Onion, BlackOlives, Mushroom"));
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
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (resourceId != 0)
            specialtyImage.setImageResource(resourceId);
    }

}
