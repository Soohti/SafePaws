package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cs3343.safepaws.entity.LocationPoint;
import org.junit.jupiter.api.Test;

public class TestLocationPoint {
	/**
     * Tests the default constructor of the {@code LocationPoint} class.
     * Ensures that all fields are initialized to their default values.
     */
    @Test
    public void testDefaultConstructor() {
        LocationPoint locationPoint=new LocationPoint();
        assertEquals(-1,locationPoint.getXValue());
        assertEquals(-1,locationPoint.getYValue());
    }

    /**
     * Tests the parameterized constructor of the {@code LocationPoint} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor1() {
    	LocationPoint locationPoint=new LocationPoint(0,10);
        assertEquals(0,locationPoint.getXValue());
        assertEquals(10,locationPoint.getYValue());
    }
    
    /**
     * Tests the distanceTo method of the {@code LocationPoint} class.
     */
    @Test
    public void testDistanceTo() {
    	LocationPoint locationPoint1=new LocationPoint(0,10);
    	LocationPoint locationPoint2=new LocationPoint(0,0);
    	assertEquals(10.0,locationPoint1.distanceTo(locationPoint2));
    }
    /**
     * Tests the toString method of the {@code LocationPoint} class.
     */
    @Test
    public void testToString() {
    	LocationPoint locationPoint=new LocationPoint(0,0);
    	assertEquals("(0.0, 0.0)",locationPoint.toString());
    }
    
}
