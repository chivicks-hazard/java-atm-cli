package com.chivickshazard.atmmachine.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DbHelper;

public class AccountDAO {
    public static void createAccount(int customerId, String accountType, double balance) {
        String query = "INSERT INTO accounts (customerId, accountType, balance) VALUES (?, ?, ?)";
        int rowsAffected;

        try {
            Connection conn = DbHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, customerId);
            stmt.setString(2, accountType);
            stmt.setDouble(3, balance);

            rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAccount Created Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
