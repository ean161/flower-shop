package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages customer records in the Customer Management System.
 * Provides functionalities to add, update, remove, search, and retrieve customer details.
 * Extends the Manager class.
 * 
 * @author Customer Management System
 */
public class CustomerManager extends Manager {
    private HashMap<String, Customer> list = new HashMap<>(); // Stores customers using their ID as the key

    /**
     * Constructor initializes the customer list with sample data
     */
    public CustomerManager() {
        String[] db = Lib.readFile(new File("./database/customer.txt")).split("\n");

        for (String line : db) {
            String[] paths = line.split("\\|");
            if (paths.length > 1) {
                list.put(paths[0], new Customer(paths[0], paths[1], paths[2]));
            }
        }
    }

    /**
     * Adds a new customer to the list
     * @return Success message after adding the customer
     */
    @Override
    public String add() {
        String id = inputID();
        String name = inputName();
        String phone = inputPhone();

        list.put(id, new Customer(id, name, phone));
        return "Customer has been added";
    }

    /**
     * Updates an existing customer's information
     * @return Success message after updating the customer
     */
    @Override
    public String update() {
        if (list.isEmpty()) {
            return "No customer found in system";
        }
        
        String id = inputExistingID();
        String name = inputName();
        String phone = inputPhone();

        list.put(id, new Customer(id, name, phone));
        return "Customer has been updated";
    }

    /**
     * Removes a customer from the list
     * @return Success message after removing the customer
     */
    @Override
    public String remove() {
        if (list.isEmpty()) {
            return "No customer found in system";
        }
        
        String id = inputExistingID();
        list.remove(id);
        return "Customer has been removed";
    }

    /**
     * Searches for customers based on a keyword
     * @return A formatted string of matching customer details or a message if no match is found
     */
    @Override
    public String search() {
        if (list.isEmpty()) {
            return "No customer found in system";
        }

        String keyword = inputKeyword();

        StringBuilder res = new StringBuilder();
        res.append(String.format("|%5s|%10s|%20s|%15s|\n", "No.", "ID", "Name", "Phone"));

        int iFound = 0;
        int i = 0;
        for (String item : list.keySet()) {
            String thisInfo = list.get(item).toString();
            if (thisInfo.toLowerCase().contains(keyword.toLowerCase())) {
                iFound++;
                res.append(String.format("|%5s|%s\n", ++i, thisInfo));
            }
        }

        if (iFound == 0) {
            return "No customer matches with your keyword";
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Displays all customers in the system
     * @return A formatted string of all customer details
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "No customer found in system";
        }

        StringBuilder res = new StringBuilder();
        res.append(String.format("There are %d customers in system\n|%5s|%10s|%20s|%15s|\n", list.size(), "No.", "ID", "Name", "Phone"));

        int i = 0;
        for (String item : list.keySet()) {
            Customer thisCustomer = list.get(item);
            res.append(String.format("|%5s|%s\n", ++i, thisCustomer.toString()));
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Prompts user to input a unique customer ID
     * Ensures the ID follows a valid format and is not duplicated
     * @return The validated customer ID
     */
    public String inputID() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Customer ID: ",
                "Customer ID must be a valid string\n",
                1,
                "Customer ID must not be empty\n",
                Integer.MAX_VALUE,
                "Customer ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Customer ID must only contain letters and numbers"
            ).toUpperCase();

            isValid = true;
            if (list.containsKey(id)) {
                isValid = false;
                System.out.println("Customer ID duplicated");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Prompts user to input an existing customer ID
     * Ensures the ID follows a valid format and exists in the system
     * @return The validated existing customer ID
     */
    public String inputExistingID() {
        String id;
        
        boolean isValid;
        do {
            id = Lib.inputString(
                true,
                "Customer ID: ",
                "Customer ID must be a valid string\n",
                1,
                "Customer ID must not be empty\n",
                Integer.MAX_VALUE,
                "Customer ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Customer ID must only contain letters and numbers"
            ).toUpperCase();

            isValid = true;
            if (!list.containsKey(id)) {
                isValid = false;
                System.out.println("Customer ID not found");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Prompts user to input a customer name
     * Ensures the name follows a valid format
     * @return The formatted customer name
     */
    public String inputName() {
        return Lib.nameReformat(Lib.inputString(
            true,
            "Customer name: ",
            "Customer name must be a valid string\n",
            1,
            "Customer name must not be empty\n",
            Integer.MAX_VALUE,
            "Customer name must be a valid string\n",
            "^[a-zA-Z0-9 ]+$",
            "Customer name must only contain letters, numbers, and spaces"
        ));
    }

    /**
     * Prompts user to input a phone number
     * Ensures the phone number contains only digits and has a valid length
     * @return The validated phone number
     */
    public String inputPhone() {
        return Lib.inputString(
            true,
            "Customer phone number: ",
            "Phone number must be a valid string\n",
            10,
            "Phone number must be at least 10 digits\n",
            11,
            "Phone number must not exceed 11 digits\n",
            "^[0-9]+$",
            "Phone number must contain only digits"
        );
    }

    /**
     * Prompts user to input a keyword for searching customers
     * Ensures the keyword follows a valid format
     * @return The validated search keyword
     */
    public String inputKeyword() {
        return Lib.inputString(
            true,
            "Search keyword: ",
            "Keyword must be a valid string\n",
            1,
            "Keyword must not be empty\n",
            Integer.MAX_VALUE,
            "Keyword must be a valid string\n",
            "^[a-zA-Z0-9 ]+$",
            "Keyword must only contain letters, numbers, and spaces\n"
        );
    }

    /**
     * Checks if a customer ID exists in the system
     * @param id The customer ID to check
     * @return True if the ID exists, false otherwise
     */
    @Override
    public boolean isExistID(String id) {
        return list.containsKey(id);
    }

    /**
     * Checks if the customer list is empty
     * @return True if no customers exist, false otherwise
     */
    @Override
    public boolean isEmptyList() {
        return list.isEmpty();
    }

    /**
     * Retrieves a customer by their ID
     * @param id The customer ID
     * @return The Customer object if found, otherwise null
     */
    public Customer getCustomer(String id) {
        return list.get(id);
    }

    /**
     * Save data to file database
     */
    @Override
    public void save() {
        StringBuilder content = new StringBuilder();
        for (Customer item : list.values()) {
            content.append(String.format("%s|%s|%s\n", item.getID(), item.getName(), item.getPhone()));
        }

        try (FileWriter writer = new FileWriter("./database/customer.txt")) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
