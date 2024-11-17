package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        String insertSql =
                "INSERT INTO ACCOUNT (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword()); // 确保加密
            pstmt.setString(3, account.getRole());
            pstmt.executeUpdate();
            System.out.println("Account inserted successfully");
        }
        if ("M".equals(account.getRole())) {
            String query = "SELECT * FROM ACCOUNT WHERE username = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, account.getUsername());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    MemberProfile memberProfile =
                            new MemberProfile("Dog", "Dog", "m", 0, 0, 0, 0, 0,
                                    0, 0);
                    DbManager.insertMemProfile(id, memberProfile);
                }
            }
        }

    }

    public static void insertPet(Pet pet) throws SQLException {
        String insertSql =
                "INSERT INTO PET (Name, Species, Breed, Age, Weight, Gender, ActivityLevel, HealthStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getSpecies());
            pstmt.setString(3, pet.getBreed());
            pstmt.setInt(4, pet.getAge());
            pstmt.setInt(5, pet.getWeight());
            pstmt.setString(6, pet.getGender());
            pstmt.setInt(7, pet.getActivityLevel());
            pstmt.setInt(8, pet.getHealthStatus());

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
    public static boolean authenticateUser(String username, String password)
            throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return BCrypt.checkpw(password, storedPassword);
            }
        } catch (SQLException e) {
            System.out.println(
                    "Error during authentication: " + e.getMessage());
        }
        return false;
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
            if (rs.next()) {
                String Username = rs.getString("username");
                String Password = rs.getString("password");
                String Role = rs.getString("role");
                int id = rs.getInt("Id");
                Account account = new Account();
                if ("A".equals(Role)) {
                    account = new Admin(Username, Password, Role);
                } else if ("M".equals(Role)) {
                    account = DbManager.selectMemberById(id);
                }
                account.setId(id);
                return account;
            }
        } catch (SQLException e) {
            System.out.println("Error during Logging in: " + e.getMessage());
        }
        return null;
    }


    public static ArrayList<Application> ViewAllApplication()
            throws SQLException {
        String query = "SELECT * FROM APPLICATION";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Application> applications = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int Mid = rs.getInt("MId");
                int Pid = rs.getInt("PId");
                int State = rs.getInt("State");
                Member account = selectMemberById(Mid);
                Pet pet = selectPetById(Pid);
                Application application = new Application(account, pet, State);
                application.setId(id);
                applications.add(application);
            }
            return applications;
        } catch (SQLException e) {
            System.out.println(
                    "Error during Viewing all Applications: " + e.getMessage());
        }
        return null;


    }

    public static Application selectApplication(int id) {
        String query = "SELECT * FROM APPLICATION WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int Mid = rs.getInt("Mid");
                int Pid = rs.getInt("Pid");
                int State = rs.getInt("State");
                Member account = selectMemberById(Mid);
                Pet pet = selectPetById(Pid);
                Application application = new Application(account, pet, State);
                application.setId(id);
                return application;
            }
        } catch (SQLException e) {
            System.out.println(
                    "Error during Selecting Application: " + e.getMessage());
        }
        return null;
    }

    public static Application changeState(int Aid, int state) {
        String updateSQL = "UPDATE APPLICATION SET State = ? WHERE Id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setInt(1, state);
            pstmt.setInt(2, Aid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(
                    "Error during Changing State: " + e.getMessage());
        }
        return null;
    }


    private static Member selectMemberById(int mid) throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE Id= ?";
        Member account = new Member();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, mid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String Username = rs.getString("username");
                String Password = rs.getString("password");
                String Role = rs.getString("role");
                account.setUsername(Username);
                account.setPassword(Password);
                account.setId(mid);
                account.setRole(Role);
            }
        }
        query = "SELECT * FROM MEMBER_PROFILE WHERE Id= ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(query)) {
            pstmt1.setInt(1, mid);
            ResultSet rs1 = pstmt1.executeQuery();
            if (rs1.next()) {
                String preferredSpecies = rs1.getString("PreferredSpecies");
                String preferredBreed = rs1.getString("PreferredBreed");
                int extroversionLevel = rs1.getInt("ExtroversionLevel");
                int dailyActivityLevel = rs1.getInt("DailyActivityLevel");
                int houseSize = rs1.getInt("HouseSize");
                int workHours = rs1.getInt("WorkHours");
                int numberOfFamilyMembers = rs1.getInt("NumberOfFamilyMembers");
                int previousPetExperience = rs1.getInt("PreviousPetExperience");
                int financialBudget = rs1.getInt("financialBudget");
                String gender = rs1.getString("Gender");
                MemberProfile memberProfile =
                        new MemberProfile(preferredSpecies, preferredBreed,
                                gender, extroversionLevel, dailyActivityLevel,
                                houseSize, workHours, numberOfFamilyMembers,
                                previousPetExperience, financialBudget);
                account.SetProfile(memberProfile);
                return account;
            }
        }
        return null;
    }


    /**
     * @param petId
     * @return
     * @throws SQLException
     */
    public static Pet selectPetById(int petId) throws SQLException {
        String selectSql = "SELECT * FROM PET WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {

            pstmt.setInt(1, petId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieving all fields using column indices or column names
                    String name = rs.getString("Name");
                    String species = rs.getString("Species");
                    String breed = rs.getString("Breed");
                    int age = rs.getInt("Age");
                    int weight = rs.getInt("Weight");
                    String gender = rs.getString("Gender");
                    int activityLevel = rs.getInt("ActivityLevel");
                    int healthStatus = rs.getInt("HealthStatus");
                    Pet pet = new Pet(name, species, breed, age, weight, gender,
                            activityLevel, healthStatus);
                    pet.setId(petId);
                    return pet;
                } else {
                    System.out.println("No pet found with the given Id");
                    return null;
                }
            }
        }
    }

    /**
     * @return
     */
    public static List<Pet> getAllPets() {
        String query = "SELECT * FROM PET";
        List<Pet> pets = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String species = rs.getString("species");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                int weight = rs.getInt("weight");
                String gender = rs.getString("gender");
                int activityLevel = rs.getInt("activityLevel");
                int healthStatus = rs.getInt("healthStatus");
                int id = rs.getInt("Id");

                Pet pet = new Pet(name, species, breed, age, weight, gender,
                        activityLevel, healthStatus);
                pet.setId(id);
                pets.add(pet);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error retrieving pets from database: " + e.getMessage());
        }

        return pets;
    }

    public static void insertMemProfile(int id, MemberProfile memberProfile)
            throws SQLException {
        String insertSql = "INSERT INTO MEMBER_PROFILE (Id, PreferedSpecies, "
                + "PreferedBreed, ExtroversionLevel, "
                + "DailyActivityLevel, HouseSize, "
                + "WorkHours, NumberofFamilyMembers, "
                + "PreviousPetExperience, FinancialBudget, Gender) VALUES (?, ?, ?,?, ?, ?,?, ?, ?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, memberProfile.getPreferredSpecies());
            pstmt.setString(3, memberProfile.getPreferredBreed());
            pstmt.setInt(4, memberProfile.getExtroversionLevel());
            pstmt.setInt(5, memberProfile.getDailyActivityLevel());
            pstmt.setInt(6, memberProfile.getHouseSize());
            pstmt.setInt(7, memberProfile.getWorkHours());
            pstmt.setInt(8, memberProfile.getNumberOfFamilyMembers());
            pstmt.setInt(9, memberProfile.getPreviousPetExperience());
            pstmt.setInt(10, memberProfile.getFinancialBudget());
            pstmt.setString(11, memberProfile.getGender());
            pstmt.executeUpdate();
            System.out.println("Member profile inserted successfully");
        }
    }

    public static void changeMemProfile(int id, MemberProfile memberProfile)
            throws SQLException {

        String updateSQL =
                "UPDATE MEMBER_PROFILE SET PreferedSpecies = ?, PreferedBreed = ?, "
                        +
                        "ExtroversionLevel = ?, DailyActivityLevel = ?, HouseSize = ?, "
                        +
                        "WorkHours = ?, NumberofFamilyMembers = ?, PreviousPetExperience = ?, "
                        +
                        "FinancialBudget = ?, Gender = ? " +
                        "WHERE Id =?";


        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, memberProfile.getPreferredSpecies());
            pstmt.setString(2, memberProfile.getPreferredBreed());
            pstmt.setInt(3, memberProfile.getExtroversionLevel());
            pstmt.setInt(4, memberProfile.getDailyActivityLevel());
            pstmt.setInt(5, memberProfile.getHouseSize());
            pstmt.setInt(6, memberProfile.getWorkHours());
            pstmt.setInt(7, memberProfile.getNumberOfFamilyMembers());
            pstmt.setInt(8, memberProfile.getPreviousPetExperience());
            pstmt.setInt(9, memberProfile.getFinancialBudget());
            pstmt.setString(10, memberProfile.getGender());
            pstmt.setInt(11, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(
                    "Error during Changing Member Profile: " + e.getMessage());
        }
    }

    /**
     * @param memberId
     * @param petId
     * @param state
     * @throws SQLException
     */
    public static void insertApplication(Application application)
            throws SQLException {
        String insertSql =
                "INSERT INTO APPLICATION (MId, PId, State) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            pstmt.setInt(1, application.getUser().getId());
            pstmt.setInt(2, application.getPet().getId());
            pstmt.setInt(3, 0);

            pstmt.executeUpdate();
            System.out.println("Application inserted successfully");
        }
    }


    public static List<Application> selectApplicationByMember(Member member) {
        String query = "SELECT * FROM APPLICATION WHERE Mid = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, member.getId());
            ResultSet rs = pstmt.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int Pid = rs.getInt("PId");
                int State = rs.getInt("State");
                Pet pet = selectPetById(Pid);
                Application application = new Application(member, pet, State);
                application.setId(id);
                applications.add(application);
            }
            return applications;
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
