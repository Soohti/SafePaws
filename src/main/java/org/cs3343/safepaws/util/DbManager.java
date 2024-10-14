package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;


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
        createAdminAccountIfNotExists(conn);
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
