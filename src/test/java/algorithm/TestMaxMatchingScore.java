package algorithm;

import org.cs3343.safepaws.algorithm.MaxMatchingScore;
import org.cs3343.safepaws.entity.MatchingPair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMaxMatchingScore {

    @Test
    public void testWork() {
        HashMap<String, Vector<MatchingPair>> input = new HashMap<>();
        input.put("U1", new Vector<>(Arrays.asList(new MatchingPair("P1", 100.0),
                new MatchingPair("P2", 2.0), new MatchingPair("P3", 3.0))));
        input.put("U2", new Vector<>(Arrays.asList(new MatchingPair("P2", 2.0),
                new MatchingPair("P3", 1.0), new MatchingPair("P4", 3.0))));
        input.put("U3", new Vector<>(Arrays.asList(new MatchingPair("P3", 3.0),
                new MatchingPair("P3", 3.0), new MatchingPair("P5", 2.0))));
        String output = new MaxMatchingScore().work(input);
        String expectedAns =
                "We have 3 pairs with a total matching score of 106.0\n";
        expectedAns += "Username <-> Pet ID\n";
        expectedAns += "U1 <-> P1\n";
        expectedAns += "U2 <-> P4\n";
        expectedAns += "U3 <-> P3\n";
        assertEquals(expectedAns, output);
    }
}