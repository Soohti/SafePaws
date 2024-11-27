package testAlgorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.SortPetAlgo;
import org.junit.jupiter.api.Test;

public class testSortPetAlgo {
	@Test
	public void testCalculateMatch() {
		Member account = new Member("CityuCS3334","CityuCS3334","CityuCS3334");
		MemberProfile memberProfile = new MemberProfile();
		account.setProfile(memberProfile);
		List<Pet> pets = SortPetAlgo.sortPetsByMatch(account);
		// Pet override @equals and @hashcode
	}
}
