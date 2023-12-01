package com.example.rupizzaapp;
import java.util.ArrayList;


/**
 * Abstract class to represent different types of Pizza.
 * @author Deshna Doshi, Haejin Song
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Constructor to define a Pizza.
     * @param size Size of pizza.
     * @param sauce Sauce on pizza.
     * @param extraSauce True if extra sauce is selected, false otherwise.
     * @param extraCheese True if extra cheese is selected, false otherwise.
     */
    public Pizza(Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * Default Pizza constructor.
     */
    public Pizza() {

    }

    /**
     * Setter for the pizza size.
     * @param size Size of the pizza.
     */
    public abstract void setPizzaSize(Size size);

    /**
     * Setter for the boolean extraSauce.
     * @param addSauce True if extra sauce is selected, false otherwise.
     */
    public abstract void setExtraSauce(boolean addSauce);

    /**
     * Setter for the boolean extraCheese.
     * @param addCheese True if extra cheese is selected, false otherwise.
     */
    public abstract void setExtraCheese(boolean addCheese);

    /**
     * Sets the sauce of the pizza.
     * @param sauce Sauce on the pizza.
     */
    public abstract void setSauce(Sauce sauce);

    /**
     * Calculates the price of the pizza.
     * @return Price of the pizza based on the size.
     */
    public abstract double price(); //polymorphism

    /**
     * Calculates the price based on the size of the pizza.
     * @return The price of the pizza.
     */
    public abstract double advancedPrice(); //polymorphism

    /**
     * Set the toppings of the pizza.
     * @param toppings ArrayList of the Toppings on the pizza.
     */
    public abstract void setToppings(ArrayList<Topping> toppings);

    /**
     * Setter for the price of the pizza.
     * @param amount The price of the pizza.
     */
    public abstract void setPrice(double amount);

    /**
     * Converts the pizza information into a String.
     * @return Pizza description.
     */
    @Override
    public abstract String toString();


}
