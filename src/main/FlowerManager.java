package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages a collection of flowers in the flower shop.
 * Allows adding, updating, removing, and searching for flowers.
 * Inherits from the Manager class.
 * 
 * @author Flower Shop
 */
public class FlowerManager extends Manager {
    private HashMap<String, Flower> list = new HashMap<>();

    /**
     * Initializes the FlowerManager with some predefined flowers
     */
    public FlowerManager() {
        String[] db = Lib.readFile(new File("./database/flower.txt")).split("\n");

        for (String line : db) {
            String[] paths = line.split("\\|");
            if (paths.length > 1) {
                list.put(paths[0], new Flower(paths[0], paths[1], paths[2], Double.parseDouble(paths[3]), Integer.parseInt(paths[4])));
            }
        }
    }

    /**
     * Adds a new flower to the collection
     * @return A message indicating the operation's success
     */
    @Override
    public String add() {
        String id = inputID();
        String name = inputName();
        String type = inputType();
        double price = inputPrice();
        int amount = inputAmount();

        list.put(id, new Flower(id, name, type, price, amount));
        return "Flower has been added";
    }

    /**
     * Updates an existing flower's details
     * @return A message indicating whether the update was successful
     */
    @Override
    public String update() {
        if (list.isEmpty()) {
            return "No flower found in shop";
        }

        String id = inputExistingID();
        String name = inputName();
        String type = inputType();
        double price = inputPrice();
        int amount = inputAmount();

        list.put(id, new Flower(id, name, type, price, amount));
        return "Flower has been updated";
    }

    /**
     * Removes a flower from the collection
     * @return A message indicating whether the removal was successful
     */
    @Override
    public String remove() {
        if (list.isEmpty()) {
            return "No flower found in shop";
        }

        String id = inputExistingID();
        list.remove(id);
        return "Flower has been removed";
    }

    /**
     * Searches for flowers that match the given keyword
     * @return A formatted string containing search results or an appropriate message
     */
    @Override
    public String search() {
        if (list.isEmpty()) {
            return "No flower found in shop";
        }

        String keyword = inputKeyword();
        StringBuilder res = new StringBuilder();
        res.append(String.format("|%5s|%10s|%20s|%10s|%10s|%10s|\n", "No.", "ID", "Name", "Type", "Price", "Amount"));

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
            return "No flower matches with your keyword";
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Returns a formatted string representing all flowers in the system
     * @return A string containing all flower details
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return "No flower found in shop";
        }

        StringBuilder res = new StringBuilder();
        res.append(String.format("There are %d flowers in system\n|%5s|%10s|%20s|%10s|%10s|%10s|\n", list.size(), "No.", "ID", "Name", "Type", "Price", "Amount"));

        int i = 0;
        for (String item : list.keySet()) {
            Flower thisFlower = list.get(item);
            res.append(String.format("|%5s|%s\n", ++i, thisFlower.toString()));
        }

        return res.toString().substring(0, res.toString().length() - 1);
    }

    /**
     * Prompts the user to input a unique flower ID
     * Ensures the ID does not already exist
     * @return The validated flower ID
     */
    public String inputID() {
        String id;
        boolean isValid;

        do {
            id = Lib.inputString(
                true,
                "Flower ID: ",
                "Flower ID must be a valid string\n",
                1,
                "Flower ID must not be empty\n",
                Integer.MAX_VALUE,
                "Flower ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Flower ID must contain only letters and numbers"
            ).toUpperCase();

            isValid = !list.containsKey(id);
            if (!isValid) {
                System.out.println("Flower ID duplicated");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Prompts the user to input an existing flower ID
     * Ensures the ID exists in the system
     * @return The validated flower ID
     */
    public String inputExistingID() {
        String id;
        boolean isValid;

        do {
            id = Lib.inputString(
                true,
                "Flower ID: ",
                "Flower ID must be a valid string\n",
                1,
                "Flower ID must not be empty\n",
                Integer.MAX_VALUE,
                "Flower ID must be a valid string\n",
                "^[a-zA-Z0-9]+$",
                "Flower ID must contain only letters and numbers"
            ).toUpperCase();

            isValid = list.containsKey(id);
            if (!isValid) {
                System.out.println("Flower ID not found");
            }
        } while (!isValid);

        return id;
    }

    /**
     * Prompts the user to input a flower name
     * @return The validated flower name
     */
    public String inputName() {
        return Lib.nameReformat(Lib.inputString(
            true,
            "Flower name: ",
            "Flower name must be a valid string\n",
            1,
            "Flower name must not be empty\n",
            Integer.MAX_VALUE,
            "Flower name must be a valid string\n",
            "^[a-zA-Z0-9 ]+$",
            "Flower name must contain only letters, numbers, and spaces"
        ));
    }

    /**
     * Prompts the user to input a flower type
     * @return The validated flower type
     */
    public String inputType() {
        return Lib.inputString(
            true,
            "Flower type: ",
            "Flower type must be a valid string\n",
            1,
            "Flower type must not be empty\n",
            Integer.MAX_VALUE,
            "Flower type must be a valid string\n",
            "^[a-zA-Z0-9 ]+$",
            "Flower type must contain only letters, numbers, and spaces"
        );
    }

    /**
     * Prompts the user to input a flower price
     * @return The validated flower price
     */
    public double inputPrice() {
        return Lib.inputDouble(
            true,
            "Flower price: ",
            "Flower price must be a valid number\n",
            0,
            "Flower price must be equal to or greater than 0\n",
            Integer.MAX_VALUE,
            "Flower price must be a valid number\n"
        );
    }

    /**
     * Prompts the user to input a flower amount
     * @return The validated flower amount
     */
    public int inputAmount() {
        return Lib.inputInteger(
            true,
            "Flower amount: ",
            "Flower amount must be a valid integer\n",
            0,
            "Flower amount must be equal to or greater than 0\n",
            Integer.MAX_VALUE,
            "Flower amount must be a valid integer\n"
        );
    }

    /**
     * Prompts the user to input a search keyword
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
            "Keyword must contain only letters, numbers, and spaces\n"
        );
    }

    /**
     * Check exist ID in list
     * @return Exist or not
     */
    @Override
    public boolean isExistID(String id) {
        return list.containsKey(id);
    }

    /**
     * Check empty list
     * @return Empty or not
     */
    @Override
    public boolean isEmptyList() {
        return list.isEmpty();
    }

    /**
     * Get flower object by ID
     * @param id Flower ID
     * @return Flower object
     */
    public Flower getFlower(String id) {
        return list.get(id);
    }

    /**
     * Save data to file database
     */
    @Override
    public void save() {
        StringBuilder content = new StringBuilder();
        for (Flower item : list.values()) {
            content.append(String.format("%s|%s|%s|%.3f|%d\n", item.getID(), item.getName(), item.getType(), item.getPrice(), item.getAmount()));
        }

        try (FileWriter writer = new FileWriter("./database/flower.txt")) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
