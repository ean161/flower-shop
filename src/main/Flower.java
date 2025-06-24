package main;

/**
 * Represents a flower in the Flower Shop system.
 * Stores details such as ID, name, type, price, and available amount.
 * Provides methods to get and update flower attributes.
 * 
 * @author Flower Shop
 */
public class Flower {
    private String id;     // Unique identifier for the flower
    private String name;   // Name of the flower
    private String type;   // Type/category of the flower
    private double price;  // Price per unit
    private int amount;    // Available quantity in stock

    /**
     * Constructor to initialize a Flower object
     * @param id     The unique identifier of the flower
     * @param name   The name of the flower
     * @param type   The type or category of the flower
     * @param price  The price of the flower
     * @param amount The available stock amount
     */
    public Flower(String id, String name, String type, double price, int amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    /**
     * Sets a new name for the flower
     * @param name The new name of the flower
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new type for the flower
     * @param type The new type of the flower
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets a new price for the flower
     * @param price The new price of the flower
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets a new amount for the flower stock
     * @param amount The new stock amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Retrieves the current stock amount of the flower
     * @return The stock amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Type getter
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the name of the flower
     * @return The flower name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the price of the flower
     * @return The flower price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retrieves the unique ID of the flower
     * @return The flower ID
     */
    public String getID() {
        return id;
    }

    /**
     * Returns a formatted string representation of the flower
     * @return A string containing the flower's details
     */
    @Override
    public String toString() {
        return String.format("%-10s|%-20s|%-10s|%10.3f|%10d|", id, name, type, price, amount);
    }
}
