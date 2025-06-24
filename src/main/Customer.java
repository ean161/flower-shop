package main;

/**
 * Represents a customer in the Customer Management System.
 * This class stores customer details such as ID, name, and phone number.
 * 
 * @author Customer Management System
 */
public class Customer {
    private String id;    // Unique identifier for the customer
    private String name;  // Customer's full name
    private String phone; // Customer's phone number

    /**
     * Constructor to initialize a new customer with an ID, name, and phone number
     * @param id    The unique ID of the customer
     * @param name  The name of the customer
     * @param phone The phone number of the customer
     */
    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Sets the customer's name
     * @param name The new name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the customer's phone number
     * @param phone The new phone number of the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the customer's name
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * ID getter
     * @return The ID of the customer
     */
    public String getID() {
        return id;
    }

    /**
     * Phone getter
     * @return The phone of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns a formatted string representation of the customer's details
     * @return A formatted string with customer ID, name, and phone number
     */
    @Override
    public String toString() {
        return String.format("%-10s|%-20s|%-15s|", id, name, phone);
    }
}
