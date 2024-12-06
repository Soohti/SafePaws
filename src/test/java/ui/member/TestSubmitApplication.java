package ui.member;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.member.SubmitApplication;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@link SubmitApplication} class.
 */
public class TestSubmitApplication {

    Connection conn;
    /**
     * Constant for the ID1 used in tests.
     */
    private static final int ID1 = 22;
    /**
     * Constant for the ID2 used in tests.
     */
    private static final int ID2 = 100;
    /**
     * Path to the server properties file.
     */
    private static final String SERVER_PROPERTIES_PATH
            = "conf/server/server.properties";

    /**
     * Sets up the test environment by initializing the database
     * connection and redirecting the system output.
     *
     * @throws Exception if an error occurs during setup
     */
    @BeforeEach
    public void setUp() throws Exception {
        Properties serverProperties = new Properties();

        try (FileInputStream input = new
                FileInputStream(SERVER_PROPERTIES_PATH)) {
            serverProperties.load(input);
        }

        String dbUrl = serverProperties.getProperty("db.url");
        String dbUsername = serverProperties.getProperty("db.username");
        String dbPassword = serverProperties.getProperty("db.password");

        DbManager.init(dbUrl, dbUsername, dbPassword);
        conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterEach
    public void tearDown() throws Exception {
        conn.close();
    }

    /**
     * Tests the constructor of the {@link SubmitApplication} class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testConstructor() {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        // Act
        SubmitApplication submitApplication = new SubmitApplication(referrer);

        // Assert
        assertNotNull(submitApplication);
        assertEquals("Submit an application", submitApplication.getName());
        assertEquals(referrer, submitApplication.getReferrer());
    }

    /**
     * Tests the getNextUI method of the {@link SubmitApplication} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV1() throws Exception {
        // Arrange
        String simulatedInput = "22\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Member member = new Member();
        member.setId(ID1);
        member.setUsername("newUsername");
        member.setPassword("newPassword");
        member.setRole("M");
        MemberProfile profile = new MemberProfile(ID1, "Cat", "Siamese", "f");
        member.setProfile(profile);
        session.setAccount(member);
        SubmitApplication submitApplication = new SubmitApplication(referrer);

        // Act
        UI nextUI = submitApplication.getNextUI(session);

        // Assert
        assertNotNull(nextUI);
        assertEquals("Submit an application", submitApplication.getName());
        String preUpdateSQL = "DELETE FROM APPLICATION WHERE MId = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setInt(1, ID1);
        pstmt.executeUpdate();
    }

    /**
     * Tests the getNextUI method of the {@link SubmitApplication} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV2() throws Exception {
        // Arrange
        String simulatedInput = "16\n22\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Member member = new Member();
        member.setId(ID1);
        member.setUsername("newUsername");
        member.setPassword("newPassword");
        member.setRole("M");
        MemberProfile profile = new MemberProfile(ID1, "Cat", "Siamese", "f");
        member.setProfile(profile);
        session.setAccount(member);
        SubmitApplication submitApplication = new SubmitApplication(referrer);

        // Act
        UI nextUI = submitApplication.getNextUI(session);

        // Assert
        assertNotNull(nextUI);
        assertEquals("Submit an application", submitApplication.getName());
        String preUpdateSQL = "DELETE FROM APPLICATION WHERE MId = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setInt(1, ID1);
        pstmt.executeUpdate();
    }

    /**
     * Tests the getNextUI method of the {@link SubmitApplication} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV3() throws Exception {
        // Arrange
        String simulatedInput = "16\n22\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Member member = new Member();
        member.setId(ID2);
        member.setUsername("newUsername");
        member.setPassword("newPassword");
        member.setRole("M");
        MemberProfile profile = new MemberProfile(ID2, "Cat", "Siamese", "f");
        member.setProfile(profile);
        session.setAccount(member);
        SubmitApplication submitApplication = new SubmitApplication(referrer);

        // Act
        UI nextUI = submitApplication.getNextUI(session);

        // Assert
        assertNotNull(nextUI);
        assertEquals("Submit an application", submitApplication.getName());
        String preUpdateSQL = "DELETE FROM APPLICATION WHERE MId = ?";
        PreparedStatement pstmt =
                conn.prepareStatement(preUpdateSQL);
        pstmt.setInt(1, ID2);
        pstmt.executeUpdate();
    }
}
