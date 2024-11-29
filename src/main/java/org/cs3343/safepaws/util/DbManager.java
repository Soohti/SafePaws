package org.cs3343.safepaws.util;

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
    //todo read father class field
    /**
     * Reads an entity from the database by its ID.
     *
     * @param <T> the type of the entity
     * @param entityType the class of the entity
     * @param tableName the name of the table
     * @param idValue the ID value of the entity
     * @return the entity with the specified ID, or null if not found
     * @throws Exception if any database or reflection error occurs
     */
    public <T> T readWithID(final Class<T> entityType,
                            final String tableName,
                            final int idValue) throws Exception {
        String sqlCommand = "SELECT * FROM " + tableName + " WHERE ID = ?;";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sqlCommand)) {
            statement.setInt(1, idValue);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return buildEntityFromResultSet(entityType, resultSet);
                }
            }
        }
        return null;
    }
    /**
     * Reads entities from the database that match the specified conditions.
     *
     * @param <T> the type of the entity
     * @param entityType the class of the entity
     * @param tableName the name of the table
     * @param conditions the conditions to match
     * @return a list of entities that match the conditions
     * @throws Exception if any database or reflection error occurs
     */
    public <T> List<T> readWithCondition(final Class<T> entityType,
                                         final String tableName,
                                         final Map<String, String> conditions)
            throws Exception {
        StringBuilder sqlCommand = new StringBuilder("SELECT * FROM ")
                .append(tableName).append(" WHERE ");
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, String> condition : conditions.entrySet()) {
            sqlCommand.append(condition.getKey()).append(" = ? AND ");
            values.add(condition.getValue());
        }
        if (!conditions.isEmpty()) {
            sqlCommand.setLength(sqlCommand.length() - " AND ".length());
        }
        sqlCommand.append(";");
        List<T> entities = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement(sqlCommand.toString())) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    entities.add(buildEntityFromResultSet(
                            entityType, resultSet));
                }
            } catch (SQLException e) {
                System.out.println("executeQuery Error "
                        + e.getMessage());
            }
        }
        return entities;
    }
    /**
     * Reads all entities from the specified table.
     *
     * @param <T> the type of the entity
     * @param entityType the class of the entity
     * @param tableName the name of the table
     * @return a list of all entities in the table
     * @throws Exception if any database or reflection error occurs
     */
    public <T> ArrayList<T> readAll(final Class<T> entityType,
                                    final String tableName) throws Exception {
        ArrayList<T> entities = new ArrayList<>();
        String sqlCommand = "SELECT * FROM " + tableName + ";";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCommand)) {
            while (resultSet.next()) {
                entities.add(buildEntityFromResultSet(entityType, resultSet));
            }
        }
        return entities;
    }
    /**
     * Builds an entity from the result set.
     *
     * @param <T> the type of the entity
     * @param entityType the class of the entity
     * @param resultSet the result set containing the entity data
     * @return the built entity
     * @throws Exception if any reflection error occurs
     */
    private <T> T buildEntityFromResultSet(final Class<T> entityType,
                                           final ResultSet resultSet)
            throws Exception {
        Constructor<T> constructor;
        T entityInstance;
        try {
            constructor = entityType.getDeclaredConstructor();
            constructor.setAccessible(true);
            entityInstance = constructor.newInstance();
        } catch (Exception e) {
            throw new Exception("Error initializing entity instance for "
                    + entityType.getName(), e);
        }
        for (Field field : entityType.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.isAnnotationPresent(OneToOne.class)) {
                    handleOneToOneField(field, resultSet, entityInstance);
                } else if (field.getType().isEnum()) {
                    handleEnumField(field, resultSet, entityInstance);
                } else {
                    handleRegularField(field, resultSet, entityInstance);
                }
            } catch (Exception e) {
                System.err.println("Error setting field "
                        + field.getName() + ": " + e.getMessage());
            }
        }
        return entityInstance;
    }
    /**
     * Gets the setter method for the specified field.
     *
     * @param builderClass the class of the builder
     * @param field the field for which to get the setter method
     * @return the setter method, or null if not found
     * @throws NoSuchMethodException if the setter method is not found
     */
    private Method getSetterMethod(final Class<?> builderClass,
                                   final Field field)
            throws NoSuchMethodException {
        try {
            return builderClass.getMethod("set"
                    + capitalize(field.getName()), field.getType());
        } catch (NoSuchMethodException e) {
            System.err.println("No setter for field " + field.getName());
            return null;
        }
    }
    /**
     * Handles a one-to-one field.
     *
     * @param field the field to handle
     * @param resultSet the result set containing the field data
     * @param entityInstance the object instance
     * @throws Exception if any database or reflection error occurs
     */
    private void handleOneToOneField(final Field field,
                                     final ResultSet resultSet,
                                     final Object entityInstance)
            throws Exception {
        OneToOne oneToOne = field.getAnnotation(OneToOne.class);
        Integer foreignKeyId = resultSet.getInt(oneToOne.columnName());
        if (resultSet.wasNull()) {
            field.set(entityInstance, null);
        } else {
            Object relatedEntity = readWithID(field.getType(),
                    oneToOne.tableName(), foreignKeyId);
            field.set(entityInstance, relatedEntity);
        }
    }
    /**
     * Handles an enum field.
     *
     * @param field the field to handle
     * @param resultSet the result set containing the field data
     * @param entityInstance the object instance
     * @throws Exception if any reflection error occurs
     */
    private void handleEnumField(final Field field,
                                 final ResultSet resultSet,
                                 final Object entityInstance)
            throws Exception {
        int ordinal = resultSet.getInt(field.getName());
        if (resultSet.wasNull()) {
            field.set(entityInstance, null);
        } else {
            Enum<?> enumValue = (Enum<?>) field.getType()
                    .getEnumConstants()[ordinal];
            field.set(entityInstance, enumValue);
        }
    }
    /**
     * Handles a regular field.
     *
     * @param field the field to handle
     * @param resultSet the result set containing the field data
     * @param entityInstance the object instance
     * @throws Exception if any reflection error occurs
     */
    private void handleRegularField(final Field field,
                                    final ResultSet resultSet,
                                    final Object entityInstance)
            throws Exception {
        Object value = resultSet.getObject(field.getName());
        if (resultSet.wasNull()) {
            value = null;
        }
        field.set(entityInstance, value);
    }
    /**
     * Updates records in the specified table based on the given conditions.
     *
     * @param <T> the type of the entity
     * @param entityType the class of the entity
     * @param tableName the name of the table
     * @param whereFields the conditions for the WHERE clause
     * @param setFields the fields and values to set in the SET clause
     * @throws Exception if any database or reflection error occurs
     */
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
                sqlCommand.setLength(sqlCommand.length()
                            - LENGTH_OF_LAST_AND);
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
    /**
     * Inserts a new record into the specified table.
     *
     * @param <T> the type of the entity
     * @param entity the entity to insert
     * @param tableName the name of the table
     * @throws Exception if any database or reflection error occurs
     */
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
    /**
     * Inserts a new record into the specified table with auto-generated values.
     *
     * @param insertField the fields and values to insert
     * @param tableName the name of the table
     * @throws Exception if any database error occurs
     */
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
    /**
     * Capitalizes the first letter of the given string.
     *
     * @param str the string to capitalize
     * @return the capitalized string
     */
    private String capitalize(final String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    /**
     * Retrieves a map of field names and their corresponding
     * values from the given entity.
     *
     * @param <T> the type of the entity
     * @param entity the entity from which to retrieve field names and values
     * @return a map where the keys are field names and the values are
     * the corresponding field values
     * @throws IllegalAccessException if the field is not accessible
     */
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
}
