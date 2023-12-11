package com.example.rupizzaapp;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Develops a system to track all of the store's orders.
 * @author Haejin Song, Deshna Doshi
 */
public class StoreOrders {
    private int nextOrderNumber = 0;
    private static StoreOrders storeOrderObject;
    private ArrayList <Order> store_orders = new ArrayList<>();

    /**
     * Constructor for StoreOrders()
     */
    private StoreOrders(){
        store_orders = new ArrayList<Order>();
    }

    /**
     * Provides an instance of a StoreOrders object.
     * @return a StoreOrders object.
     */
    public static StoreOrders getInstance() {
        if (storeOrderObject == null) {
            storeOrderObject = new StoreOrders();
        }
        return storeOrderObject;
    }

    /**
     * Provides the next available order number.
     * @return Integer of the next order number.
     */
    public int getNextOrderNumber() {
        nextOrderNumber += 1;
        return nextOrderNumber;
    }

    /**
     * Adds an order to the store's orders.
     * @param order New order to be added.
     */
    public void addOrder(Order order) {
        store_orders.add(order);
    }

    /**
     * Finds an order based on its index.
     * @param i The index of the order.
     * @return The Order object.
     */
    public Order findOrder(int i) {
        return store_orders.get(i);
    }

    /**
     * Deletes an order based on its index.
     * @param i The index of the order.
     */
    public void deleteOrder(int i) {
        store_orders.remove(i);
    }

    /**
     * Creates an ArrayList with all of the store's orders.
     * @return ArrayList of Order objects.
     */
    public ArrayList<Order> getStore_orders() {
        return store_orders;
    }

    /**
     * Creates an ArrayList with all of the store's order numbers.
     * @return ArrayList of order numbers in String.
     */
    public ArrayList<String> getOrderNumbers() {
        ArrayList<String> orderNumbers = new ArrayList<>();
        for (int i = 0; i < store_orders.size(); i++) {
            orderNumbers.add(store_orders.get(i).getOrderNumber());
        }
        return orderNumbers;
    }

    /**
     * Exports all of the orders to a text file.
     * @param all_orders The ArrayList of all of the orders.
     * @param file The file where the data will be stored.
     */
    public static void export(ArrayList <Order> all_orders, File file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int orderNum = 0;
            for (Order item : all_orders) {
                orderNum += 1;
                for (int i = 0; i < item.getAllOrders().size(); i++){


                    writer.write("#" + orderNum + ": " + item.getAllOrders().get(i).toString());
                    writer.newLine();
                }


            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
