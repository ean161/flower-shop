package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
/**
 * Manages orders in the flower shop, including creating, searching, and exporting orders.
 * It interacts with FlowerManager, EmployeeManager, and CustomerManager to process orders.
 * 
 * @author Flower shop
 */
public class OrderManager {
    private HashMap<String, Order> list = new HashMap<>();
    private FlowerManager flowerManager;
    private EmployeeManager employeeManager;
    private CustomerManager customerManager;

    /**
     * Constructs an OrderManager with references to other managers
     * @param flowerManager    Manages available flowers
     * @param employeeManager  Manages employees
     * @param customerManager  Manages customers
     */
    public OrderManager(FlowerManager flowerManager, EmployeeManager employeeManager, CustomerManager customerManager) {
        this.flowerManager = flowerManager;
        this.employeeManager = employeeManager;
        this.customerManager = customerManager;
    }

    /**
     * Creates a new order if employees, customers, and flowers exist in the system
     * @return A message indicating whether the order was created successfully or why it failed
     */
    public String create() {
        if (employeeManager.isEmptyList()) {
            return "Cant create order while no employee found in system";
        } else if (customerManager.isEmptyList()) {
            return "Cant create order while no customer found in system";
        } else if (flowerManager.isEmptyList()) {
            return "Cant create order while no flower found in system";
        }

        String id = inputID();
        String sellerID = inputSeller();
        String buyerID = inputBuyer();
        HashMap<Flower, Integer> items = inputItems();

        HashMap<String, Double> priceMap = new HashMap<>();
        for (Flower item : items.keySet()) {
            priceMap.put(item.getID(), item.getPrice());
        }

        list.put(id, new Order(id, customerManager.getCustomer(buyerID), employeeManager.getEmployee(sellerID), items, priceMap));
        return String.format("%s\nOrder has been added", export(id));
    }

    /**
     * Searches for orders that match a given keyword
     * @return A formatted string containing matching orders or a message if no matches are found
     */
    public String search() {
        if (list.isEmpty()) {
            return "No order found in shop";
        }

        String keyword = inputKeyword();

        StringBuilder res = new StringBuilder();
        int iFound = 0;
        int i = 0;
        for (String item : list.keySet()) {
            Order thisOrder = list.get(item);
            if (thisOrder.toString().toLowerCase().contains(keyword.toLowerCase())) {
                iFound++;
                res.append(String.format("%d. %s\n", ++i, thisOrder.toString()));
            }
        }

        if (iFound == 0) {
            return "No order matches with your keyword";
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Returns a formatted string representation of all orders
     * @return A string listing all orders or a message if no orders exist
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "No order found in shop";
        }

        StringBuilder res = new StringBuilder();
        res.append(String.format("There are %d order found\n", list.size()));

        int i = 0;
        for (String item : list.keySet()) {
            Order thisOrder = list.get(item);
            res.append(String.format("%d. %s\n", ++i, thisOrder.toString()));
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Takes user input for a unique order ID
     * Ensures the ID is alphanumeric and not already used
     * @return The validated order ID
     */
    public String inputID() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Order ID: ",
                "Order ID must be a valid string\n",
                1,
                "Order ID must be not empty\n",
                Integer.MAX_VALUE,
                "Order ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Order ID must be just contains alpha and number\n"
            ).toUpperCase();

            isValid = true;
            if (list.containsKey(id)) {
                isValid = false;
                System.out.println("Order ID duplicated");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Takes user input for an employee ID and ensures the employee exists
     * @return The validated employee ID
     */
    public String inputSeller() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Employee ID: ",
                "Employee ID must be a valid string\n",
                1,
                "Employee ID must be not empty\n",
                Integer.MAX_VALUE,
                "Employee ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Employee ID must be just contains alpha and number\n"
            ).toUpperCase();

            isValid = true;
            if (!employeeManager.isExistID(id)) {
                isValid = false;
                System.out.println("Employee ID not found");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Takes user input for a customer ID and ensures the customer exists
     * @return The validated customer ID
     */
    public String inputBuyer() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Customer ID: ",
                "Customer ID must be a valid string\n",
                1,
                "Customer ID must be not empty\n",
                Integer.MAX_VALUE,
                "Customer ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Customer ID must be just contains alpha and number\n"
            ).toUpperCase();

            isValid = true;
            if (!customerManager.isExistID(id)) {
                isValid = false;
                System.out.println("Customer ID not found");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Takes user input for a search keyword
     * @return The input keyword
     */
    public String inputKeyword() {
        return Lib.inputString(
            true,
            "Search keyword: ",
            "Keyword must be a valid string\n",
            1,
            "Keyword must be not empty\n",
            Integer.MAX_VALUE,
            "Keyword must be a valid string\n",
            "^[a-zA-Z0-9/ ]+$",
            "Keyword must be just contains alpha, number and space\n"
        );
    }

    /**
     * Input flower amount 
     * @param flowerID Folower ID
     * @return Valid amount
     */
    public int inputItemAmount(String flowerID) {
        int amount;
        int remaining = flowerManager.getFlower(flowerID).getAmount();
        
        boolean isValid;
        do {
            amount = Lib.inputInteger(
                true,
                "Flower amount: ",
                "Flower amount must be a valid integer\n",
                1,
                "Flower amount must be not empty\n",
                Integer.MAX_VALUE,
                "Flower amount must be a valid integer\n"
            );

            isValid = true;
            if (remaining < amount) {
                isValid = false;
                System.out.printf("Not enough flower (%d remaining)\n", remaining);
            }
        } while (!isValid);

        return amount;
    }

    /**
     * Input list of item
     * @return Map of item choice
     */
    public HashMap<Flower, Integer> inputItems() {
        HashMap<Flower, Integer> items = new HashMap<>();

        do {
            String flowerID = inputFlower();
            int flowerAmount = inputItemAmount(flowerID);

            Flower flowerObj = flowerManager.getFlower(flowerID);
            if (!items.containsKey(flowerObj)) {
                items.put(flowerObj, flowerAmount);
            } else {
                System.out.println("This flower cant be add into order because it was added before");
            }
        } while (Lib.inputYoN(
            "Do you want to add more (y/n)? ",
            "Choice must be y or n\n"
        ));

        return items;
    }

    /**
     * Takes user input for a flower ID and ensures the flower exists and is in stock
     * @return The validated flower ID
     */
    public String inputFlower() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Flower ID: ",
                "Flower ID must be a valid string\n",
                1,
                "Flower ID must be not empty\n",
                Integer.MAX_VALUE,
                "Flower ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Flower ID must be just contains alpha and number\n"
            ).toUpperCase();

            isValid = true;
            
            if (!flowerManager.isExistID(id)) {
                isValid = false;
                System.out.println("Flower ID not found");
            } else {
                int amountRemaining = flowerManager.getFlower(id).getAmount();
                if (amountRemaining <= 0) {
                    isValid = false;
                    System.out.println("This flower is out of stock");
                }
            }
        } while (!isValid);

        return id;
    }

    /**
     * Exports an order to a text file
     * @param id The ID of the order to export
     * @return A message indicating where the order was saved
     */
    public String export(String id) {
        try (FileWriter writer = new FileWriter(String.format("./orders/%s.txt", id))) {
            writer.write(list.get(id).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("Order has been exported at orders/%s.txt", id);
    }
}
