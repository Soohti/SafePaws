package ui.member;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.member.CheckApplication;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@link CheckApplication} class.
 */
public class TestCheckApplication {
    /**
     * Constant for the ID1 used in tests.
     */
    private static final int ID1 = 21;
    /**
     * Constant for the ID2 used in tests.
     */
    private static final int ID2 = 22;
    /**
     * Constant for the ID3 used in tests.
     */
    private static final int ID3 = 5;

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
     * Tests the constructor of the {@link CheckApplication} class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testCheckApplicationConstructor() {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        // Act
        CheckApplication checkApplication = new CheckApplication(referrer);

        // Assert
        assertNotNull(checkApplication);
        assertEquals("Check submitted adoption applications",
                checkApplication.getName());
        assertEquals(referrer, checkApplication.getReferrer());
    }

    /**
     * Tests the getNextUI method of the {@link CheckApplication} class
     * when there are no applications.
     */
    @Test
    public void testGetNextUIV1() {
        // Arrange
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
        CheckApplication checkApplication = new CheckApplication(referrer);

        // Act
        UI nextUI = checkApplication.getNextUI(session);

        // Assert
        assertEquals("Check submitted adoption applications",
                checkApplication.getName());
    }

    /**
     * Tests the getNextUI method of the {@link CheckApplication} class
     * when there are applications.
     */
    @Test
    public void testGetNextUIV2() {
        // Arrange
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

        Pet pet = new Pet(ID3, "1", "1", "1", "m");

        Application application = new Application();
        application.setPet(pet);
        application.setState(Application.State.APPROVED);

        List<Application> applications = new ArrayList<>();
        applications.add(application);

        CheckApplication checkApplication = new CheckApplication(referrer);

        // Act
        UI nextUI = checkApplication.getNextUI(session);

        // Assert
        assertEquals("Check submitted adoption applications",
                checkApplication.getName());
    }
}
