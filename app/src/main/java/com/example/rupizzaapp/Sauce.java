package com.example.rupizzaapp;

/**
 * Enum class to represent the Sauce.
 * @author Haejin Song, Deshna Doshi
 */
public enum Sauce {
    TOMATO(0),
    ALFREDO(1);

    private final int CODE;

    /**
     * Constructor for the Sauce enum.
     * @param code Integer corresponding to the sauce type.
     */
    Sauce(int code) {
        this.CODE = code;
    }

    /**
     * Provides the code corresponding to the sauce type.
     * @return Code (integer) of the sauce.
     */
    public int getCode() {
        return CODE;
    }


}
