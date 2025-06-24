package main;

import java.util.Scanner;

/**
 *
 * @author Flower shop
 */
public class Main {

    /**
     * The main method
     * @param args Command line args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FlowerManager flowerManager = new FlowerManager();
        EmployeeManager employeeManager = new EmployeeManager();
        CustomerManager customerManager = new CustomerManager();
        OrderManager orderManager = new OrderManager(flowerManager, employeeManager, customerManager);

        int choice = -1;
        do {
            System.out.println(Message.menu());

            choice = Lib.inputInteger(
                true,
                "> Your choice: ",
                "Choice must be a valid integer\n",
                1,
                "Choice must be from 1 to 20\n",
                20,
                "Choice must be from 1 to 20"
            );

            System.out.println("");

            switch (choice) {
                case 1:
                    System.out.println("--- Add flower ---");
                    System.out.println(flowerManager.add());
                    break;
                case 2:
                    System.out.println("--- Update flower ---");
                    System.out.println(flowerManager.update());
                    break;
                case 3:
                    System.out.println("--- Remove flower ---");
                    System.out.println(flowerManager.remove());
                    break;
                case 4:
                    System.out.println("--- Search for flower ---");
                    System.out.println(flowerManager.search());
                    break;
                case 5:
                    System.out.println("--- Statistic flower ---");
                    System.out.println(flowerManager.toString());
                    break;
                case 6:
                    System.out.println("--- Add employee ---");
                    System.out.println(employeeManager.add());
                    break;
                case 7:
                    System.out.println("--- Update employee ---");
                    System.out.println(employeeManager.update());
                    break;
                case 8:
                    System.out.println("--- Remove employee ---");
                    System.out.println(employeeManager.remove());
                    break;
                case 9:
                    System.out.println("--- Search for employee ---");
                    System.out.println(employeeManager.search());
                    break;
                case 10:
                    System.out.println("--- Show employees ---");
                    System.out.println(employeeManager.toString());
                    break;
                case 11:
                    System.out.println("--- Add customer ---");
                    System.out.println(customerManager.add());
                    break;
                case 12:
                    System.out.println("--- Update customer ---");
                    System.out.println(customerManager.update());
                    break;
                case 13:
                    System.out.println("--- Remove customer ---");
                    System.out.println(customerManager.remove());
                    break;
                case 14:
                    System.out.println("--- Search for customer ---");
                    System.out.println(customerManager.search());
                    break;
                case 15:
                    System.out.println("--- Customer statistic ---");
                    System.out.println(customerManager.toString());
                    break;
                case 16:
                    System.out.println("--- Create order ---");
                    System.out.println(orderManager.create());
                    break;
                case 17:
                    System.out.println("--- Search for order ---");
                    System.out.println(orderManager.search());
                    break;
                case 18:
                    System.out.println("--- Order statistic ---");
                    System.out.println(orderManager.toString());
                    break;
                default:
                    flowerManager.save();
                    employeeManager.save();
                    customerManager.save();
                    System.out.println("All data has been saved, thanks for using!");
                    break;
            }

            if (choice != 19) {
                System.out.print("\nEnter to back to menu > ");
                in.nextLine();
            }
        } while (choice != 19);

        in.close();
    }   
}
