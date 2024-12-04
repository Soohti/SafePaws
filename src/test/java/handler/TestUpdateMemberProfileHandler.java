package handler;

import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.handler.UpdateMemberProfileHandler;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for UpdateMemberProfileHandler.
 */
public class TestUpdateMemberProfileHandler {
    /**
     * ByteArrayOutputStream to capture the output stream.
     */
    private final ByteArrayOutputStream outContent
            = new ByteArrayOutputStream();

    /**
     * Original PrintStream to restore after tests.
     */
    private final PrintStream originalOut = System.out;

    /**
     * Constant for the member profile ID used in tests.
     */
    private static final int ID = 20;

    /**
     * Path to the server properties file.
     */
    private static final String SERVER_PROPERTIES_PATH
            = "conf/server/server.properties";

    /**
     * Sets up the test environment before each test.
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
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores the original output stream after each test.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests the updateMemberProfile method of UpdateMemberProfileHandler.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateMemberProfile() throws Exception {
        // Arrange
        MemberProfile memberProfile = new MemberProfile(ID, "Dog",
                "Beagle", "f");
        memberProfile.setDailyActivityLevel(1);
        memberProfile.setExtroversionLevel(1);
        memberProfile.setHouseSize(1);
        memberProfile.setFinancialBudget(1);
        memberProfile.setPreviousPetExperience(1);
        memberProfile.setNumberOfFamilyMembers(1);
        memberProfile.setWorkHours(1);

        UpdateMemberProfileHandler handler = new UpdateMemberProfileHandler();

        // Act
        handler.updateMemberProfile(memberProfile);

        // Assert
        MemberProfile updatedProfile =
                DbManager.readWithID(MemberProfile.class,
                        TableSchema.Name.MEMBER_PROFILE, ID);
        assert updatedProfile != null;
        assertEquals(1, updatedProfile.getDailyActivityLevel());
        assertEquals(1, updatedProfile.getExtroversionLevel());
        assertEquals(1, updatedProfile.getHouseSize());
        assertEquals(1, updatedProfile.getFinancialBudget());
        assertEquals(1, updatedProfile.getPreviousPetExperience());
        assertEquals(1, updatedProfile.getNumberOfFamilyMembers());
        assertEquals(1, updatedProfile.getWorkHours());
    }
}
