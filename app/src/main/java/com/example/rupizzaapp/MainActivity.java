package com.example.rupizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Creates and manages the Main Menu Screen.
 * @author Deshna Doshi, Haejin Song
 */
public class MainActivity extends AppCompatActivity {

    private ImageView byomain;
    private ImageView coMain;
    private ImageView specialtyMain;
    private ImageView soMain;

    /**
     * Initializes the view for the Main Menu screen.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openBYO(savedInstanceState);
        openCurrentOrder(savedInstanceState);
        openStoreOrders(savedInstanceState);
        openSpecialtyPizzas(savedInstanceState);

    }

    /**
     * Handles a click on the Build Your Own Button.
     * @param savedInstanceState If the activity is being re-initialized after
     *      previously being shut down then this Bundle contains the data it most
     *      recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void openBYO(Bundle savedInstanceState){
        byomain = findViewById(R.id.byomain);
        byomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuildYourOwnActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Handles a click on the Current Order Button.
     * @param savedInstanceState If the activity is being re-initialized after
     *      previously being shut down then this Bundle contains the data it most
     *      recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void openCurrentOrder(Bundle savedInstanceState){
        coMain = findViewById(R.id.coMain);
        coMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Handles a click on the Store Orders Button.
     * @param savedInstanceState If the activity is being re-initialized after
     *      previously being shut down then this Bundle contains the data it most
     *      recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void openStoreOrders(Bundle savedInstanceState){
        soMain = findViewById(R.id.soMain);

        soMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Handles a click on the Specialty Pizzas Button.
     * @param savedInstanceState If the activity is being re-initialized after
     *      previously being shut down then this Bundle contains the data it most
     *      recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    protected void openSpecialtyPizzas(Bundle savedInstanceState){
        specialtyMain = findViewById(R.id.specialtyMain);

        specialtyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecialtyPizzasActivity.class);
                startActivity(intent);
            }
        });
    }




}