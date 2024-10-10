package org.cs3343.safepaws.util;

import java.sql.*;

public class DbManager {
    private static String url;
    private static String username;
    private static String password;
    private static final String testSql = "SELECT 1";

    public static void init(String dbUrl, String dbUsername, String dbPassword) throws SQLException {
        url = dbUrl;
        username = dbUsername;
        password = dbPassword;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(testSql)) {
            pstmt.executeQuery();
            System.out.println("Database connection established");
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
            System.out.println("Error executing query: " + e.getMessage());
        }
        return null;
    }
}
