package com.example.rupizzaapp;


/**
 * Enum class to represent the Toppings.
 * @author Haejin Song, Deshna Doshi
 */
public enum Topping {
    GREENPEPPER(0),
    ONION(1),
    PINEAPPLE(2),
    BLACKOLIVES(3),
    MUSHROOM(4),
    SAUSAGE(5),
    CHICKEN(6),
    BEEF(7),
    HAM(8),
    CRABMEATS(9),
    PEPPERONI(10),
    SQUID(11),
    SHRIMP(12);

    private final int CODE;

    /**
     * Constructor for the Topping enum.
     * @param code Integer corresponding to the toppings.
     */
    Topping(int code) {
        this.CODE = code;
    }

    /**
     * Provides the code corresponding to the topping.
     * @return Code (integer) of the topping.
     */
    public int getCode() {
        return CODE;
    }
}
