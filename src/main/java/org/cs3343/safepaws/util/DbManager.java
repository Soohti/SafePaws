package org.cs3343.safepaws.util;

import java.sql.*;

public class DbManager {
    private static String url;
    private static String username;
    private static String password;
    private static final String testSql = "SELECT 1";

    public static void init(String dbDriver, String dbUrl, String dbUsername, String dbPassword) {
        url = dbUrl;
        username = dbUsername;
        password = dbPassword;
        try {
            Class.forName(dbDriver);
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
