package main;

/**
 * Represents an Employee in the Employee Management System
 * Each employee has a unique ID and a name
 * Provides basic getter and setter methods for modifying and retrieving employee details
 * 
 * @author Employee Management System
 */
public class Employee {
    private String id;  // Employee ID
    private String name; // Employee Name

    /**
     * Constructor to initialize an employee with an ID and name
     * @param id   The unique identifier for the employee
     * @param name The name of the employee
     */
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Sets the name of the employee
     * @param name The new name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the employee's id
     * @return The employee's id
     */
    public String getID() {
        return id;
    }

    /**
     * Retrieves the employee's name
     * @return The employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a formatted string representation of the employee
     * @return A formatted string containing the employee ID and name
     */
    @Override
    public String toString() {
        return String.format("%-10s|%-20s|", id, name);
    }
}
