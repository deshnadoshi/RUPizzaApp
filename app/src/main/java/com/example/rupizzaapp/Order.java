package com.example.rupizzaapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Creates a Pizza Order.
 * @author Haejin Song, Deshna Doshi
 */
public class Order {
    private int orderNumber;
    private static Order orderObject;
    private ArrayList <Pizza> all_orders;

    /**
     * Constructor for a Pizza Order
     */
    private Order(){
        StoreOrders storeOrders = StoreOrders.getInstance();
        orderNumber = storeOrders.getNextOrderNumber();
        all_orders = new ArrayList<Pizza>();
    }

    /**
     * Retrieves the instance of an Order.
     * @return an Order.
     */
    public static Order getInstance() {
        if (orderObject == null) {
            orderObject = new Order();
        }
        return orderObject;
    }

    /**
     * Add a pizza to an order.
     * @param new_order The Pizza object to be added to an order.
     */
    public void addPizza(Pizza new_order){
        all_orders.add(new_order);
    }

    /**
     * Remove a pizza from an order.
     * @param index The index of the pizza to be removed.
     */
    public void removePizza(int index){
        all_orders.remove(index);
    }

    /**
     * Returns the order number of the pizza.
     * @return a String representing the order number.
     */
    public String getOrderNumber(){
        return String.valueOf(orderNumber);
    }

    /**
     * Find a pizza based on the type of pizza.
     * @param pizza a String describing the pizza.
     * @return -1 if not found, the index if found.
     */
    public int findPizza(String pizza) {
        for (int i = 0; i < all_orders.size(); i++) {
            if (pizza.equals(all_orders.get(i).toString())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Provides all of the orders in a pizza.
     * @return an ArrayList of Pizzas.
     */
    public ArrayList<Pizza> getAllOrders() {
        return all_orders;
    }

    /**
     * Delete an order.
     */
    public void deleteOrder() {
        orderObject = null;
    }


    /**
     * Creates an ObservableList with all of the pizzas in an order.
     * @return an ObservableList of <String> with pizza information.
     */
    public ObservableList<String> toStringArray() {
        ObservableList<String> stringList = FXCollections.observableArrayList();
        for (int i = 0; i < this.all_orders.size(); i++) {
            stringList.add(String.valueOf(this.all_orders.get(i)));
        }
        return stringList;
    }

    /**
     * Prints all of the orders.
     * @return a String that represents all of the orders.
     */
    public String allOrdersToString(){
        String retString = "";
        for (int i = 0; i < all_orders.size(); i++){
            retString += (all_orders.get(i).toString() + "\n") ;
        }

        return retString;
    }

}
