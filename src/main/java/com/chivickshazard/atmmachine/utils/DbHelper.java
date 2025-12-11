package com.chivickshazard.atmmachine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String url = "jdbc:postgresql://localhost:5432/bank";
    private static final String userName = "postgres"; // mysql: root
    private static final String password = "pgchivicks"; // mysql: sqlchivicks
    
    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, userName, password);

        conn.setAutoCommit(true);

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
