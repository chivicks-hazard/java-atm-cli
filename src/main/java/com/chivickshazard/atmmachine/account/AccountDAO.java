package com.chivickshazard.atmmachine.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DBHelper;

public class AccountDAO {
    public static void createAccount(int customerId, String accountType, double balance) {
        String query = "INSERT INTO accounts (customerId, accountType, balance) VALUES (?, ?, ?)";
        int rowsAffected;

        try (Connection conn = DBHelper.getConnection()) {
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

    public static boolean changeBalance(int customerId, String accountType, double balance) {
        String query = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        boolean isTransactionSuccessful = false;

        try (Connection conn = DBHelper.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, balance);
            stmt.setInt(2, customerId);
            stmt.setString(3, accountType);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nBalance Updated Successfully!");

                isTransactionSuccessful = true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            isTransactionSuccessful = false;
        }

        return isTransactionSuccessful;
    }
    
    public static boolean makeTransfer(int senderId, int receiverId, String senderAccountType, String receiverAccountType, double newSenderBalance, double newReceiverBalance) {
        String senderQuery = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        String receiverQuery = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        boolean isTransactionSuccessful = false;

        try (
            Connection conn = DBHelper.getConnection()) {
            conn.setAutoCommit(false);

            // For the sender account
            try (PreparedStatement senderStmt = conn.prepareStatement(senderQuery);) {
                senderStmt.setDouble(1, newSenderBalance);
                senderStmt.setInt(2, senderId);
                senderStmt.setString(3, senderAccountType);
                int senderRowsAffected = senderStmt.executeUpdate();
    
                if (senderRowsAffected == 0) {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                return false;
            }
            
            // For the receiver account
            try (PreparedStatement receiverStmt = conn.prepareStatement(receiverQuery)) {
                receiverStmt.setDouble(1, newReceiverBalance);
                receiverStmt.setInt(2, receiverId);
                receiverStmt.setString(3, receiverAccountType);
                int receiverRowsAffected = receiverStmt.executeUpdate();
    
                if (receiverRowsAffected == 0) {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                return false;
            }

            
            conn.commit();   
            System.out.println("\nBalance Updated Successfully!");
            
            isTransactionSuccessful = true;
        
        } catch (Exception e) {
            e.printStackTrace();

            isTransactionSuccessful = false;
        }

        return isTransactionSuccessful;
    }
}
