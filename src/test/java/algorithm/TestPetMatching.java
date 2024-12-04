package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.algorithm.PetMatchingAlgo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

/**
 * Test class for PetMatchingAlgo.
 */
public class TestPetMatching {
    /**
     * Constant for the score.
     */
    private static final double EXPECTED = 1049.9;

    /**
     * Tests the calculateMatch method of PetMatchingAlgo.
     */
    @Test
    public void testCalculateMatch() {
        int pid = 1;
        String preferredSpecies = "Dog";
        String preferredBreed = "Beagle";
        String mGen = "f";
        MemberProfile memberProfile = new MemberProfile(pid,
                preferredSpecies, preferredBreed, mGen);
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
        int petId = 1;
        String name = "Pet";
        String species = "Dog";
        String breed = "Beagle";
        String gender = "f";
        Pet pet = new Pet(petId, name, species, breed, gender);
        double score = PetMatchingAlgo.calculateMatch(account, pet);
        assertEquals(EXPECTED, score);
    }

    /**
     * Tests the private constructor of PetMatchingAlgo.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<PetMatchingAlgo> algoConstructor =
                PetMatchingAlgo.class.getDeclaredConstructor();
        algoConstructor.setAccessible(true);
        int cntFail = 0;
        try {
            algoConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }
        assertEquals(1, cntFail);
    }
}
