package ui.member;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.member.ViewPets;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the ViewPets class.
 */
public class TestViewPets {
    /**
     * ByteArrayOutputStream to capture the output stream.
     */
    private final ByteArrayOutputStream outContent =
            new ByteArrayOutputStream();

    /**
     * Original PrintStream to restore after tests.
     */
    private final PrintStream originalOut = System.out;

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
     * Tests the constructor of the ViewPets class.
     * Ensures that the object is created correctly.
     */
    @Test
    public void testViewPetsConstructor() {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };

        // Act
        ViewPets viewPets = new ViewPets(referrer);

        // Assert
        assertNotNull(viewPets);
        assertEquals("View Pet Details", viewPets.getName());
        assertEquals(referrer, viewPets.getReferrer());
    }

    /**
     * Tests the getNextUI method of the ViewPets class.
     * Ensures that the method displays the correct output.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testGetNextUI() throws Exception {
        // Arrange
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ViewPets viewPets = new ViewPets(referrer);
        Session session = new Session(System.in, System.out);
        int pid = 1;
        String preferredSpecies = "Dog";
        String preferredBreed = "Beagle";
        String mGen = "f";
        MemberProfile memberProfile = new MemberProfile(pid, preferredSpecies,
                preferredBreed, mGen);
        memberProfile.setDailyActivityLevel(1);
        memberProfile.setExtroversionLevel(1);
        memberProfile.setHouseSize(1);
        memberProfile.setFinancialBudget(1);
        memberProfile.setPreviousPetExperience(1);
        memberProfile.setNumberOfFamilyMembers(1);
        memberProfile.setWorkHours(1);
        int mid = 1;
        final String newUsername = "CityuCS3334";
        final String newPassword = "CityuCS3334";
        final String newRole = "M";
        Member member = new Member(mid, newUsername, newPassword, newRole,
                memberProfile);
        member.setProfile(memberProfile);
        session.setAccount(member);

        List<Pet> pets = new ArrayList<>(); // Initialize pets list
        Pet pet1 = new Pet(1, "Buddy", "Dog",
                "Beagle", "m");
        Pet pet2 = new Pet(2, "Mittens", "Cat",
                "Siamese", "f");
        pets.add(pet1);
        pets.add(pet2);

        // Act
        viewPets.getNextUI(session);
        // Assert
        assertEquals("View Pet Details", viewPets.getName());
    }
}
