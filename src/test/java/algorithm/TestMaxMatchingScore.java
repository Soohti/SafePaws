package algorithm;

import org.cs3343.safepaws.algorithm.MaxMatchingScore;
import org.cs3343.safepaws.entity.MatchingPair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for MaxMatchingScore.
 */
public class TestMaxMatchingScore {

    /**
     * Tests the work method of MaxMatchingScore.
     */
    @Test
    public void testWork() {
        // Create a HashMap to store input data
        HashMap<String, Vector<MatchingPair>> input = new HashMap<>();

        // Add matching pairs for user U1
        input.put("U1", new Vector<>(Arrays.asList(
                new MatchingPair("P1", 100.0),
                new MatchingPair("P2", 2.0),
                new MatchingPair("P3", 3.0))));

        // Add matching pairs for user U2
        input.put("U2", new Vector<>(Arrays.asList(
                new MatchingPair("P2", 2.0),
                new MatchingPair("P3", 1.0),
                new MatchingPair("P4", 3.0))));

        // Add matching pairs for user U3
        input.put("U3", new Vector<>(Arrays.asList(
                new MatchingPair("P3", 3.0),
                new MatchingPair("P3", 3.0),
                new MatchingPair("P5", 2.0))));

        // Call the work method of MaxMatchingScore
        String output = new MaxMatchingScore().work(input);

        // Define the expected output
        String expectedAns = "We have 3 pairs with"
                + " a total matching score of 106.0\n";
        expectedAns += "Username <-> Pet ID\n";
        expectedAns += "U1 <-> P1\n";
        expectedAns += "U2 <-> P4\n";
        expectedAns += "U3 <-> P3\n";

        // Assert that the output matches the expected output
        assertEquals(expectedAns, output);
    }
}
