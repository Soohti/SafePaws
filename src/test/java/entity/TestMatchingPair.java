package entity;

import org.cs3343.safepaws.entity.MatchingPair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMatchingPair {
    /**
     * Tests the parameterized constructor of the {@code MatchingPair} class.
     * Ensures that all fields are initialized correctly.
     */
    @Test
    public void testParameterizedConstructor1() {
        MatchingPair matchingPair = new MatchingPair("a", 0);
        assertEquals("a", matchingPair.getFirst());
        assertEquals(0, matchingPair.getSecond());
    }
}
