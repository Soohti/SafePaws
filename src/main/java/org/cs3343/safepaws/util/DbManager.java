package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * the length of the last AND in sql command.
     */
    private static final int LENGTH_OF_LAST_AND = 5;
    /**
     * the length of the last command in sql command.
     */
    private static final int LENGTH_OF_LAST_COMMA = 2;
    /**
     * Singleton instance of DbManager.
     */
    private static DbManager instance;
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
        if (instance == null) {
            instance = new DbManager();
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(TEST_SQL)) {
                pstmt.execute();
                System.out.println("Database connection established");
            }
        }
    }
    /**
     * Returns the singleton instance of DbManager.
     *
     * @return The singleton instance.
     * @throws IllegalStateException If the instance has not been initialized.
     */
    public static DbManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DbManager has "
                    + "not been initialized. Call init() first.");
        }
        return instance;
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

    private DbManager() {

    }

    public <T> T readWithID(final Class<T> entityType,
                            final String tableName, final int idName)
            throws Exception {
        StringBuilder sqlCommand = new StringBuilder("SELECT * FROM "
                + tableName + " WHERE ID = " + idName + ";");
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement
                     .executeQuery(sqlCommand.toString())) {
            Class<?> builderClass = Class.forName(
                    entityType.getName() + "$Builder");
            Constructor<?> builderConstructor = builderClass
                    .getDeclaredConstructor();
            builderConstructor.setAccessible(true);
            Object builderInstance = builderClass.getDeclaredConstructor()
                    .newInstance();
            if (resultSet.next()) {
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Method setter = builderClass.getMethod("set"
                                + capitalize(field.getName()), field.getType());
                        Object value = resultSet.getObject(field.getName());
                        if (field.getType().isEnum()) {
                            int ordinal = resultSet.getInt(field.getName());
                            value = (Enum<?>) field.getType()
                                    .getEnumConstants()[ordinal];
                            setter.invoke(builderInstance, value);
                        } else {
                             value = resultSet.getObject(field.getName());
                             setter = builderClass.getMethod("set"
                                    + capitalize(field.getName()), field.getType());
                            setter.invoke(builderInstance, value);
                        }

                    } catch (NoSuchMethodException | IllegalAccessException
                             | SQLException e) {
                        System.err.println("Error setting field "
                                + field.getName() + ": " + e.getMessage());
                    }
                }
                Method buildMethod = builderClass.getMethod("build");
                return (T) buildMethod.invoke(builder);
            }
        }
        return null;
    }
    public <T> List<T> readWithCondition(
            final Class<T> entityType,
            final String tableName,
            final Map<String, String> conditions)
            throws Exception {
        StringBuilder sqlCommand = new StringBuilder("SELECT * FROM ")
                .append(tableName).append(" WHERE ");
        List<T> entities = new ArrayList<>();

        // 构建SQL查询条件
        for (Map.Entry<String, String> condition : conditions.entrySet()) {
            sqlCommand.append(condition.getKey()).append(" = '")
                    .append(condition.getValue()).append("' AND ");
        }
        if (!conditions.isEmpty()) {
            sqlCommand.setLength(sqlCommand.length() - " AND ".length());
        }
        sqlCommand.append(";");

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCommand.toString())) {\
            Object builderInstance = builderConstructor.newInstance();
            while (resultSet.next()) {
                Class<?> builderClass = Class.forName(
                        entityType.getName() + "$Builder");
                Object builder = builderClass.getDeclaredConstructor()
                        .newInstance();
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    String columnName = field.getName(); // 假设列名与字段名相同

                    // 获取构建器对应的setter方法
                    Method setter = builder.getClass().getMethod("set" + capitalize(field.getName()), field.getType());

                    // 读取并设置字段值
                    Object value = resultSet.getObject(columnName);
                    if (value != null) {
                        setter.invoke(builder, value);
                    }
                }
                // 通过构建器创建实体实例
                T entity = constructor.newInstance((Builder) builder);
                entities.add(entity);
            }
        }
        return entities;
    }
    public <T> ArrayList<T> readAll(final Class<T> entityType,
                                    final String tableName) throws Exception {
        ArrayList<T> entities = new ArrayList<>();
        StringBuilder sqlCommand = new StringBuilder("SELECT * FROM ")
                .append(tableName).append(";");
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement
                     .executeQuery(sqlCommand.toString())) {
            Class<?> builderClass = Class.forName(
                    entityType.getName() + "$Builder");
            Constructor<?> builderConstructor = builderClass
                    .getDeclaredConstructor();
            builderConstructor.setAccessible(true);
            while (resultSet.next()) {
                Object builderInstance = builderConstructor.newInstance();
                for (Field field : entityType.getDeclaredFields()) {
                    field.setAccessible(true);
                    String columnName = field.getName();
                    if (field.isAnnotationPresent(OneToOne.class)) {
                        OneToOne oneToOne = field.getAnnotation(OneToOne.class);
                        Integer foreignKeyId = resultSet.getInt(
                                oneToOne.columnName());
                        Object relatedEntity = readWithID(
                                field.getType(),
                                oneToOne.tableName(),
                                foreignKeyId);
                        Method setter = builderClass.getMethod("set"
                                + capitalize(field.getName()), field.getType());
                        setter.invoke(builderInstance, relatedEntity);
                    } else if (field.getType().isEnum()) {
                        int ordinal = resultSet.getInt(columnName);
                        Enum<?> enumValue = (Enum<?>) field.getType()
                                .getEnumConstants()[ordinal];
                        Method setter = builderClass.getMethod("set"
                                + capitalize(field.getName()), field.getType());
                        setter.invoke(builderInstance, enumValue);
                    } else {
                        Object value = resultSet.getObject(columnName);
                        Method setter = builderClass.getMethod("set"
                                + capitalize(field.getName()), field.getType());
                        setter.invoke(builderInstance, value);
                    }
                }
                Method buildMethod = builderClass.getMethod("build");
                T entity = (T) buildMethod.invoke(builderInstance);
                entities.add(entity);
            }
        }
        return entities;
    }
    public <T> void update(final Class<T> entityType,
                                  final String tableName,
                                  final Map<String, String> whereFields,
                                  final Map<String, String> setFields)
            throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            StringBuilder sqlCommand = new StringBuilder("UPDATE "
                    + tableName + " SET ");
            List<Object> parameters = new ArrayList<>();
            // Build SET clause
            for (Map.Entry<String, String> entry : setFields.entrySet()) {
                sqlCommand.append(entry.getKey()).append(" = ?, ");
                parameters.add(entry.getValue());
            }
            // Remove the last comma and space
            if (!setFields.isEmpty()) {
                sqlCommand.setLength(sqlCommand.length()
                        - LENGTH_OF_LAST_COMMA);
            }
            // Build WHERE clause
            if (!whereFields.isEmpty()) {
                sqlCommand.append(" WHERE ");
                for (Map.Entry<String, String> entry : whereFields.entrySet()) {
                    sqlCommand.append(entry.getKey()).append(" = ? AND ");
                    parameters.add(entry.getValue());
                }
                // Remove the last " AND "
                if (!whereFields.isEmpty()) {
                    sqlCommand.setLength(sqlCommand.length()
                            - LENGTH_OF_LAST_AND);
                }
            }
            sqlCommand.append(";");
            statement = conn.prepareStatement(sqlCommand.toString());
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new Exception("Failed to update database", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public <T> void insert(final T entity,
                           final String tableName) throws Exception {
        Map<String, Object> fieldsAndValues = getFieldsAndValues(entity);
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String columnName : fieldsAndValues.keySet()) {
            columns.append(columnName).append(", ");
        }
        if (columns.length() > 0) {
            columns.setLength(columns.length() - 2);
        }
        for (int i = 0; i < fieldsAndValues.size(); i++) {
            values.append("?");
            if (i < fieldsAndValues.size() - 1) {
                values.append(", ");
            }
        }
        String sqlCommand = "INSERT INTO " + tableName + " ("
                + columns.toString() + ") VALUES (" + values.toString() + ");";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlCommand.toString())) {
            int index = 1;
            for (Object value : fieldsAndValues.values()) {
                preparedStatement.setObject(index++, value);
            }
            preparedStatement.executeUpdate();
        }
    }
    public void insertWithAutoValue(final Map<String, String> insertField,
                                  final String tableName) throws Exception {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Object> valueList = new ArrayList<>();
        for (Map.Entry<String, String> entry : insertField.entrySet()) {
            columns.append(entry.getKey()).append(", ");
            values.append("?");
            if (entry.getValue() != null) {
                valueList.add(entry.getValue());
            } else {
                valueList.add(null);
            }
            values.append(", ");
        }
        if (!columns.isEmpty() && !values.isEmpty()) {
            columns.setLength(columns.length() - LENGTH_OF_LAST_COMMA);
            values.setLength(values.length() - LENGTH_OF_LAST_COMMA);
        }
        String sqlCommand = "INSERT INTO " + tableName + " ("
                + columns.toString() + ") VALUES (" + values.toString() + ");";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlCommand)) {
            for (int i = 0; i < valueList.size(); i++) {
                preparedStatement.setObject(i + 1, valueList.get(i));
            }
            preparedStatement.executeUpdate();
        }
    }
    private String capitalize(final String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    private static <T> Map<String, Object> getFieldsAndValues(final T entity)
            throws IllegalAccessException {
        Map<String, Object> fieldsAndValues = new HashMap<>();
        Class<?> clazz = entity.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(entity);
                fieldsAndValues.put(fieldName, fieldValue);
            }
        }
        return fieldsAndValues;
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
     * Changes the state of an application.
     *
     * @param aid   the application ID.
     * @param state the new state.
     */
    public static void changeState(final int aid,
                                   final Application.State state) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            if (state == Application.State.APPROVED) {
                Pet pet = DbManager.selectApplication(aid).getPet();
                int pid = pet.getId();
                String preUpdateSQL1 = "UPDATE APPLICATION SET State = ? "
                        + "WHERE Pid = ?";
                try (PreparedStatement pstmt =
                             conn.prepareStatement(preUpdateSQL1)) {
                    setValues(pstmt, Application.State.REJECTED.ordinal(), pid);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error during Changing "
                            + "other applications State: "
                            + e.getMessage());
                    throw e;
                }
                String preUpdateSQL2 = "UPDATE PET SET State = ? "
                        + "WHERE Id = ?";
                try (PreparedStatement pstmt =
                             conn.prepareStatement(preUpdateSQL2)) {
                    setValues(pstmt, Application.State.APPROVED.ordinal(), pid);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error during Changing pet State: ");
                    throw e;
                }
            }
            String updateSQL = "UPDATE APPLICATION SET State = ? "
                    + "WHERE Id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                setValues(pstmt, state.ordinal(), aid);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error during Changing State: "
                        + e.getMessage());
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Reverting all changes...");
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ignored) {
            }
        } finally {
            try {
                if (conn != null) {
                    conn.commit();
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error during Committing changes: "
                        + e.getMessage());
            }
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

        String query = "SELECT * FROM LOCATION_POINT";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {

            ResultSet rs = stmt.executeQuery(query);

            ArrayList<LocationPoint> locationPoints = new ArrayList<>();

            while (rs.next()) {
                int xValue = rs.getInt("xValue");
                int yValue = rs.getInt("yValue");

                LocationPoint locationPoint = new LocationPoint(xValue, yValue);
                locationPoints.add(locationPoint);
            }

            return locationPoints;

        } catch (SQLException e) {
            System.out.println("Error during Viewing all Location Point: "
                    + e.getMessage());
            return null;
        }
    }


}
