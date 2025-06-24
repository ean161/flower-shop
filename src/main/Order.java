package main;

import java.util.HashMap;

/**
 * Represents an order in the flower shop.
 * Each order has an ID, creation date, buyer, seller, and a list of ordered flowers.
 * It also maintains a price map for each flower in the order.
 * 
 * @author Flower shop
 */
public class Order {
    private String id, createdDate;
    private Customer buyer;
    private Employee seller;
    private HashMap<Flower, Integer> list = new HashMap<>(); 
    private HashMap<String, Double> priceMap = new HashMap<>(); 

    /**
     * Constructor to create a new Order
     * @param id        The unique identifier for the order
     * @param buyer     The customer who placed the order
     * @param seller    The employee who processed the order
     * @param list      A map of flowers and their quantities in the order
     * @param priceMap  A map of flower IDs and their prices at the time of the order
     */
    public Order(String id, Customer buyer, Employee seller, HashMap<Flower, Integer> list, HashMap<String, Double> priceMap) {
        this.id = id;
        this.createdDate = Lib.getCurrentDate(); // Automatically sets the order creation date
        this.buyer = buyer;
        this.seller = seller;
        this.list = list;
        this.priceMap = priceMap;
    }

    /**
     * Gets the unique order ID
     * @return The order ID as a string
     */
    public String getID() {
        return id;
    }

    /**
     * Generates a formatted list of items in the order
     * @return A formatted string listing all items with their quantity and total cost per item
     */
    public String getItems() {
        StringBuilder res = new StringBuilder();

        for (Flower item : list.keySet()) {
            res.append(String.format(
                "\t\t%s: %.3f x %d = %.3f\n",
                item.getName(),                    // Flower name
                priceMap.get(item.getID()),        // Price per unit at purchase time
                list.get(item),                    // Quantity ordered
                priceMap.get(item.getID()) * list.get(item) // Total price per item
            ));
        }

        // Ensure the string is not empty before applying substring
        return res.length() > 0 ? res.substring(0, res.length() - 1) : res.toString();
    }

    /**
     * Calculates the total price of the order
     * @return The total price of all flowers in the order
     */
    public double getTotalPrice() {
        double res = 0;

        for (Flower item : list.keySet()) {
            res += priceMap.get(item.getID()) * list.get(item); // Multiply price per flower by quantity
        }

        return res;
    }

    /**
     * Generates a formatted string representation of the order
     * @return A string detailing order information including customer, seller, item list, and total cost
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append(String.format(
            "Order #%s:\n"
            + "\t- Created at: %s\n"
            + "\t- Customer: %s\n"
            + "\t- Seller: %s\n"
            + "\t- List of items:\n%s\n"
            + "\t- Total cost: %.3f",
            id,                    // Order ID
            createdDate,            // Order creation date
            buyer.getName(),        // Customer name
            seller.getName(),       // Seller name
            getItems(),             // Ordered items list
            getTotalPrice()         // Total order cost
        ));

        return res.toString();
    }
}
