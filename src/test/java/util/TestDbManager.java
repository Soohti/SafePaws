package util;

import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDbManager {

    /**
     * Tests the insertWithAutoValue, insert and readWithCondition
     * method of DbManager.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testCase1() throws Exception {

        testInit();
        testTableABInit();
        TestClassC testClassCAns = new TestClassC(1);
        TestClassB testAns = new TestClassB(1, testClassCAns,
                TestClassB.TestState.TestA, 1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.ChangeV,
                        "1", TableSchema.Column.State, "0"),
                TableSchema.Name.TestTableA);
        DbManager.insert(testClassCAns, TableSchema.Name.TestTableB);
        TestClassB testRes = DbManager
                .readWithCondition(TestClassB.class,
                        TableSchema.Name.TestTableA,
                        Map.of(TableSchema.Column.Id, "1")).getFirst();
        testTableABClean();
        assertEquals(testAns, testRes);
    }

    /**
     * Tests the insertWithAutoValue, insert and update method of DbManager.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testCase2() throws Exception {

        testInit();
        testTableABInit();
        TestClassC testClassCAns = new TestClassC(1);
        TestClassB testAns = new TestClassB(1, testClassCAns,
                TestClassB.TestState.TestA, -1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.ChangeV,
                        "1", TableSchema.Column.State, "0"),
                TableSchema.Name.TestTableA);
        DbManager.insert(testClassCAns, TableSchema.Name.TestTableB);
        testAns.setChangeV(ANSCHANGEVALUE);
        DbManager.update(TestClassB.class, TableSchema.Name.TestTableA,
                Map.of(TableSchema.Column.Id, "1"),
                Map.of(TableSchema.Column.ChangeV, "10")
        );
        TestClassB testRes = DbManager
                .readAll(TestClassB.class,
                        TableSchema.Name.TestTableA).getFirst();
        testTableABClean();
        assertEquals(testAns, testRes);
    }

    /**
     * Tests the insertWithAutoValue and readAll method of DbManager.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testCase3() throws Exception {

        testInit();
        testTableCDelete();
        testTableCCreate();
        TestClassD testAns = new TestClassD("test");
        testAns.setId(1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.Username,
                        testAns.getName()),
                TableSchema.Name.TestTableC);
        TestClassD testRes = DbManager
                .readAll(TestClassD.class,
                        TableSchema.Name.TestTableC).getFirst();
        testTableCDelete();
        assertEquals(testAns, testRes);
    }

    /**
     * The ans of change value for testcase 2.
     */
    private static final int ANSCHANGEVALUE = 10;
    /**
     * The sql command for create test table A.
     */
    private static String testSqlCREATEA = "create table TestTableA\n"
            + "(\n"
            + "    Id    INTEGER \n"
            + "        primary key AUTOINCREMENT,\n"
            + "    ChangeV int null,\n"
            + "    State int null\n"
            + ");";
    /**
     * The sql command for create test table B.
     */
    private static String testSqlCREATEB = "create table TestTableB\n"
            + "(\n"
            + "    Id int not null\n"
            + "        primary key,\n"
            + "    constraint TestTableB_TestTableA_Id_fk\n"
            + "        foreign key (Id) references TestTableA (Id)\n"
            + ");";
    /**
     * The sql command for create test table C.
     */
    private static String testSqlCREATEC = "CREATE TABLE TestTableC (\n"
            + "    Id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + "    Username VARCHAR(50)"
            + ");";
    /**
     * The sql command for drop test table A.
     */
    private static String testSqlDROPA = "DROP TABLE if exists TestTableA;";
    /**
     * The sql command for drop test table B.
     */
    private static String testSqlDROPB = "DROP TABLE if exists TestTableB";
    /**
     * The sql command for drop test table C.
     */
    private static String testSqlDROPC = "DROP TABLE if exists TestTableC;";

    /**
     * The url of database.
     */
    private static String dbUrl;
    /**
     * The username of database.
     */
    private static String dbUsername;
    /**
     * The password of database.
     */
    private static String dbPassword;

    private void testInit() {
        Properties serverProperties = new Properties();
        try (FileInputStream input = new FileInputStream(
                "conf/server/server.properties")) {
            serverProperties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dbUrl = serverProperties.getProperty("db.url");
        dbUsername = serverProperties.getProperty("db.username");
        dbPassword = serverProperties.getProperty("db.password");
        try {
            DbManager.init(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    private void testTableACreate() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlCREATEA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableADelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlDROPA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableBCreate() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlCREATEB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableBDelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlDROPB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableCCreate() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlCREATEC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableCDelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(testSqlDROPC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableABInit() {
        testTableBDelete();
        testTableADelete();
        testTableACreate();
        testTableBCreate();
    }

    private void testTableABClean() {
        testTableBDelete();
        testTableADelete();
    }

}

