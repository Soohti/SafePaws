package org.cs3343.safepaws.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbManager {
    private static String url;
    private static String username;
    private static String password;
    private final static String testSql = "SELECT 1";

    public static void init() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("conf/server/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        try {
            Class.forName(driver);
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(testSql)) {
                pstmt.executeQuery();
                System.out.println("Database connection established");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static String testSelect() {
        System.out.println("Running test query: " + testSql);
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(testSql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
