package com.chivickshazard.atmmachine.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DbHelper;

public class CustomerDAD {
    public static void createCustomer(String firstName, String lastName, String phone, String email, String pin) {
        String query = "INSERT INTO customers (firstName, lastName, phone, email, pin) VALUES (?, ?, ?, ?, ?)";

        try {
            Customer customer = new Customer();
            Connection conn = DbHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setString(5, pin);

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        customer.setCustomerId(rs.getInt("id"));
                        customer.setFirstName(rs.getString("firstName"));
                        customer.setLastName(rs.getString("lastName"));
                        customer.setPhone(rs.getString("phone"));
                        customer.setEmail(rs.getString("email"));
                        customer.setPin(rs.getString("pin"));
                    }
                }

                System.out.println("Account Created Successfully!");
                System.out.println(customer.toString());
            } else {
                System.err.println("Account Creation Failed :(");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createCustomer(Customer newCustomer) {
        String query = "INSERT INTO customers (firstName, lastName, phone, email, pin) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DbHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, newCustomer.getFirstName());
            stmt.setString(2, newCustomer.getLastName());
            stmt.setString(3, newCustomer.getPhone());
            stmt.setString(4, newCustomer.getEmail());
            stmt.setString(5, newCustomer.getPin());

            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        
                    }
                }

                System.out.println("New Account Created Successfully!");
                System.out.println(newCustomer.toString());
            } else {
                System.err.println("Account Not Created :(");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
