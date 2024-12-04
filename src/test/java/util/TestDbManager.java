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

    @Test
    public void testCaseV1() throws Exception {

        testInit();
        testTableABInit();
        TestClassC testClassCAns = new TestClassC(1);
        TestClassB testAns = new TestClassB(1,testClassCAns, TestClassB.TestState.TestA,1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.ChangeV,
                "1",TableSchema.Column.State, "0"),TableSchema.Name.TestTableA);
        DbManager.insert(testClassCAns,TableSchema.Name.TestTableB);
        TestClassB testRes = DbManager
                .readWithCondition(TestClassB.class, TableSchema.Name.TestTableA,
                        Map.of(TableSchema.Column.Id,"1")).getFirst();
        testTableABClean();
        assertEquals(testAns,testRes);
    }

    @Test
    public void testCaseV2() throws Exception {

        testInit();
        testTableABInit();
        TestClassC testClassCAns = new TestClassC(1);
        TestClassB testAns = new TestClassB(1,testClassCAns, TestClassB.TestState.TestA,-1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.ChangeV,
                "1",TableSchema.Column.State, "0"),TableSchema.Name.TestTableA);
        DbManager.insert(testClassCAns,TableSchema.Name.TestTableB);
        testAns.setChangeV(10);
        DbManager.update(TestClassB.class, TableSchema.Name.TestTableA,
                Map.of(TableSchema.Column.Id,"1"),
                Map.of(TableSchema.Column.ChangeV,"10")
        );
        TestClassB testRes = DbManager
                .readAll(TestClassB.class, TableSchema.Name.TestTableA).getFirst();
        testTableABClean();
        assertEquals(testAns,testRes);
    }

    @Test
    public void testCaseV3() throws Exception {

        testInit();
        testTableCDelete();
        testTableCCreate();
        TestClassD testAns = new TestClassD("test");
        testAns.setId(1);
        DbManager.insertWithAutoValue(Map.of(TableSchema.Column.Username,testAns.getName()),
                TableSchema.Name.TestTableC);
        TestClassD testRes = DbManager
                .readAll(TestClassD.class, TableSchema.Name.TestTableC).getFirst();
        testTableCDelete();
        assertEquals(testAns,testRes);
    }




    private static String TestSqlCREATEA = "create table TestTableA\n"
            + "(\n"
            + "    Id    int auto_increment\n"
            + "        primary key,\n"
            + "    ChangeV int null,\n"
            + "    State int null\n"
            + ");";

    private static String TestSqlCREATEB = "create table TestTableB\n"
            + "(\n"
            + "    Id int not null\n"
            + "        primary key,\n"
            + "    constraint TestTableB_TestTableA_Id_fk\n"
            + "        foreign key (Id) references TestTableA (Id)\n"
            + ");";

    private static String TestSqlCREATEC ="CREATE TABLE TestTableC (\n"
            + "    Id INT PRIMARY KEY AUTO_INCREMENT,\n"
            + "    Username VARCHAR(50)"
            + ");";

    private static String TestSqlDROPA =  "DROP TABLE if exists TestTableA;";

    private static String TestSqlDROPB =  "DROP TABLE if exists TestTableB";

    private static String TestSqlDROPC = "DROP TABLE if exists TestTableC;";


    private static String dbUrl;

    private static String dbUsername;

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
            statement.execute(TestSqlCREATEA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableADelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(TestSqlDROPA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableBCreate() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(TestSqlCREATEB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableBDelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(TestSqlDROPB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableCCreate() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(TestSqlCREATEC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTableCDelete() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(TestSqlDROPC);
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

