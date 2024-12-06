package algorithm;

import org.cs3343.safepaws.algorithm.SortPetAlgo;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for SortPetAlgo.
 */
public class TestSortPet {
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
     * Constant for the pet ID used in tests.
     */
    private static final int ID = 6;

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
    }

    /**
     * Tests the private constructor of SortPetAlgo.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<SortPetAlgo> algoConstructor =
                SortPetAlgo.class.getDeclaredConstructor();
        algoConstructor.setAccessible(true);
        int cntFail = 0;
        try {
            algoConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }
        assertEquals(1, cntFail);
    }

    /**
     * Tests the sortPetsByMatch method of SortPetAlgo.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    public void testSortPetAlgo() throws SQLException {
        int pid = 1;
        String preferredSpecies = "Dog";
        String preferredBreed = "Beagle";
        String mGen = "f";
        MemberProfile memberProfile = new MemberProfile(
                pid, preferredSpecies, preferredBreed, mGen);
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
        Member account = new Member(mid, newUsername,
                newPassword, newRole, memberProfile);
        account.setProfile(memberProfile);
        List<Pet> sortedPets = SortPetAlgo.sortPetsByMatch(account);
        assertEquals(ID, sortedPets.get(0).getId());
        assertEquals("Pet", sortedPets.get(0).getName());
        assertEquals("Dog", sortedPets.get(0).getSpecies());
        assertEquals("Beagle", sortedPets.get(0).getBreed());
        assertEquals("f", sortedPets.get(0).getGender());
        assertEquals(1, sortedPets.get(0).getAge());
        assertEquals(1, sortedPets.get(0).getWeight());
        assertEquals(1, sortedPets.get(0).getActivityLevel());
        assertEquals(1, sortedPets.get(0).getHealthStatus());
    }
}
