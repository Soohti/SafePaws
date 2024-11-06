package org.cs3343.safepaws.util;

import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.entity.Member;

//import java.lang.reflect.Member;
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
    
    public static void insertPet(Pet pet) throws SQLException{
    	String insertSql = "INSERT INTO PET (Breed, Age, Size, Gender, ActivityLevel, HealthStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, pet.getBreed());
            pstmt.setInt(2, pet.getAge()); 
            pstmt.setInt(3, pet.getSize());
            pstmt.setString(4, pet.getGender());
            pstmt.setInt(5, pet.getActivityLevel());
            pstmt.setInt(6, pet.getHealthStatus());
            
            pstmt.executeUpdate();
            System.out.println("Pet inserted successfully");
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
    
    public static Pet selectPetById(int pid) throws SQLException{
    	String query = "SELECT * FROM PET WHERE Id= ?";

        try (Connection conn = getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setInt(1, pid);
        	ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String breed = rs.getString("Breed");
                int age = rs.getInt("Age");
                int size = rs.getInt("Size");
                String gender = rs.getString("Gender");
                int activityLevel = rs.getInt("ActivityLevel");
                int healthStatus = rs.getInt("HealthStatus");
                Pet pet=new Pet(breed, age, size, gender, activityLevel, healthStatus);
                pet.setId(pid);
                return pet;
                //applications.add();
            }
        } 
		return null;
	}
    
    public static ArrayList<Application> ViewAllApplication() throws SQLException{
    	String query = "SELECT * FROM APPLICATION";
    	
    	try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {
    		ResultSet rs = stmt.executeQuery(query);
    		ArrayList<Application> applications=new ArrayList<>();
    		while (rs.next()) {
                int id=rs.getInt("Id");
                int Mid=rs.getInt("MId");
                int Pid=rs.getInt("PId");
                int State =rs.getInt("State");
                Member account = selectMemberById(Mid);
            	Pet pet = selectPetById(Pid);
            	Application application=new Application(account,pet,State);
            	application.setId(id);
                applications.add(application);
                return applications;
            }
    	} catch (SQLException e) {
            System.out.println("Error during Logging in: " + e.getMessage());
        }
		return null;
    	
    	
    	
    }
    
    public static Application selectApplication(int id) {
    	String query = "SELECT * FROM APPLICATION WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	int Mid = rs.getInt("Mid");
            	int Pid = rs.getInt("Pid");
            	int State = rs.getInt("State");
            	Member account = selectMemberById(Mid);
            	Pet pet = selectPetById(Pid);
            	Application application=new Application(account,pet,State);
            	application.setId(id);
                return application;
            }
        } catch (SQLException e) {
            System.out.println("Error during Logging in: " + e.getMessage());
        }
		return null;
    }

	private static Member selectMemberById(int mid) throws SQLException{
		String query = "SELECT * FROM Account WHERE Id= ?";

        try (Connection conn = getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setInt(1, mid);
        	ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	String Username = rs.getString("username");
            	String Password = rs.getString("password");
            	String Role = rs.getString("role");
                Member account= new Member(Username,Password,Role);
                query = "SELECT * FROM MEMBERPROFILE WHERE Id= ?";
        
                try(PreparedStatement pstmt1 = conn.prepareStatement(query)) {
                    	pstmt1.setInt(1, mid);
                    	rs = pstmt1.executeQuery();
                        if (rs.next()) {
                        	this.preferredBreed = preferredBreed;
                   		 this.extroversionLevel = extroversionLevel;
                   		 this.dailyActivityLevel = dailyActivityLevel;
                   		 this.houseSize = houseSize;
                   		 this.workHours = workHours;
                   		 this.numberOfFamilyMembers = numberOfFamilyMembers;
                   		 this.previousPetExperience = previousPetExperience;
                   		 this.financialBudget = financialBudget;
                   		 this.gender=gender;
                            Member account= new Member(Username,Password,Role);
                            query = "SELECT * FROM MEMBERPROFILE WHERE Id= ?";
                        	
                        	
                            return account;
                        }
                }
            }
        } 
		return null;
	}

//	private static Account selectAccountById(int mid) throws SQLException{
//		String query = "SELECT * FROM ACCOUNT WHERE Id= ?";
//
//        try (Connection conn = getConnection();
//        	PreparedStatement pstmt = conn.prepareStatement(query)) {
//        	pstmt.setInt(1, mid);
//        	ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//            	String Username = rs.getString("username");
//            	String Password = rs.getString("password");
//            	String Role = rs.getString("role");
//                Account account= new Account(Username,Password,Role);
//            	account.setId(mid);
//                return account;
//            }
//        } 
//		return null;
//	}

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
