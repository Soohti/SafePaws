package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.ShelterLocation;
import org.junit.jupiter.api.Test;

public class TestShelterLocation {
	/**
     * Tests the default constructor of the {@code ShelterLocation} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        ShelterLocation shelterLocation=new ShelterLocation();
        assertEquals(-1,shelterLocation.getId());
        assertEquals(-1,shelterLocation.getXValue());
        assertEquals(-1,shelterLocation.getYValue());
    }
    
    /**
     * Tests the parameterized constructor of the {@code ShelterLocation} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor() {
    	ShelterLocation shelterLocation=new ShelterLocation(1,1,1);
        assertEquals(1,shelterLocation.getId());
        assertEquals(1,shelterLocation.getXValue());
        assertEquals(1,shelterLocation.getYValue());
    }
    /**
     * Tests the clone method of the {@code ShelterLocation} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testClone() {
    	ShelterLocation shelterLocation=new ShelterLocation(1,1,1);
    	ShelterLocation cloneShelterLocation=shelterLocation.clone();
        assertEquals(cloneShelterLocation.getId(),shelterLocation.getId());
        assertEquals(cloneShelterLocation.getXValue(),shelterLocation.getXValue());
        assertEquals(cloneShelterLocation.getYValue(),shelterLocation.getYValue());
    }
    
    /**
     * Tests the toString method of the {@code ShelterLocation} class.
     * Ensures that a deep copy of the member object is created.
     */
    @Test
    public void testToString() {
    	ShelterLocation shelterLocation=new ShelterLocation(1,1,1);
    	assertEquals("(1.0, 1.0)",shelterLocation.toString());
    }
}
