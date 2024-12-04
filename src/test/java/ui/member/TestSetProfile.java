package ui.member;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.member.SetProfile;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSetProfile {
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
    }

    /**
     * Tests the constructor of the SetProfile class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testSetProfileConstructor() {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        // Act
        SetProfile setProfile = new SetProfile(referrer);

        // Assert
        assertNotNull(setProfile);
        assertEquals("Set Profile", setProfile.getName());
        assertEquals(referrer, setProfile.getReferrer());
    }

    /**
     * Tests the getNextUI method of the SetProfile class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV1() throws Exception {
        // Arrange
        String simulatedInput = "5\n5\n100\n8\n4\n7\n5000\nDog\nBeagle\nf\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        Account account = new Account(1, "username", "password", "M");
        session.setAccount(account);

        SetProfile setProfile = new SetProfile(referrer);
        // Act
        UI nextUI = setProfile.getNextUI(session);

        // Assert
        assertEquals("Set Profile", setProfile.getName());

    }
    /**
     * Tests the getNextUI method of the SetProfile class.
     * Simulates user input with
     * invalid and valid data and verifies the expected output.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetNextUIV2() throws Exception {
        // Arrange
        String simulatedInput = "5\n5"
                + "\n100\n8\n4\n7"
                + "\n5000"
                + "\nDoggggggggggggggggggggggggggggg"
                + "\nDog"
                + "\nBeagleeeeeeeeeeeeeeeeeeeeeeeeee"
                + "\nBeagle"
                + "\nfemale\nf\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        Session session = new Session(System.in, System.out);
        Account account = new Account(1, "username", "password", "M");
        session.setAccount(account);

        SetProfile setProfile = new SetProfile(referrer);

        // Act
        UI nextUI = setProfile.getNextUI(session);

        // Assert
        assertEquals("Set Profile", setProfile.getName());
    }
}
