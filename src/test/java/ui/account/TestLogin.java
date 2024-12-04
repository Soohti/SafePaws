package ui.account;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Login;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@link Login} class.
 */
public class TestLogin {
    /**
     * Path to the server properties file.
     */
    private static final String SERVER_PROPERTIES_PATH =
            "conf/server/server.properties";

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
    }

    /**
     * Tests the constructor of the {@link Login} class.
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
        Login login = new Login(referrer);

        // Assert
        assertNotNull(login);
        assertEquals("Log In", login.getName());
        assertEquals(referrer, login.getReferrer());
    }

    /**
     * Tests the getNextUI method of the {@link Login} class.
     * Simulates admin input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV1() throws Exception {
        // Arrange
        String simulatedInput = "adminaaa\nadminaaa\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Login login = new Login(referrer);

        // Act
        UI nextUI = login.getNextUI(session);

        // Assert
        assertEquals("Admin Menu", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link Login} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV2() throws Exception {
        // Arrange
        String simulatedInput = "memberm1\nmemberm1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Login login = new Login(referrer);

        // Act
        UI nextUI = login.getNextUI(session);

        // Assert
        assertEquals("Member Menu", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link Login} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV3() throws Exception {
        // Arrange
        String simulatedInput = "shelter1\nshelter1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Login login = new Login(referrer);

        // Act
        UI nextUI = login.getNextUI(session);

        // Assert
        assertEquals("Shelter Menu", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link Login} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV4() throws Exception {
        // Arrange
        String simulatedInput = "adminaaa\nadminaa\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Login login = new Login(referrer);

        // Act
        UI nextUI = login.getNextUI(session);

        // Assert
        assertEquals("Referrer", nextUI.getName());
    }
}
