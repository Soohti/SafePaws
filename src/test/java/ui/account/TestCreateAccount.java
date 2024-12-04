package ui.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.CreateAccount;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCreateAccount {
    Connection conn;
    @BeforeEach
    public void setUp() throws Exception {
        Properties serverProperties = new Properties();
        final String SERVER_PROPERTIES_PATH = "conf/server/server.properties";

        try (FileInputStream input = new FileInputStream(SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");

        DbManager.init(dbUrl, dbUsername, dbPassword);
        conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    /**
     * Test create account V 1.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testCreateAccountV1() throws SQLException {
        String inputs="Blackkkk2\nBlackkkk2\nM\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        CreateAccount createAccount =new CreateAccount(referrer);
        UI nextUI = createAccount.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Create Account", createAccount.getName());
        String preUpdateSQL1 = "SELECT * FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt1 =
                conn.prepareStatement(preUpdateSQL1);
        pstmt1.setString(1, "Blackkkk2");
        ResultSet rs = pstmt1.executeQuery();
        int id=0;
        if (rs.next()) {
            id = rs.getInt("Id");
        }
        String preUpdateSQL2 = "DELETE FROM MEMBER_PROFILE WHERE Id = ?";
        PreparedStatement pstmt2 =
                conn.prepareStatement(preUpdateSQL2);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
        String preUpdateSQL = "DELETE FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkkk2");
        pstmt.executeUpdate();
    }
    @Test
    public void testCreateAccountV2() throws SQLException {
        String inputs="Black\nBlackkkk2\nBlack\nBlackkkk2\nf\nM\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        CreateAccount createAccount =new CreateAccount(referrer);
        UI nextUI = createAccount.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Create Account", createAccount.getName());
        String preUpdateSQL1 = "SELECT * FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt1 =
                conn.prepareStatement(preUpdateSQL1);
        pstmt1.setString(1, "Blackkkk2");
        ResultSet rs = pstmt1.executeQuery();
        int id=0;
        if (rs.next()) {
            id = rs.getInt("Id");
        }
        String preUpdateSQL2 = "DELETE FROM MEMBER_PROFILE WHERE Id = ?";
        PreparedStatement pstmt2 =
                conn.prepareStatement(preUpdateSQL2);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
        String preUpdateSQL = "DELETE FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkkk2");
        pstmt.executeUpdate();
    }
    @Test
    public void testCreateAccountV3() throws SQLException {
        String inputs="memberm1\nBlackkkk2\nBlack\nBlackkkk2\nf\nM\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        CreateAccount createAccount =new CreateAccount(referrer);
        UI nextUI = createAccount.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Create Account", createAccount.getName());
        String preUpdateSQL1 = "SELECT * FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt1 =
                conn.prepareStatement(preUpdateSQL1);
        pstmt1.setString(1, "Blackkkk2");
        ResultSet rs = pstmt1.executeQuery();
        int id=0;
        if (rs.next()) {
            id = rs.getInt("Id");
        }
        String preUpdateSQL2 = "DELETE FROM MEMBER_PROFILE WHERE Id = ?";
        PreparedStatement pstmt2 =
                conn.prepareStatement(preUpdateSQL2);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
        String preUpdateSQL = "DELETE FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkkk2");
        pstmt.executeUpdate();
    }
    @Test
    public void testCreateAccountV4() throws SQLException {
        String inputs="Black\nBlackkkk2\nBlack\nBlackkkk2\nf\nS\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        CreateAccount createAccount =new CreateAccount(referrer);
        UI nextUI = createAccount.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Create Account", createAccount.getName());
        String preUpdateSQL1 = "SELECT * FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt1 =
                conn.prepareStatement(preUpdateSQL1);
        pstmt1.setString(1, "Blackkkk2");
        ResultSet rs = pstmt1.executeQuery();
        int id=0;
        if (rs.next()) {
            id = rs.getInt("Id");
        }
        String preUpdateSQL2 = "DELETE FROM SHELTER_LOCATION WHERE Id = ?";
        PreparedStatement pstmt2 =
                conn.prepareStatement(preUpdateSQL2);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
        String preUpdateSQL = "DELETE FROM ACCOUNT WHERE Username = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setString(1, "Blackkkk2");
        pstmt.executeUpdate();
    }
}
