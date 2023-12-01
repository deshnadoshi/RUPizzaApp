package com.example.rupizzaapp;

/**
 * Creates an instance of the Pizza class.
 * @author Haejin Song, Deshna Doshi
 */
public class PizzaMaker {

    /**
     * Creates a pizza.
     * @param pizzaType String of the type of pizza to create.
     * @return Created Pizza object.
     */
    public static Pizza createPizza(String pizzaType) {
        Pizza pizza = null;
        if (pizzaType.equals("Deluxe")) {
            pizza = new Deluxe(Size.SMALL, null, false, false);
        } else if (pizzaType.equals("Meatzza")) {
            pizza = new Meatzza(Size.SMALL, null, false, false);
        } else if (pizzaType.equals("Pepperoni")) {
            pizza = new Pepperoni(Size.SMALL, null, false, false);
        } else if (pizzaType.equals("Seafood")) {
            pizza = new Seafood(Size.SMALL, null, false, false);
        } else if (pizzaType.equals("Supreme")) {
            pizza = new Supreme(Size.SMALL, null, false, false);
        } else if (pizzaType.equals("BuildYourOwn")) {
            pizza = new BuildYourOwn(Size.SMALL, null, false, false, null);
        } else {
            return null;
        }
        return pizza; // placeholder
    }
}
