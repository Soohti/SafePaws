package entity;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Application.State;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestApplication {
    /**
     * Tests the default constructor of the {@code Application} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        Application application = new Application();
        assertEquals(0, application.getId());
    }

    /**
     * Tests the parameterized constructor of the {@code Application} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor1() {
        MemberProfile profile = new MemberProfile(1, "Dog",
                "Labrador", "m");
        Member member = new Member(1, "username",
                "password", "M", profile);
        Pet pet = new Pet();
        Application application = new Application(member, pet, State.PENDING);
        assertEquals(State.PENDING, application.getState());
        assertEquals(member.getId(), application.getUser().getId());
        assertEquals(pet.getId(), application.getPet().getId());
    }

    /**
     * Tests the isValid method of the {@code Application} class.
     */
    @Test
    public void testIsValidState() {
        assertEquals(false, Application.isValidState(0));
    }

    /**
     * Tests the setter and getter methods of the {@code Application} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        Application application = new Application();
        application.setId(2);
        Pet pet = new Pet();
        application.setPet(pet);
        application.setState(State.PENDING);
        assertEquals(2, application.getId());
        assertEquals(State.PENDING, application.getState());
    }
}
