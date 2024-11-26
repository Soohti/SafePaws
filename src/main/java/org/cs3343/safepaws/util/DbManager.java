package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.LocationPoint;
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
     *                      error occurs or the URL is null.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Sets the values of the given PreparedStatement.
     * The values are set in the order they are provided.
     *
     * @param preparedStatement the PreparedStatement to set values for.
     * @param values            the values to set.
     * @throws SQLException if a database error occurs.
     */
    private static void setValues(final PreparedStatement preparedStatement,
                                  final Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
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
                + "(Username, Password, Role) "
                + "VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            setValues(pstmt, account.getUsername(),
                    account.getPassword(), account.getRole());
            pstmt.executeUpdate();
            System.out.println("Account inserted successfully");
        }
        if (account instanceof Member) {
            int[] numericAttributes = {0, 0, 0, 0, 0, 0, 0};
            MemberProfile memberProfile = new MemberProfile("Dog",
                    "Dog", "m", numericAttributes);
            DbManager.insertMemProfile((Member) account, memberProfile);
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
                + "Weight, Gender, ActivityLevel, HealthStatus, State) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            setValues(pstmt, pet.getName(), pet.getSpecies(), pet.getBreed(),
                    pet.getAge(), pet.getWeight(), pet.getGender(),
                    pet.getActivityLevel(), pet.getHealthStatus(),
                    pet.getState());
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
     */
    public static boolean authenticateUser(final String newUsername,
                                           final String newPassword) {
        String query = "SELECT * FROM ACCOUNT WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            setValues(pstmt, newUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
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
        String query = "SELECT * FROM ACCOUNT WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            setValues(pstmt, inputUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String rUser = rs.getString("Username");
                String rPsw = rs.getString("Password");
                String rPole = rs.getString("Role");
                int id = rs.getInt("Id");
                if ("A".equals(rPole)) {
                    return new Admin(rUser, rPsw, rPole);
                } else if ("M".equals(rPole)) {
                    return selectMemberById(id);
                }
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
     */
    public static ArrayList<Application> viewAllApplication() {
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
                Application.State applicationState =
                        Application.State.values()[state];
                Application application =
                        new Application(account, pet, applicationState);
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
        String query = "SELECT * FROM APPLICATION WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            setValues(pstmt, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int mid = rs.getInt("Mid");
                int pid = rs.getInt("Pid");
                int state = rs.getInt("State");
                Member account = selectMemberById(mid);
                Pet pet = selectPetById(pid);
                Application.State applicationState =
                        Application.State.values()[state];
                Application application =
                        new Application(account, pet, applicationState);
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
     */
    public static void changeState(final int aid,
                                   final int state) {
        if (state == 1) {
            Pet pet = DbManager.selectApplication(aid).getPet();
            int pid = pet.getId();
            String preUpdateSQL1 = "UPDATE APPLICATION SET State = ? "
                    + "WHERE Pid = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt =
                         conn.prepareStatement(preUpdateSQL1)) {
                setValues(pstmt, 2, pid);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error during Changing "
                        + "other applications State: "
                        + e.getMessage());
            }
            String preUpdateSQL2 = "UPDATE PET SET State = ? "
                    + "WHERE Id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt =
                         conn.prepareStatement(preUpdateSQL2)) {
                setValues(pstmt, 1, pid);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error during Changing pet State: ");
            }
        }
        String updateSQL = "UPDATE APPLICATION SET State = ? "
                + "WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            setValues(pstmt, state, aid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during Changing State: "
                    + e.getMessage());
        }
    }

    private static Member selectMemberById(final int mid)
            throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE Id= ?";
        Member account;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            setValues(pstmt, mid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String rUser = rs.getString("Username");
                String rPsw = rs.getString("Password");
                String rRole = rs.getString("Role");
                query = "SELECT * FROM MEMBER_PROFILE WHERE Id= ?";
                try (PreparedStatement pstmt1 = conn.prepareStatement(query)) {
                    setValues(pstmt1, mid);
                    ResultSet rs1 = pstmt1.executeQuery();
                    if (rs1.next()) {
                        String preferredSpecies =
                                rs1.getString("PreferredSpecies");
                        String preferredBreed = rs1.getString("PreferredBreed");
                        String gender = rs1.getString("Gender");
                        int[] numericAttributes =
                                {rs1.getInt("ExtroversionLevel"),
                                        rs1.getInt("DailyActivityLevel"),
                                        rs1.getInt("HouseSize"),
                                        rs1.getInt("WorkHours"),
                                        rs1.getInt("NumberOfFamilyMembers"),
                                        rs1.getInt("PreviousPetExperience"),
                                        rs1.getInt("financialBudget")};
                        MemberProfile memberProfile = new MemberProfile(
                                preferredSpecies, preferredBreed, gender,
                                numericAttributes);
                        account = new Member(rUser, rPsw, rRole, memberProfile);
                        return account;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Selects a pet by ID.
     *
     * @param petId the pet ID.
     * @return the pet, or null if not found.
     */
    public static Pet selectPetById(final int petId) {
        String selectSql = "SELECT * FROM PET WHERE Id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            setValues(pstmt, petId);
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
                int state = rs.getInt("State");
                int[] numericAttributes = {age, weight,
                        activityLevel, healthStatus};
                return new Pet(petId, name, species, breed,
                        gender, numericAttributes, state);
            } else {
                System.out.println("No pet found with the given Id");
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
                String name = rs.getString("Name");
                String species = rs.getString("Species");
                String breed = rs.getString("Breed");
                String gender = rs.getString("Gender");
                int age = rs.getInt("Age");
                int weight = rs.getInt("Weight");
                int activityLevel = rs.getInt("ActivityLevel");
                int healthStatus = rs.getInt("HealthStatus");
                int state = rs.getInt("State");
                int id = rs.getInt("Id");
                int[] numericAttributes = {age, weight,
                        activityLevel, healthStatus};
                Pet pet = new Pet(id, name, species, breed,
                        gender, numericAttributes, state);
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
     * @param member        the member to insert the profile for.
     * @param memberProfile the member profile to insert.
     * @throws SQLException if a database error occurs.
     */
    public static void insertMemProfile(final Member member,
                                        final MemberProfile memberProfile)
            throws SQLException {
        String insertSql = "INSERT INTO MEMBER_PROFILE (Id, PreferredSpecies, "
                + "PreferredBreed, ExtroversionLevel, DailyActivityLevel, "
                + "HouseSize, WorkHours, NumberofFamilyMembers, "
                + "PreviousPetExperience, FinancialBudget, Gender) VALUES (?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            setValues(pstmt, getAccountId(member),
                    memberProfile.getPreferredSpecies(),
                    memberProfile.getPreferredBreed(),
                    memberProfile.getExtroversionLevel(),
                    memberProfile.getDailyActivityLevel(),
                    memberProfile.getHouseSize(),
                    memberProfile.getWorkHours(),
                    memberProfile.getNumberOfFamilyMembers(),
                    memberProfile.getPreviousPetExperience(),
                    memberProfile.getFinancialBudget(),
                    memberProfile.getGender());
            pstmt.executeUpdate();
            System.out.println("Member profile inserted successfully");
        }
    }

    /**
     * Gets the account ID.
     *
     * @param account the account.
     * @return the account ID.
     * @throws SQLException if a database error occurs.
     */
    public static int getAccountId(final Account account) throws SQLException {
        String query = "SELECT * FROM ACCOUNT WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            setValues(pstmt, account.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        }
        return -1;
    }

    /**
     * Changes a member profile.
     *
     * @param name          the username of the member.
     * @param memberProfile the new member profile.
     */
    public static void changeMemProfile(final String name,
                                        final MemberProfile memberProfile) {
        String updateSQL = "UPDATE MEMBER_PROFILE SET PreferredSpecies = ?, "
                + "PreferredBreed = ?, ExtroversionLevel = ?, "
                + "DailyActivityLevel = ?, HouseSize = ?, WorkHours = ?, "
                + "NumberofFamilyMembers = ?, PreviousPetExperience = ?, "
                + "FinancialBudget = ?, Gender = ? WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            setValues(pstmt, memberProfile.getPreferredSpecies(),
                    memberProfile.getPreferredBreed(),
                    memberProfile.getExtroversionLevel(),
                    memberProfile.getDailyActivityLevel(),
                    memberProfile.getHouseSize(),
                    memberProfile.getWorkHours(),
                    memberProfile.getNumberOfFamilyMembers(),
                    memberProfile.getPreviousPetExperience(),
                    memberProfile.getFinancialBudget(),
                    memberProfile.getGender(), name);
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
            setValues(pstmt, getAccountId(application.getUser()),
                    application.getPet().getId(), 0);
            pstmt.executeUpdate();
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
            setValues(pstmt, getAccountId(member));
            ResultSet rs = pstmt.executeQuery();
            List<Application> applications = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int pid = rs.getInt("PId");
                int state = rs.getInt("State");
                Pet pet = selectPetById(pid);
                Application.State applicationState =
                        Application.State.values()[state];
                Application application = new Application(
                        member, pet, applicationState);
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
     * Retrieves all location points from the database.
     * This method executes a SQL query to fetch all records from the
     * "APPLICATION" table in the database. It then processes these records
     * to create a list of
     * {@link LocationPoint} objects, where each object represents a location
     * point with x and y values corresponding to the "XValue"
     * and "YValue" columns in the table.
     *
     * @return an {@link ArrayList} containing all the location points retrieved
     * from the database, or {@code null} if an error occurs
     * @throws SQLException if a database access error occurs
     */
    public static ArrayList<LocationPoint> listAllLocationPoint()
            throws SQLException {

        String query = "SELECT * FROM APPLICATION";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            ArrayList<LocationPoint> locationPoints = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("Id");
                int xValue = rs.getInt("XValue");
                int yValue = rs.getInt("YValue");

                LocationPoint locationPoint = new LocationPoint(xValue, yValue);
                locationPoints.add(locationPoint);
            }

            return locationPoints;

        } catch (SQLException e) {
            System.out.println("Error during Viewing all Applications: "
                    + e.getMessage());
            return null;
        }
    }

    private DbManager() {
        throw new AssertionError("Instantiation not allowed");
    }
}
