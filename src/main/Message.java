package main;

/**
 *
 * @author Flower shop
 */
public class Message {
    /**
     * Menu format
     * @return Menu
     */
    public static String menu() {
        return "------------- Menu -------------\n"
            + "Flower category:\n"
                + "\t1. Add flower\n"
                + "\t2. Update flower\n"
                + "\t3. Remove flower\n"
                + "\t4. Search for flower\n"
                + "\t5. Show all flower\n"
            + "Employee category:\n"
                + "\t6. Add employee\n"
                + "\t7. Update employee\n"
                + "\t8. Remove employee\n"
                + "\t9. Search for employee\n"
                + "\t10. Show all employee\n"
            + "Customer category:\n"
                + "\t11. Add customer\n"
                + "\t12. Update customer\n"
                + "\t13. Remove customer\n"
                + "\t14. Search for customer\n"
                + "\t15. Show all customer\n"
            + "Order:\n"
                + "\t16. Create order\n"
                + "\t17. Search for order\n"
                + "\t18. Order statistics\n"
                + "\t19. Save and exit\n";
    }
}
