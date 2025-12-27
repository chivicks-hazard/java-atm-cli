package com.chivickshazard.atmmachine.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DbHelper;

/**
 * Data Access Object for Customer operations.
 * Note: Class name has a typo (DAD instead of DAO) but kept for backward compatibility.
 * Handles all database interactions related to customers.
 */
public class CustomerDAD {
    /**
     * Creates a new customer in the database.
     * Solution: Fixed resource leak by using try-with-resources for proper connection management.
     * 
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     * @param phone Customer's phone number
     * @param email Customer's email address
     * @param pin Customer's PIN
     */
    public static void createCustomer(String firstName, String lastName, String phone, String email, String pin) {
        String query = "INSERT INTO customers (firstName, lastName, phone, email, pin) VALUES (?, ?, ?, ?, ?)";

        // Solution: Fixed resource leak - now using try-with-resources for automatic connection closure
        try (Connection conn = DbHelper.getConnection()) {
            Customer customer = new Customer();
            
            // Solution: PreparedStatement is also managed within try-with-resources
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, pin);

                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    // Solution: Fixed - getGeneratedKeys() returns a ResultSet with the generated key column
                    // The column name is typically "GENERATED_KEY" or the first column, not "id"
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            // Solution: Fixed - use getInt(1) to get the first (and only) generated key
                            customer.setCustomerId(rs.getInt(1));
                            customer.setFirstName(firstName);
                            customer.setLastName(lastName);
                            customer.setPhone(phone);
                            customer.setEmail(email);
                            customer.setPin(pin);
                        }
                    }

                    System.out.println("Account Created Successfully!");
                    System.out.println(customer.toString());
                } else {
                    System.err.println("Account Creation Failed :(");
                }
            }
        } catch (Exception e) {
            // Solution: Improved error handling with descriptive message
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Creates a new customer in the database using a Customer object.
     * Solution: Fixed resource leak by using try-with-resources and removed empty try block.
     * 
     * @param newCustomer The Customer object containing customer information
     */
    public static void createCustomer(Customer newCustomer) {
        String query = "INSERT INTO customers (firstName, lastName, phone, email, pin) VALUES (?, ?, ?, ?, ?)";

        // Solution: Fixed resource leak - now using try-with-resources for automatic connection closure
        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, newCustomer.getFirstName());
            stmt.setString(2, newCustomer.getLastName());
            stmt.setString(3, newCustomer.getPhone());
            stmt.setString(4, newCustomer.getEmail());
            stmt.setString(5, newCustomer.getPin());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Solution: Fixed empty try block - now properly retrieves and sets the generated customer ID
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Solution: Set the generated customer ID from the database
                        newCustomer.setCustomerId(rs.getInt(1));
                    }
                }

                System.out.println("New Account Created Successfully!");
                System.out.println(newCustomer.toString());
            } else {
                System.err.println("Account Not Created :(");
            }
        } catch (Exception e) {
            // Solution: Improved error handling with descriptive message
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
