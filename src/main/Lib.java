package main;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Flower shop
 */
public class Lib {
        // Scanner for keyboard input
    private static Scanner in = new Scanner(System.in);

    /**
     * Validates and returns an double input within specified bounds
     * @param loop If true, continues asking for input until valid; if false, returns after first attempt
     * @param label Prompt message displayed before input
     * @param exception Error message for invalid number format
     * @param min Minimum acceptable value
     * @param minException Error message when input is below minimum
     * @param max Maximum acceptable value
     * @param maxException Error message when input is above maximum
     * @return A validated double within the specified range
     */
    public static double inputDouble(boolean loop, String label, String exception, int min, String minException, int max, String maxException) {
        // Track input validation status
        boolean valid = false;
        // Store input value
        double value = 0;

        do {
            // Handle potential parsing errors
            try {
                // Display prompt if provided
                if (!label.isEmpty()) {
                    System.out.print(label);
                }

                // Read and parse user input
                value = Double.parseDouble(in.nextLine());

                // Validate against minimum bound
                if (value < min) {
                    System.out.print(minException);
                // Validate against maximum bound
                } else if (value > max) {
                    System.out.print(minException);
                // Input is valid
                } else {
                    valid = true;
                }

                // Exit loop if not in continuous mode
                if (!loop) {
                    valid = true;
                }
            } catch (Exception ex) {
                // Display error message if provided
                if (!exception.isEmpty()) {
                    System.out.print(exception);
                }
            }
        // Continue until valid input received
        } while (!valid);

        return value;
    }

    /**
     * Validates and returns an integer input within specified bounds
     * @param loop If true, continues asking for input until valid; if false, returns after first attempt
     * @param label Prompt message displayed before input
     * @param exception Error message for invalid number format
     * @param min Minimum acceptable value
     * @param minException Error message when input is below minimum
     * @param max Maximum acceptable value
     * @param maxException Error message when input is above maximum
     * @return A validated integer within the specified range
     */
    public static int inputInteger(boolean loop, String label, String exception, int min, String minException, int max, String maxException) {
        // Track input validation status
        boolean valid = false;
        // Store input value
        int value = 0;

        do {
            // Handle potential parsing errors
            try {
                // Display prompt if provided
                if (!label.isEmpty()) {
                    System.out.print(label);
                }

                // Read and parse user input
                value = Integer.parseInt(in.nextLine());

                // Validate against minimum bound
                if (value < min) {
                    System.out.print(minException);
                // Validate against maximum bound
                } else if (value > max) {
                    System.out.print(minException);
                // Input is valid
                } else {
                    valid = true;
                }

                // Exit loop if not in continuous mode
                if (!loop) {
                    valid = true;
                }
            } catch (Exception ex) {
                // Display error message if provided
                if (!exception.isEmpty()) {
                    System.out.print(exception);
                }
            }
        // Continue until valid input received
        } while (!valid);

        return value;
    }

    /**
     * Validates and returns an string input within specified length bounds
     * @param loop If true, continues asking for input until valid; if false, returns after first attempt
     * @param label Prompt message displayed before input
     * @param exception Error message for invalid string format
     * @param min Minimum length acceptable value
     * @param minException Error message when input is below minimum length
     * @param max Maximum length acceptable value
     * @param maxException Error message when input is above maximum length
     * @param regex Regex string
     * @param regexException Error message when input not match regex
     * @return A validated string within the specified range
     */
    public static String inputString(boolean loop, String label, String exception, int min, String minException, int max, String maxException, String regex, String regexException) {
        // Track input validation status
        boolean valid = false;
        // Store input value
        String value = "";

        do {
            // Handle errors
            try {
                // Display prompt if provided
                if (!label.isEmpty()) {
                    System.out.print(label);
                }

                // Read and parse user input
                value = in.nextLine().trim();

                // Validate against minimum length bound
                if (value.length() < min) {
                    System.out.print(minException);
                // Validate against maximum length bound
                } else if (value.length() > max) {
                    System.out.print(minException);
                // Input is valid
                } else if (!regex.isEmpty() && !value.matches(regex)) {
                    System.out.print(regexException);
                } else {
                    valid = true;
                }

                // Exit loop if not in continuous mode
                if (!loop) {
                    valid = true;
                }
            } catch (Exception ex) {
                // Display error message if provided
                if (!exception.isEmpty()) {
                    System.out.print(exception + ex.getMessage());
                }
            }
        // Continue until valid input received
        } while (!valid);

        return value;
    }

    /**
     * Require input between yes or no
     * @param label Message before press
     * @param other Message of other input format
     * @return True if user choose yes, otherwise is false
     */
    public static boolean inputYoN(String label, String other) {
        while (true) {
            System.out.print(label);
            String choice = in.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.print(other);
            }
        }
    }

    /**
     * Input/validate file name
     * @param label Prompt message displayed before input
     * @param exception Error message for invalid string format
     * @param minException Error message when input is below minimum length
     * @param maxException Error message when input is above maximum length
     * @param endWithDotException Error message when input end with dot
     * @param missExtentionException Error message when input missed file extension
     * @return A validated file name within the specified range
     */
    public static String inputFileName(String label, String exception, String minException, String maxException, String endWithDotException, String missExtentionException) {
        while (true) {
            String value = Lib.inputString(
                true,
                label,
                exception,
                3,
                minException,
                255,
                maxException,
                "^[a-zA-Z0-9_.-]+$",
                exception
            );

            // Get file extension by name
            String extension =  "";
            if (value.contains(".")) {
                String[] dotSplit = value.split("\\.");
                extension = dotSplit[dotSplit.length - 1];
            }

            if (value.endsWith(".")) {
                System.out.print(endWithDotException);
                continue;
            } else if (extension.isEmpty()) {
                System.out.print(missExtentionException);
                continue;
            }

            return value;
        }
    }

    /**
     * Handler for reading file
     * @param file File obj to read
     * @return Target file content
     */
    public static String readFile(File file) {
        String result = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Append new line content
                result += scanner.nextLine() + "\n";
            }
        } catch (Exception e) {
            return "Error on file read: " + e.getMessage();
        }

        return (result.length() >= 1 ? result.substring(0, result.length() - 1) : "");
    }

    /**
     * Reformat given name
     *
     * @param name Name to reformat
     * @return A reformatted name
     */
    public static String nameReformat(String name) {
        String upper = name.toUpperCase();
        String result = "";
        for (int i = 0; i < name.length(); i++) {
            if (i == 0) {
                result += upper.charAt(i);
            } else if (name.charAt(i - 1) == 32) {
                result += upper.charAt(i);
            } else {
                result += name.charAt(i);
            }
        }

        return result;
    }

    /**
     * Get current date
     * @return Current date
     */
    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
