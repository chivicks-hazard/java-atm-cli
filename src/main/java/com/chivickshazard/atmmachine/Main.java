package com.chivickshazard.atmmachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bank";
        String userName = "root";
        String password = "sqlchivicks";

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            
            System.out.println("Connected to the database");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM atm_accounts");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int accountNumber = rs.getInt("accountNumber");
                String accountType = rs.getString("accountType");
                System.out.println();

                SavingsAccount savAcc = new SavingsAccount(id, lastName + " " + firstName, accountNumber, accountType);

                System.out.println(savAcc.toString());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Thank you :)");
    }
}