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

/**
 * Database manager for handling database operations.
 */
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
     * Index constant for the value 3.
     */
    private static final int INDEX_THREE = 3;

    /**
     * Index constant for the value 4.
     */
    private static final int INDEX_FOUR = 4;

    /**
     * Index constant for the value 5.
     */
    private static final int INDEX_FIVE = 5;

    /**
     * Index constant for the value 6.
     */
    private static final int INDEX_SIX = 6;

    /**
     * Index constant for the value 7.
     */
    private static final int INDEX_SEVEN = 7;

    /**
     * Index constant for the value 8.
     */
    private static final int INDEX_EIGHT = 8;
    
    /**
     * Index constant for the value 9.
     */
    private static final int INDEX_NINE = 9;

    /**
     * Index constant for the value 10.
     */
    private static final int INDEX_TEN = 10;

    /**
     * Index constant for the value 11.
     */
    private static final int INDEX_ELEVEN = 11;

    /**
     * Initializes the database connection.
     *
     * @param dbUrl      URL for the database.
     * @param dbUsername Username for the database.
     * @param dbPassword Password for the database.
     * @throws SQLException If an error occurs.
     */
    public static void init(final String dbUrl,
                            final String dbUsername,
                            final String dbPassword) throws SQLException {
        url = dbUrl;
        username = dbUsername;
        password = dbPassword;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_SQL)) {
            pstmt.execute();
            System.out.println("Database connection established");
        }
    }

    /**
     * Establishes a connection to the database
     * using the configured URL, username,
     * and password.
     *
     * @return a Connection object to the database.
     * @throws SQLException if a database access
     * error occurs or the URL is null.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Inserts an account into the database.
     *
     * @param account the account to insert.
     * @throws SQLException if a database error occurs.
     */
    public static void insertAccount(final Account account)
            throws SQLException {
        String insertSql = "INSERT INTO ACCOUNT "
                + "(username, password, role) "
                + "VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt =
                     conn.prepareStatement(insertSql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());
            pstmt.setString(3, account.getRole());
            pstmt.executeUpdate();
            System.out.println("Account inserted successfully");
        }
        if ("M".equals(account.getRole())) {
            String query = "SELECT * FROM ACCOUNT"
                    + "WHERE username = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, account.getUsername());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    int[] numericAttributes = {0, 0, 0, 0, 0, 0, 0};
                    MemberProfile memberProfile = new MemberProfile("Dog",
                            "Dog", "m", numericAttributes);
                    DbManager.insertMemProfile(id, memberProfile);
                }
            }
        }
    }

    /**
     * Inserts a pet into the database.
     *
     * @param pet the pet to insert.
     * @throws SQLException if a database error occurs.
     */
    public static void insertPet(final Pet pet) throws SQLException {
        String insertSql = "INSERT INTO PET (Name, Species, Breed, Age, "
                + "Weight, Gender, ActivityLevel, HealthStatus) VALUES (?, ?, "
                + "?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, pet.getSpecies());
            pstmt.setString(INDEX_THREE, pet.getBreed());
            pstmt.setInt(INDEX_FOUR, pet.getAge());
            pstmt.setInt(INDEX_FIVE, pet.getWeight());
            pstmt.setString(INDEX_SIX, pet.getGender());
            pstmt.setInt(INDEX_SEVEN, pet.getActivityLevel());
            pstmt.setInt(INDEX_EIGHT, pet.getHealthStatus());
            pstmt.executeUpdate();
            System.out.println("Pet inserted successfully");
        }
    }

    /**
     * Authenticates a user.
     *
     * @param newUsername the username.
     * @param newPassword the password.
     * @return true if authentication is successful, false otherwise.
     * @throws SQLException if a database error occurs.
     */
    public static boolean authenticateUser(final String newUsername,
                                           final String newPassword)
            throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return BCrypt.checkpw(newPassword, storedPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error during authentication: "
                    + e.getMessage());
        }
        return false;
    }

    /**
     * Selects an account by username.
     *
     * @param inputUsername the username.
     * @return the account, or null if not found.
     */
    public static Account selectAccount(final String inputUsername) {
        String query = "SELECT * FROM ACCOUNT WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, inputUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String rUser = rs.getString("username");
                String rPsw = rs.getString("password");
                String rPole = rs.getString("role");
                int id = rs.getInt("Id");
                Account account = new Account();
                if ("A".equals(rPole)) {
                    account = new Admin(rUser, rPsw, rPole);
                } else if ("M".equals(rPole)) {
                    account = DbManager.selectMemberById(id);
                }
                account.setId(id);
                return account;
            }
        } catch (SQLException e) {
            System.out.println("Error during Logging in: "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Views all applications.
     *
     * @return a list of all applications.
     * @throws SQLException if a database error occurs.
     */
    public static ArrayList<Application> viewAllApplication()
            throws SQLException {
        String query = "SELECT * FROM APPLICATION";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Application> applications = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int mid = rs.getInt("MId");
                int pid = rs.getInt("PId");
                int state = rs.getInt("State");
                Member account = selectMemberById(mid);
                Pet pet = selectPetById(pid);
                Application application = new Application(account, pet, state);
                application.setId(id);
                applications.add(application);
            }
            return applications;
        } catch (SQLException e) {
            System.out.println("Error during Viewing all Applications: "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Selects an application by ID.
     *
     * @param id the application ID.
     * @return the application, or null if not found.
     */
    public static Application selectApplication(final int id) {
        String query = "SELECT * FROM APPLICATION WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int mid = rs.getInt("Mid");
                int pid = rs.getInt("Pid");
                int state = rs.getInt("State");
                Member account = selectMemberById(mid);
                Pet pet = selectPetById(pid);
                Application application = new Application(account, pet, state);
                application.setId(id);
                return application;
            }
        } catch (SQLException e) {
            System.out.println("Error during Selecting Application: "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Changes the state of an application.
     *
     * @param aid   the application ID.
     * @param state the new state.
     * @return the updated application, or null if not found.
     */
    public static Application changeState(final int aid,
            final int state) {
        String updateSQL = "UPDATE APPLICATION SET State = ? "
                + "WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setInt(1, state);
            pstmt.setInt(2, aid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during Changing State: "
                    + e.getMessage());
        }
        return null;
    }

    private static Member selectMemberById(final int mid)
            throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE Id= ?";
        Member account = new Member();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, mid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String rUser = rs.getString("username");
                String rPsw = rs.getString("password");
                String rRole = rs.getString("role");
                account.setUsername(rUser);
                account.setPassword(rPsw);
                account.setId(mid);
                account.setRole(rRole);
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
                String gender = rs1.getString("Gender");
                int[] numericAttributes = {rs1.getInt("ExtroversionLevel"),
                        rs1.getInt("DailyActivityLevel"),
                        rs1.getInt("HouseSize"), rs1.getInt("WorkHours"),
                        rs1.getInt("NumberOfFamilyMembers"),
                        rs1.getInt("PreviousPetExperience"),
                        rs1.getInt("financialBudget")};
                MemberProfile memberProfile = new MemberProfile(
                        preferredSpecies, preferredBreed, gender,
                        numericAttributes);
                account.setProfile(memberProfile);
                return account;
            }
        }
        return null;
    }

    /**
     * Selects a pet by ID.
     *
     * @param petId the pet ID.
     * @return the pet, or null if not found.
     * @throws SQLException if a database error occurs.
     */
    public static Pet selectPetById(final int petId) {
        String selectSql = "SELECT * FROM PET WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setInt(1, petId);
            ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");
                    String species = rs.getString("Species");
                    String breed = rs.getString("Breed");
                    String gender = rs.getString("Gender");
                    int age = rs.getInt("Age");
                    int weight = rs.getInt("Weight");
                    int activityLevel = rs.getInt("ActivityLevel");
                    int healthStatus = rs.getInt("HealthStatus");
                    int[] numericAttributes = {age, weight,
                            activityLevel, healthStatus};
                    Pet pet = new Pet(name, species, breed,
                            gender, numericAttributes);
                    pet.setId(petId);
                    return pet;
                } else {
                    System.out.println("No pet found with the given Id");
                    return null;
                }

        } catch (Exception e) {
            System.out.println("Error during selecting pet by id.");
        }
        return null;
    }

    /**
     * Retrieves all pets.
     *
     * @return a list of all pets.
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
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int weight = rs.getInt("weight");
                int activityLevel = rs.getInt("activityLevel");
                int healthStatus = rs.getInt("healthStatus");
                int id = rs.getInt("Id");
                int[] numericAttributes = {age, weight,
                        activityLevel, healthStatus};
                Pet pet = new Pet(name, species, breed,
                        gender, numericAttributes);
                pet.setId(id);
                pets.add(pet);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving pets from database: "
                    + e.getMessage());
        }
        return pets;
    }

    /**
     * Inserts a member profile into the database.
     *
     * @param id            the member ID.
     * @param memberProfile the member profile to insert.
     * @throws SQLException if a database error occurs.
     */
    public static void insertMemProfile(final int id,
                                        final MemberProfile memberProfile)
            throws SQLException {
        String insertSql = "INSERT INTO MEMBER_PROFILE (Id, PreferredSpecies, "
                + "PreferredBreed, ExtroversionLevel, DailyActivityLevel, "
                + "HouseSize, WorkHours, NumberofFamilyMembers, "
                + "PreviousPetExperience, FinancialBudget, Gender) VALUES (?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, memberProfile.getPreferredSpecies());
            pstmt.setString(INDEX_THREE, memberProfile.getPreferredBreed());
            pstmt.setInt(INDEX_FOUR, memberProfile.getExtroversionLevel());
            pstmt.setInt(INDEX_FIVE, memberProfile.getDailyActivityLevel());
            pstmt.setInt(INDEX_SIX, memberProfile.getHouseSize());
            pstmt.setInt(INDEX_SEVEN, memberProfile.getWorkHours());
            pstmt.setInt(INDEX_EIGHT, memberProfile.getNumberOfFamilyMembers());
            pstmt.setInt(INDEX_NINE, memberProfile.getPreviousPetExperience());
            pstmt.setInt(INDEX_TEN, memberProfile.getFinancialBudget());
            pstmt.setString(INDEX_ELEVEN, memberProfile.getGender());
            pstmt.executeUpdate();
            System.out.println("Member profile inserted successfully");
        }
    }

    /**
     * Changes a member profile.
     *
     * @param id            the member ID.
     * @param memberProfile the new member profile.
     * @throws SQLException if a database error occurs.
     */
    public static void changeMemProfile(final int id,
            final MemberProfile memberProfile)
            throws SQLException {
        String updateSQL = "UPDATE MEMBER_PROFILE SET PreferredSpecies = ?, "
                + "PreferredBreed = ?, ExtroversionLevel = ?, "
                + "DailyActivityLevel = ?, HouseSize = ?, WorkHours = ?, "
                + "NumberofFamilyMembers = ?, PreviousPetExperience = ?, "
                + "FinancialBudget = ?, Gender = ? WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, memberProfile.getPreferredSpecies());
            pstmt.setString(2, memberProfile.getPreferredBreed());
            pstmt.setInt(INDEX_THREE, memberProfile.getExtroversionLevel());
            pstmt.setInt(INDEX_FOUR, memberProfile.getDailyActivityLevel());
            pstmt.setInt(INDEX_FIVE, memberProfile.getHouseSize());
            pstmt.setInt(INDEX_SIX, memberProfile.getWorkHours());
            pstmt.setInt(INDEX_SEVEN, memberProfile.getNumberOfFamilyMembers());
            pstmt.setInt(INDEX_EIGHT, memberProfile.getPreviousPetExperience());
            pstmt.setInt(INDEX_NINE, memberProfile.getFinancialBudget());
            pstmt.setString(INDEX_TEN, memberProfile.getGender());
            pstmt.setInt(INDEX_ELEVEN, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during Changing Member Profile: "
                    + e.getMessage());
        }
    }

    /**
     * Inserts an application into the database.
     *
     * @param application the application to insert.
     * @throws SQLException if a database error occurs.
     */
    public static void insertApplication(final Application application)
            throws SQLException {
        String insertSql = "INSERT INTO APPLICATION "
                + "(MId, PId, State) VALUES "
                + "(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setInt(1, application.getUser().getId());
            pstmt.setInt(2, application.getPet().getId());
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
            System.out.println("Application inserted successfully");
        }
    }

    /**
     * Selects applications by member.
     *
     * @param member the member.
     * @return a list of applications.
     */
    public static List<Application> selectApplicationByMember(
            final Member member) {
        String query = "SELECT * FROM APPLICATION WHERE Mid = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, member.getId());
            ResultSet rs = pstmt.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int pid = rs.getInt("PId");
                int state = rs.getInt("State");
                Pet pet = selectPetById(pid);
                Application application = new Application(
                        member, pet, state);
                application.setId(id);
                applications.add(application);
            }
            return applications;
        } catch (SQLException e) {
            System.out.println("Error during Logging in: "
                    + e.getMessage());
        }
        return null;
    }

    /**
     * Tests the database connection.
     *
     * @return the result of the test query.
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
            System.out.println("Error executing query: "
                    + e.getMessage());
        }
        return null;
    }

    private DbManager() {
        throw new AssertionError("Instantiation not allowed");
    }
}
