package com.chivickshazard.atmmachine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String userName = "root";
    private static final String password = "sqlchivicks";
    
    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, userName, password);

        return conn;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
