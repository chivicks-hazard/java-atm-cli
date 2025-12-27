package com.chivickshazard.atmmachine.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.chivickshazard.atmmachine.utils.DbHelper;

/**
 * Data Access Object for Account operations.
 * Handles all database interactions related to accounts.
 */
public class AccountDAO {
    /**
     * Creates a new account in the database.
     * Solution: Uses try-with-resources for proper connection management.
     * 
     * @param customerId The ID of the customer who owns the account
     * @param accountType The type of account (SAVINGS or CURRENT)
     * @param balance The initial balance for the account
     */
    public static void createAccount(int customerId, String accountType, double balance) {
        String query = "INSERT INTO accounts (customerId, accountType, balance) VALUES (?, ?, ?)";
        int rowsAffected;

        try (Connection conn = DbHelper.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, customerId);
            stmt.setString(2, accountType);
            stmt.setDouble(3, balance);

            rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAccount Created Successfully!");
            }
        } catch (Exception e) {
            // Solution: Improved error handling with descriptive message
            System.err.println("Error creating account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates the balance of an account.
     * Solution: Uses try-with-resources for proper connection management.
     * 
     * @param customerId The ID of the customer
     * @param accountType The type of account
     * @param balance The new balance
     * @return true if the update was successful, false otherwise
     */
    public static boolean changeBalance(int customerId, String accountType, double balance) {
        String query = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        boolean isTransactionSuccessful = false;

        try (Connection conn = DbHelper.getConnection()) {
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
    
    /**
     * Performs a transfer between two accounts using a database transaction.
     * Solution: Fixed try-with-resources syntax and improved transaction handling.
     * Both updates must succeed or both will be rolled back (atomic transaction).
     * 
     * @param senderId The ID of the sender customer
     * @param receiverId The ID of the receiver customer
     * @param senderAccountType The account type of the sender
     * @param receiverAccountType The account type of the receiver
     * @param newSenderBalance The new balance for the sender account
     * @param newReceiverBalance The new balance for the receiver account
     * @return true if the transfer was successful, false otherwise
     */
    public static boolean makeTransfer(int senderId, int receiverId, String senderAccountType, String receiverAccountType, double newSenderBalance, double newReceiverBalance) {
        String senderQuery = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        String receiverQuery = "UPDATE accounts SET balance = ? WHERE customerId = ? AND accountType = ?";
        boolean isTransactionSuccessful = false;

        // Solution: Fixed - Manual connection management to ensure proper rollback on exceptions
        // Using try-with-resources can cause issues because connection closes before rollback
        // Solution: Using a single connection for both updates prevents deadlocks
        Connection conn = null;
        try {
            conn = DbHelper.getConnection();
            // Solution: Disable auto-commit to enable transaction control
            // This ensures both updates happen atomically (all or nothing)
            conn.setAutoCommit(false);

            // For the sender account
            try (PreparedStatement senderStmt = conn.prepareStatement(senderQuery)) {
                senderStmt.setDouble(1, newSenderBalance);
                senderStmt.setInt(2, senderId);
                senderStmt.setString(3, senderAccountType);
                int senderRowsAffected = senderStmt.executeUpdate();
    
                if (senderRowsAffected == 0) {
                    // Solution: Rollback transaction if sender update fails
                    conn.rollback();
                    return false;
                }
            }
            
            // For the receiver account
            try (PreparedStatement receiverStmt = conn.prepareStatement(receiverQuery)) {
                receiverStmt.setDouble(1, newReceiverBalance);
                receiverStmt.setInt(2, receiverId);
                receiverStmt.setString(3, receiverAccountType);
                int receiverRowsAffected = receiverStmt.executeUpdate();
    
                if (receiverRowsAffected == 0) {
                    // Solution: Rollback transaction if receiver update fails
                    conn.rollback();
                    return false;
                }
            }

            // Solution: Commit transaction only if both updates succeeded
            conn.commit();   
            System.out.println("\nBalance Updated Successfully!");
            isTransactionSuccessful = true;
        
        } catch (SQLException e) {
            // Solution: CRITICAL FIX - Explicitly rollback on exception to prevent hanging/deadlocks
            // If we don't rollback, the transaction remains open and can cause the database to hang
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Error during transfer transaction: " + e.getMessage());
            e.printStackTrace();
            isTransactionSuccessful = false;
        } catch (Exception e) {
            // Solution: CRITICAL FIX - Explicitly rollback on exception to prevent hanging/deadlocks
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Unexpected error during transfer: " + e.getMessage());
            e.printStackTrace();
            isTransactionSuccessful = false;
        } finally {
            // Solution: Ensure connection is always closed, even if rollback fails
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error closing connection: " + closeEx.getMessage());
                }
            }
        }

        return isTransactionSuccessful;
    }
}
