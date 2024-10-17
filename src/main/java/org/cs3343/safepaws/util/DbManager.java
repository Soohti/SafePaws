package org.cs3343.safepaws.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.cs3343.safepaws.entity.Account;


import java.sql.*;

public final class DbManager {
    /**
     * URL for the database.
     */
    private static String url;
    /**
     * Username for the database.
     */
    private static String username;
    /**
     * Password for the database.
     */
    private static String password;
    /**
     * A query to test the database connection.
     */
    private static final String TEST_SQL = "SELECT 1";

    /**
     * Initializes the database connection.
     *
     * @param dbUrl      URL for the database.
     * @param dbUsername Username for the database.
     * @param dbPassword Password for the database.
     * @throws SQLException If an error occurs.
     */
    public static void init(final String dbUrl, final String dbUsername,
                            final String dbPassword)
            throws SQLException {
        url = dbUrl;
        username = dbUsername;
        password = dbPassword;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_SQL)) {
            pstmt.execute();
            System.out.println("Database connection established");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static String encryptPassword(String password) {
        // TODO: 在此实现密码加密算法
        return password; // 这里返回原始密码，替换为加密后的密码
    }

    private static void createAdminAccountIfNotExists(Connection conn) throws SQLException {
        String checkAdminSql = "SELECT COUNT(*) FROM users WHERE role = 'admin'";
        try (PreparedStatement pstmt = conn.prepareStatement(checkAdminSql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) == 0) {
                String insertAdminSql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                try (PreparedStatement insertPstmt = conn.prepareStatement(insertAdminSql)) {
                    insertPstmt.setString(1, "admin");
                    insertPstmt.setString(2, encryptPassword("adminPassword")); // 在此处加密密码
                    insertPstmt.setString(3, "admin");
                    insertPstmt.executeUpdate();
                    System.out.println("Admin account created.");
                }
            }
        }
    }

    public static void insertAccount(Account account) throws SQLException {
        String insertSql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, encryptPassword(account.getPassword())); // 确保加密
            pstmt.setString(3, account.getRole());
            pstmt.executeUpdate();
            System.out.println("Account created successfully");
        }
    }

    public static boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // 直接比较存储的密码和输入的密码（需使用相同的加密方法）
                return storedPassword.equals(encryptPassword(password));
            }
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }
        return false; // 用户名不存在或密码不匹配
    }

    /**
     * Tests the database connection.
     *
     * @return The result of the test query.
     */
    public static String testSelect() {
        System.out.println("Running test query: " + TEST_SQL);
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_SQL);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return null;
    }

    private DbManager() {
        throw new AssertionError("Instantiation not allowed");
    }
}
