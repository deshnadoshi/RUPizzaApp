package com.example.rupizzaapp;

/**
 * Item class to handle RecyclerView in SpecialtyPizzasActivity
 * @author Deshna Doshi, Haejin Song
 */
public class Item {
    String pizzaName;
    String toppings;

    /**
     * Constructor for the items in the RecyclerView
     * @param pizzaName Name of the specialty pizza.
     * @param toppings Toppings on the specialty pizza.
     */
    public Item(String pizzaName, String toppings){
        this.pizzaName = pizzaName;
        this.toppings = toppings;
    }

    /**
     * Getter for the pizzaName instance variable.
     * @return Name of the pizza.
     */
    public String getPizzaName(){
        return pizzaName;
    }

    /**
     * Getter for the toppings instance variable.
     * @return List of the toppings on the pizza.
     */
    public String getToppings(){
        return toppings;
    }

    /**
     * Setter for the pizzaName instance variable.
     * @param pizzaName Name of the pizza.
     */
    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    /**
     * Setter for the toppings instance variable.
     * @param toppings List of the toppings on the pizza.
     */
    public void setToppings(String toppings) {
        this.toppings = toppings;
    }
}
