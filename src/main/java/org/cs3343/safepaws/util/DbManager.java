package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.LocationPoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    /**
     * Retrieves all location points from the database.
     * This method executes a SQL query to fetch all records from the
     * "APPLICATION" table in the database. It then processes these records
     * to create a list of
     * {@link LocationPoint} objects, where each object represents a location
     * point with x and y values corresponding to the "XValue"
     * and "YValue" columns in the table.
     * @return an {@link ArrayList} containing all the location points retrieved
     *         from the database, or {@code null} if an error occurs
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
