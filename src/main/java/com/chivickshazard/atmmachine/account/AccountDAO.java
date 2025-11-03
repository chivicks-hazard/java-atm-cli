package com.chivickshazard.atmmachine.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DBHelper;

public class AccountDAO {
    public static void createAccount(int customerId, String accountType, double balance) {
        String query = "INSERT INTO accounts (customerId, accountType, balance) VALUES (?, ?, ?)";
        int rowsAffected;

        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, customerId);
            stmt.setString(2, accountType);
            stmt.setDouble(3, balance);

            rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAccount Created Successfully!");
                DBHelper.close(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean changeBalance(int customerId, String accountType, double balance) {
        String query = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        boolean isTransactionSuccessful = false;

        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, balance);
            stmt.setInt(2, customerId);
            stmt.setString(3, accountType);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nBalance Updated Successfully!");
                DBHelper.close(conn);

                isTransactionSuccessful = true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            isTransactionSuccessful = false;
        }

        return isTransactionSuccessful;
    }
}
