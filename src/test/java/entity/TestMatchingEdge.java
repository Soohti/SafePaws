package entity;

import org.cs3343.safepaws.entity.MatchingEdge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMatchingEdge {
    /**
     * Tests the parameterized constructor of the {@code MatchingEdge} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor() {
        MatchingEdge matchingEdge = new MatchingEdge(1, 1, 1, 1);
        assertEquals(1, matchingEdge.getV());
        assertEquals(1, matchingEdge.getCapacity());
        assertEquals(1, matchingEdge.getWeight());
        assertEquals(1, matchingEdge.getNext());
    }

    /**
     * Tests the setter and getter methods of the {@code MatchingEdge} class.
     * Ensures that all fields are set and retrieved correctly.
     */
    @Test
    public void testSettersAndGetters() {
        MatchingEdge matchingEdge = new MatchingEdge(1, 1, 1, 1);
        matchingEdge.setCapacity(0);
        assertEquals(1, matchingEdge.getV());
        assertEquals(0, matchingEdge.getCapacity());
        assertEquals(1, matchingEdge.getWeight());
        assertEquals(1, matchingEdge.getNext());
    }
}
