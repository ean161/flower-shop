package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Manages employees in the Employee Management System.
 * Supports adding, updating, removing, and searching employees.
 * Uses a HashMap to store employee records.
 * 
 * @author Employee Management System
 */
public class EmployeeManager extends Manager {
    private HashMap<String, Employee> list = new HashMap<>();

    /**
     * Constructor initializes the employee list with sample data
     */
    public EmployeeManager() {
        String[] db = Lib.readFile(new File("./database/employee.txt")).split("\n");

        for (String line : db) {
            String[] paths = line.split("\\|");
            if (paths.length > 1) {
                list.put(paths[0], new Employee(paths[0], paths[1]));
            }
        }
    }

    /**
     * Adds a new employee to the system
     * @return A message confirming the employee was added
     */
    @Override
    public String add() {
        String id = inputID();
        String name = inputName();
        list.put(id, new Employee(id, name));
        return "Employee has been added.";
    }

    /**
     * Updates the name of an existing employee
     * @return A message confirming the update, or an error if no employees exist
     */
    @Override
    public String update() {
        if (list.isEmpty()) return "No employees found in system.";

        String id = inputExistingID();
        String name = inputName();
        
        Employee employee = list.get(id);
        employee.setName(name);  // Update name instead of replacing object
        return "Employee has been updated.";
    }

    /**
     * Removes an employee from the system by their ID
     * @return A message confirming removal or an error if no employees exist
     */
    @Override
    public String remove() {
        if (list.isEmpty()) return "No employees found in system.";
        
        String id = inputExistingID();
        list.remove(id);
        return "Employee has been removed.";
    }

    /**
     * Searches for employees by a keyword and returns a formatted list of matches
     * @return A string containing the search results or a message if no match is found
     */
    @Override
    public String search() {
        if (list.isEmpty()) return "No employees found in system.";

        String keyword = inputKeyword().toLowerCase();
        StringBuilder res = new StringBuilder();
        res.append(String.format("|%5s|%10s|%20s|\n", "No.", "ID", "Name"));

        int i = 0;
        for (Employee emp : list.values()) {
            if (emp.toString().toLowerCase().contains(keyword)) {
                res.append(String.format("|%5s|%s\n", ++i, emp));
            }
        }

        return (i == 0) ? "No employees match your keyword." : res.toString().trim();
    }

    /**
     * Displays all employees in the system in a formatted table
     * @return A string containing all employee records or a message if the list is empty
     */
    @Override
    public String toString() {
        if (list.isEmpty()) return "No employees found in system.";

        StringBuilder res = new StringBuilder();
        res.append(String.format("There are %d employees in the system:\n|%5s|%10s|%20s|\n", list.size(), "No.", "ID", "Name"));

        int i = 0;
        for (Employee emp : list.values()) {
            res.append(String.format("|%5s|%s\n", ++i, emp));
        }

        return res.toString().trim();
    }

    /**
     * Gets input for a new employee ID and ensures it is unique
     * @return A valid, unique employee ID
     */
    public String inputID() {
        String id;
        do {
            id = Lib.inputString(true, "Employee ID: ", "Invalid input.", 1, "Cannot be empty.", Integer.MAX_VALUE, "Invalid input.", "^[a-zA-Z0-9]+$", "Only letters and numbers allowed").toUpperCase();
            if (list.containsKey(id)) {
                System.out.println("Employee ID already exists.");
            } else {
                return id;
            }
        } while (true);
    }

    /**
     * Gets input for an existing employee ID and ensures it exists
     * @return A valid employee ID that exists in the system
     */
    public String inputExistingID() {
        String id;
        do {
            id = Lib.inputString(true, "Employee ID: ", "Invalid input.", 1, "Cannot be empty.", Integer.MAX_VALUE, "Invalid input.", "^[a-zA-Z0-9]+$", "Only letters and numbers allowed").toUpperCase();
            if (!list.containsKey(id)) {
                System.out.println("Employee ID not found.");
            } else {
                return id;
            }
        } while (true);
    }

    /**
     * Gets input for an employee's name and ensures it follows the naming rules
     * @return A valid employee name
     */
    public String inputName() {
        return Lib.nameReformat(Lib.inputString(
            true, "Employee name: ", "Invalid input.", 1, "Cannot be empty.", Integer.MAX_VALUE, "Invalid input.",
            "^[a-zA-Z0-9 ]+$", "Only letters, numbers, and spaces allowed"
        ));
    }

    /**
     * Gets input for a search keyword
     * @return A valid keyword for searching employees
     */
    public String inputKeyword() {
        return Lib.inputString(
            true, "Search keyword: ", "Invalid input.", 1, "Cannot be empty.", Integer.MAX_VALUE, "Invalid input.",
            "^[a-zA-Z0-9 ]+$", "Only letters, numbers, and spaces allowed"
        );
    }

    /**
     * Checks if a given employee ID exists in the system
     * @param id The employee ID to check
     * @return True if the ID exists, false otherwise
     */
    @Override
    public boolean isExistID(String id) {
        return list.containsKey(id);
    }

    /**
     * Checks if the employee list is empty
     * @return True if the list is empty, false otherwise
     */
    @Override
    public boolean isEmptyList() {
        return list.isEmpty();
    }

    /**
     * Retrieves an employee object by their ID
     * @param id The employee ID
     * @return The Employee object if found, otherwise null
     */
    public Employee getEmployee(String id) {
        return list.get(id);
    }

    /**
     * Save data to file database
     */
    @Override
    public void save() {
        StringBuilder content = new StringBuilder();
        for (Employee item : list.values()) {
            content.append(String.format("%s|%s\n", item.getID(), item.getName()));
        }

        try (FileWriter writer = new FileWriter("./database/employee.txt")) {
            writer.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
