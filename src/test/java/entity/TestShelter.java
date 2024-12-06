package entity;

import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestShelter {
    /**
     * Tests the parameterized constructor of the {@code Shelter} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor2() {
        ShelterLocation shelterLocation = new ShelterLocation(1, 1, 1);
        Shelter shelter =
                new Shelter(1, "username", "password", "S", shelterLocation);
        assertEquals(1, shelter.getId());
        assertEquals("username", shelter.getUsername());
        assertEquals("password", shelter.getPassword());
        assertEquals("S", shelter.getRole());
    }

    /**
     * Tests the setter and getter methods of the {@code Shelter} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        ShelterLocation shelterLocation1 = new ShelterLocation(1, 1, 1);
        Shelter shelter =
                new Shelter(1, "username", "password", "S", shelterLocation1);
        ShelterLocation shelterLocation2 = new ShelterLocation(2, 2, 2);
        shelter.setLocationPoint(shelterLocation2);
        assertEquals(shelterLocation2, shelter.getLocationPoint());
    }

    /**
     * Tests the clone method of the {@code Shelter} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testClone() {
        ShelterLocation shelterLocation = new ShelterLocation(1, 1, 1);
        Shelter shelter =
                new Shelter(1, "username", "password", "S", shelterLocation);
        Shelter colneShelter = shelter.clone();
        assertEquals(shelter.getId(), colneShelter.getId());
        assertEquals(shelter.getUsername(), colneShelter.getUsername());
        assertEquals(shelter.getPassword(), colneShelter.getPassword());
        assertEquals(shelter.getRole(), colneShelter.getRole());
    }
}
