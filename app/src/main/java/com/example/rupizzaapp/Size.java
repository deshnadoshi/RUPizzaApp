package com.example.rupizzaapp;

/**
 * Enum class to represent the Size.
 * @author Haejin Song, Deshna Doshi
 */
public enum Size {
    SMALL(0),
    MEDIUM(2),
    LARGE(4);

    private final int CODE;


    /**
     * Constructor for the Size enum.
     * @param code Integer corresponding to the size.
     */
    Size(int code) {
        this.CODE = code;
    }

    /**
     * Provides the code corresponding to the size.
     * @return Code (integer) of the size.
     */
    public int getCode() {
        return CODE;
    }
}
