package com.chivickshazard.atmmachine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database helper class for managing database connections.
 * Note: Database credentials should be moved to a configuration file or environment variables
 * for security in production environments.
 */
public class DbHelper {
    // TODO: Move database credentials to configuration file or environment variables for security
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String userName = "root";
    private static final String password = "sqlchivicks";
    
    /**
     * Establishes a connection to the database.
     * @return Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, userName, password);
        // Auto-commit is enabled by default, but explicitly setting it for clarity
        conn.setAutoCommit(true);
        return conn;
    }

    /**
     * Safely closes a database connection.
     * Handles null checks and SQL exceptions gracefully.
     * @param conn The connection to close
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Log error but don't throw - connection cleanup should not fail the application
                e.printStackTrace();
            }
        }
    }
}
