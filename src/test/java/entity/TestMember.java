package entity;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@link Member} class.
 */
public class TestMember {

    /**
     * Tests the default constructor of the {@code Member} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        Member member = new Member();
        assertEquals(0, member.getId());
        assertEquals("", member.getUsername());
        assertEquals("", member.getPassword());
        assertEquals("", member.getRole());
    }

    /**
     * Tests the parameterized constructor of the {@code Member} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor() {
        MemberProfile profile = new MemberProfile(1, "Dog",
                "Labrador", "m");
        Member member = new Member(1, "username",
                "password", "M", profile);

        assertEquals(1, member.getId());
        assertEquals("username", member.getUsername());
        assertEquals("password", member.getPassword());
        assertEquals("M", member.getRole());
        assertNotNull(member.getProfile());
        assertEquals(profile.getId(), member.getProfile().getId());
        assertEquals(profile.getPreferredSpecies(),
                member.getProfile().getPreferredSpecies());
        assertEquals(profile.getPreferredBreed(),
                member.getProfile().getPreferredBreed());
        assertEquals(profile.getGender(), member.getProfile().getGender());
    }

    /**
     * Tests the setter and getter methods of the {@code Member} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        Member member = new Member();
        member.setId(2);
        member.setUsername("newUsername");
        member.setPassword("newPassword");
        member.setRole("M");

        MemberProfile profile = new MemberProfile(2, "Cat",
                "Siamese", "f");
        member.setProfile(profile);

        assertEquals(2, member.getId());
        assertEquals("newUsername", member.getUsername());
        assertEquals("newPassword", member.getPassword());
        assertEquals("M", member.getRole());
        assertNotNull(member.getProfile());
        assertEquals(profile.getId(), member.getProfile().getId());
        assertEquals(profile.getPreferredSpecies(),
                member.getProfile().getPreferredSpecies());
        assertEquals(profile.getPreferredBreed(),
                member.getProfile().getPreferredBreed());
        assertEquals(profile.getGender(), member.getProfile().getGender());
    }

    /**
     * Tests the clone method of the {@code Member} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testClone() {
        MemberProfile profile = new MemberProfile(1,
                "Dog", "Labrador", "m");
        Member member = new Member(1,
                "username", "password", "M", profile);
        Member clonedMember = member.clone();

        assertEquals(member.getId(), clonedMember.getId());
        assertEquals(member.getUsername(), clonedMember.getUsername());
        assertEquals(member.getPassword(), clonedMember.getPassword());
        assertEquals(member.getRole(), clonedMember.getRole());
        assertNotNull(clonedMember.getProfile());
        assertEquals(member.getProfile().getId(),
                clonedMember.getProfile().getId());
        assertEquals(member.getProfile().getPreferredSpecies(),
                clonedMember.getProfile().getPreferredSpecies());
        assertEquals(member.getProfile().getPreferredBreed(),
                clonedMember.getProfile().getPreferredBreed());
        assertEquals(member.getProfile().getGender(),
                clonedMember.getProfile().getGender());
    }
}
