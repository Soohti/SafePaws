package entity;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.RecommendShelter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRecommandShelter {
    /**
     * Tests the parameterized constructor of the {@code RecommandShelter} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor1() {
        LocationPoint locationPoint = new LocationPoint(1, 1);
        RecommendShelter recommendShelter = new RecommendShelter(locationPoint);
        assertEquals("X: 1.0, Y: 1.0\n", recommendShelter.toString());
    }
}
