package com.example.rupizzaapp;

import java.util.ArrayList;

/**
 * Set-up for the BuildYourOwn Pizza.
 * @author Haejin Song, Deshna Doshi
 */
public class BuildYourOwn extends Pizza {
    private double pizza_price;

    /**
     * Constructor to create a BuildYourOwn Pizza.
     * @param size Size of the pizza.
     * @param sauce Sauce on the pizza.
     * @param extraSauce True if extra sauce is selected, false otherwise.
     * @param extraCheese True if extra cheese is selected, false otherwise.
     * @param toppings The toppings on the pizza.
     */
    public BuildYourOwn(Size size, Sauce sauce, boolean extraSauce, boolean extraCheese, ArrayList<Topping> toppings) {
        super(size, sauce, extraSauce, extraCheese);
        this.toppings = toppings;
    }

    /**
     * Setter for the pizza size.
     * @param size Size of the pizza.
     */
    public void setPizzaSize(Size size) {
        this.size = size;
    }

    /**
     * Setter for the pizza sauce.
     * @param sauce Sauce on the pizza.
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Setter for the boolean extraSauce.
     * @param addSauce True if extra sauce is selected, false otherwise.
     */
    public void setExtraSauce(boolean addSauce) {
        extraSauce = addSauce;
    }

    /**
     * Setter for the boolean extraCheese.
     * @param addCheese True if extra chees is selected, false otherwise.
     */
    public void setExtraCheese(boolean addCheese) {
        extraCheese = addCheese;
    }

    /**
     * Set the toppings of the pizza.
     * @param toppings ArrayList of the Toppings on the pizza.
     */
    @Override
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * toString() that transcribes the extra sauce and extra cheese parameters.
     * @return String of the extras chosen.
     */
    public String extrasToString(){
        if (extraCheese && extraSauce){
            return "extra sauce, extra cheese";
        } else if (extraCheese){
            return "extra cheese";
        } else if (extraSauce){
            return "extra sauce";
        }

        return "";
    }

    /**
     * Setter for the price of the pizza.
     * @param amount The price of the pizza.
     */
    public void setPrice(double amount){
        pizza_price = amount;
    }

    /**
     * Calculates the price of the pizza.
     * @return Price of the pizza based on the size.
     */
    @Override
    public double price() {
        double extraPrice = 0;
        if (extraCheese){
            extraPrice += 1;
        }
        if (extraSauce){
            extraPrice += 1;
        }

        if (toppings != null) {
            if (toppings.size() <= 3)
                return Double.parseDouble(String.format("%.2f", 8.99 + size.getCode() + extraPrice));
            else
                return Double.parseDouble(String.format("%.2f", 8.99 + size.getCode() + ((toppings.size() - 3) * 1.49) + extraPrice));
        }

        return Double.parseDouble(String.format("%.2f", 8.99 + size.getCode() + extraPrice));
    }


    /**
     * Calculates the price based on the size of the pizza.
     * @return The price of the pizza.
     */
    public double advancedPrice(){
        return Double.parseDouble(String.format("%.2f", 8.99 + size.getCode()));
    }

    /**
     * toString() that transcribes the toppings.
     * @return String of the toppings chosen.
     */
    public String toStringToppings(ArrayList <Topping> all_toppings){
        String retString = "";
        for (Topping allTopping : all_toppings) {
            retString += (allTopping.toString() + ", ");
        }
        return retString;
    }

    /**
     * Converts the pizza information into a String.
     * @return Pizza description.
     */
    public String toString(){
        return "[Build Your Own] " + toStringToppings(toppings) + size.toString().toLowerCase() + ", " +
                sauce.toString().toLowerCase() + ", " + extrasToString() + " $" + pizza_price;
    }

    @Override
    public String specialtyPrintTopping() {
        return null;
    }
}
