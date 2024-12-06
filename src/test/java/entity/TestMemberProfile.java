package entity;

import org.cs3343.safepaws.entity.MemberProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link MemberProfile} class.
 */
public class TestMemberProfile {

    /**
     * Tests the default constructor of the {@code MemberProfile} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        MemberProfile profile = new MemberProfile();
        assertEquals(-1, profile.getId());
        assertEquals(0, profile.getExtroversionLevel());
        assertEquals(0, profile.getDailyActivityLevel());
        assertEquals(0, profile.getHouseSize());
        assertEquals(0, profile.getWorkHours());
        assertEquals(0, profile.getNumberOfFamilyMembers());
        assertEquals(0, profile.getPreviousPetExperience());
        assertEquals(0, profile.getFinancialBudget());
        assertEquals("", profile.getPreferredSpecies());
        assertEquals("", profile.getPreferredBreed());
        assertEquals("", profile.getGender());
    }

    /**
     * Tests the parameterized constructor of the {@code MemberProfile} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor() {
        MemberProfile profile = new MemberProfile(
                1,
                "Dog",
                "Labrador",
                "m");
        assertEquals(1, profile.getId());
        assertEquals(0, profile.getExtroversionLevel());
        assertEquals(0, profile.getDailyActivityLevel());
        assertEquals(0, profile.getHouseSize());
        assertEquals(0, profile.getWorkHours());
        assertEquals(0, profile.getNumberOfFamilyMembers());
        assertEquals(0, profile.getPreviousPetExperience());
        assertEquals(0, profile.getFinancialBudget());
        assertEquals("Dog", profile.getPreferredSpecies());
        assertEquals("Labrador", profile.getPreferredBreed());
        assertEquals("m", profile.getGender());
    }

    /**
     * Tests the setter and getter methods of the {@code MemberProfile} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        MemberProfile profile = new MemberProfile();
        profile.setId(1);
        profile.setExtroversionLevel(1);
        profile.setDailyActivityLevel(1);
        profile.setHouseSize(1);
        profile.setWorkHours(1);
        profile.setNumberOfFamilyMembers(1);
        profile.setPreviousPetExperience(1);
        profile.setFinancialBudget(1);
        profile.setPreferredSpecies("Cat");
        profile.setPreferredBreed("Siamese");
        profile.setGender("f");

        assertEquals(1, profile.getId());
        assertEquals(1, profile.getExtroversionLevel());
        assertEquals(1, profile.getDailyActivityLevel());
        assertEquals(1, profile.getHouseSize());
        assertEquals(1, profile.getWorkHours());
        assertEquals(1, profile.getNumberOfFamilyMembers());
        assertEquals(1, profile.getPreviousPetExperience());
        assertEquals(1, profile.getFinancialBudget());
        assertEquals("Cat", profile.getPreferredSpecies());
        assertEquals("Siamese", profile.getPreferredBreed());
        assertEquals("f", profile.getGender());
    }

    /**
     * Tests the clone method of the {@code MemberProfile} class.
     * Ensures that a deep copy of the member profile object is created.
     */
    @Test
    public void testClone() {
        MemberProfile profile = new MemberProfile(
                1,
                "Dog",
                "Labrador",
                "m");
        MemberProfile clonedProfile = profile.clone();

        assertEquals(profile.getId(), clonedProfile.getId());
        assertEquals(profile.getExtroversionLevel(),
                clonedProfile.getExtroversionLevel());
        assertEquals(profile.getDailyActivityLevel(),
                clonedProfile.getDailyActivityLevel());
        assertEquals(profile.getHouseSize(), clonedProfile.getHouseSize());
        assertEquals(profile.getWorkHours(), clonedProfile.getWorkHours());
        assertEquals(profile.getNumberOfFamilyMembers(),
                clonedProfile.getNumberOfFamilyMembers());
        assertEquals(profile.getPreviousPetExperience(),
                clonedProfile.getPreviousPetExperience());
        assertEquals(profile.getFinancialBudget(),
                clonedProfile.getFinancialBudget());
        assertEquals(profile.getPreferredSpecies(),
                clonedProfile.getPreferredSpecies());
        assertEquals(profile.getPreferredBreed(),
                clonedProfile.getPreferredBreed());
        assertEquals(profile.getGender(), clonedProfile.getGender());
    }
}
