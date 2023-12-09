package com.example.rupizzaapp;

public class Item {
    String pizzaName;
    String toppings;

    public Item(String pizzaName, String toppings){
        this.pizzaName = pizzaName;
        this.toppings = toppings;
    }

    public String getPizzaName(){
        return pizzaName;
    }

    public String getToppings(){
        return toppings;
    }
}
