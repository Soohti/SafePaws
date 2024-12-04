package ui.shelter;

import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.shelter.SetLocation;
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
 * Test class for the {@link SetLocation} class.
 */
public class TestSetLocation {
    /**
     * Constant for the ID used in tests.
     */
    private static final int ID = 26;
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
     * Tests the constructor of the {@link SetLocation} class.
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
        SetLocation setLocation = new SetLocation(referrer);

        // Assert
        assertNotNull(setLocation);
        assertEquals("Set Location", setLocation.getName());
        assertEquals(referrer, setLocation.getReferrer());
    }

    /**
     * Tests the getNextUI method of the {@link SetLocation} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV1() throws Exception {
        // Arrange
        String simulatedInput = "1\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation();
        Shelter shelter = new Shelter(ID, "Shelter",
                "Password", "S", shelterLocation);
        session.setAccount(shelter);
        SetLocation setLocation = new SetLocation(referrer);

        // Act
        UI nextUI = setLocation.getNextUI(session);

        // Assert
        assertEquals("Referrer", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link SetLocation} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV2() throws Exception {
        // Arrange
        String simulatedInput = "101\n1\n1\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation();
        Shelter shelter = new Shelter(ID, "Shelter",
                "Password", "S", shelterLocation);
        session.setAccount(shelter);
        SetLocation setLocation = new SetLocation(referrer);

        // Act
        UI nextUI = setLocation.getNextUI(session);

        // Assert
        assertEquals("Set Location", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link SetLocation} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV3() throws Exception {
        // Arrange
        String simulatedInput = "-1\n1\n1\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation();
        Shelter shelter = new Shelter(ID, "Shelter",
                "Password", "S", shelterLocation);
        session.setAccount(shelter);
        SetLocation setLocation = new SetLocation(referrer);

        // Act
        UI nextUI = setLocation.getNextUI(session);

        // Assert
        assertEquals("Set Location", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link SetLocation} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV4() throws Exception {
        // Arrange
        String simulatedInput = "1\n101\n1\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation();
        Shelter shelter = new Shelter(ID, "Shelter",
                "Password", "S", shelterLocation);
        session.setAccount(shelter);
        SetLocation setLocation = new SetLocation(referrer);

        // Act
        UI nextUI = setLocation.getNextUI(session);

        // Assert
        assertEquals("Set Location", nextUI.getName());
    }

    /**
     * Tests the getNextUI method of the {@link SetLocation} class.
     * Simulates user input and verifies the expected output.
     */
    @Test
    public void testGetNextUIV5() throws Exception {
        // Arrange
        String simulatedInput = "a\na\n1\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        Session session = new Session(System.in, System.out);
        ShelterLocation shelterLocation = new ShelterLocation();
        Shelter shelter = new Shelter(ID, "Shelter",
                "Password", "S", shelterLocation);
        session.setAccount(shelter);
        SetLocation setLocation = new SetLocation(referrer);

        // Act
        UI nextUI = setLocation.getNextUI(session);

        // Assert
        assertEquals("Set Location", nextUI.getName());
    }

    /**
     * Tests the toString method of the {@link ShelterLocation} class.
     * Ensures that the method returns the correct string representation.
     */
    @Test
    public void testToString() {
        // Arrange
        ShelterLocation location = new ShelterLocation(1, 1, 1);

        // Act
        String result = location.toString();

        // Assert
        assertEquals("(1.0, 1.0)", result);
    }

    /**
     * Tests the clone method of the {@link ShelterLocation} class.
     * Ensures that the cloned object is equal to the original.
     */
    @Test
    public void testClone() {
        // Arrange
        ShelterLocation location = new ShelterLocation(1, 1, 1);

        // Act
        ShelterLocation clonedLocation = location.clone();

        // Assert
        assertEquals(location.getXValue(), clonedLocation.getXValue());
        assertEquals(location.getYValue(), clonedLocation.getYValue());
        assertEquals(location.getId(), clonedLocation.getId());
    }
}
