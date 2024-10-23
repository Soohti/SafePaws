package org.cs3343.safepaws.util;

import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;
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


    /**
     * Insert account.
     *
     * @param account the account
     * @throws SQLException the SQL exception
     */
    public static void insertAccount(Account account) throws SQLException {
        String insertSql = "INSERT INTO ACCOUNT (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword()); // 确保加密
            pstmt.setString(3, account.getRole());
            pstmt.executeUpdate();
            System.out.println("Account inserted successfully");
        }
    }

    /**
     * Authenticate user.
     *
     * @param username the username
     * @param password the password
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    public static boolean authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                // 直接比较存储的密码和输入的密码（需使用相同的加密方法）
                return BCrypt.checkpw(password, storedPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }
        return false; // 用户名不存在或密码不匹配
    }
    
    /**
     * Select account.
     *
     * @param username the username
     * @return the account
     */
    public static Account selectAccount(String username) {
    	String query = "SELECT * FROM ACCOUNT WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	String Username = rs.getString("username");
            	String Password = rs.getString("password");
            	String Role = rs.getString("role");
            	Account account=new Account(Username,Password,Role);
                return account;
            }
        } catch (SQLException e) {
            System.out.println("Error during Logging in: " + e.getMessage());
        }
		return null;
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
