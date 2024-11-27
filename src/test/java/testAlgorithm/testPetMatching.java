package testAlgorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.junit.jupiter.api.Test;

public class testPetMatching {
	@Test
	public void testCalculateMatch() {
		Member account = new Member("CityuCS3334","CityuCS3334","M");
		Pet pet = new Pet();
		MemberProfile memberProfile = new MemberProfile();
		account.setProfile(memberProfile);
		double Score = PetMatchingAlgo.calculateMatch(account, pet);
		assertEquals(1.00,Score);
	}
	
}
