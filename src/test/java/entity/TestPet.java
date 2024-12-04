package entity;

import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.entity.Pet.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link Pet} class.
 */
public class TestPet {

    /**
     * Tests the default constructor of the {@code Pet} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        Pet pet = new Pet();
        assertEquals(-1, pet.getId());
        assertEquals("", pet.getName());
        assertEquals("", pet.getSpecies());
        assertEquals("", pet.getBreed());
        assertEquals("", pet.getGender());
        assertEquals(0, pet.getAge());
        assertEquals(0, pet.getWeight());
        assertEquals(0, pet.getActivityLevel());
        assertEquals(0, pet.getHealthStatus());
        assertEquals(State.Free, pet.getState());
    }

    /**
     * Tests the parameterized constructor of the {@code Pet} class
     * with identity attributes.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructorWithIdentityAttributes() {
        Pet pet = new Pet(1, "Buddy", "Dog", "Labrador", "m");
        assertEquals(1, pet.getId());
        assertEquals("Buddy", pet.getName());
        assertEquals("Dog", pet.getSpecies());
        assertEquals("Labrador", pet.getBreed());
        assertEquals("m", pet.getGender());
        assertEquals(0, pet.getAge());
        assertEquals(0, pet.getWeight());
        assertEquals(0, pet.getActivityLevel());
        assertEquals(0, pet.getHealthStatus());
        assertEquals(State.Free, pet.getState());
    }

    /**
     * Tests the parameterized constructor of the {@code Pet} class
     * with physical attributes.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructorWithPhysicalAttributes() {
        Pet pet = new Pet(1, 1, 1, 1, 1, State.Adopted);
        assertEquals(1, pet.getId());
        assertEquals("", pet.getName());
        assertEquals("", pet.getSpecies());
        assertEquals("", pet.getBreed());
        assertEquals("", pet.getGender());
        assertEquals(1, pet.getAge());
        assertEquals(1, pet.getWeight());
        assertEquals(1, pet.getActivityLevel());
        assertEquals(1, pet.getHealthStatus());
        assertEquals(State.Adopted, pet.getState());
    }

    /**
     * Tests the getter methods of the {@code Pet} class.
     * Ensures that all fields are returned correctly.
     */
    @Test
    public void testGetters() {
        Pet pet = new Pet(1, "Max", "Cat", "Siamese", "f");
        assertEquals(1, pet.getId());
        assertEquals("Max", pet.getName());
        assertEquals("Cat", pet.getSpecies());
        assertEquals("Siamese", pet.getBreed());
        assertEquals("f", pet.getGender());
        assertEquals(0, pet.getAge());
        assertEquals(0, pet.getWeight());
        assertEquals(0, pet.getActivityLevel());
        assertEquals(0, pet.getHealthStatus());
        assertEquals(State.Free, pet.getState());
    }
}
