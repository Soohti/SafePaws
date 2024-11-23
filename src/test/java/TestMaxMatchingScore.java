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
        HashMap<Integer, Vector<MatchingPair>> input = new HashMap<>();
        input.put(10, new Vector<>(Arrays.asList(new MatchingPair(11, 100.0),
                new MatchingPair(22, 2.0), new MatchingPair(33, 3.0))));
        input.put(11, new Vector<>(Arrays.asList(new MatchingPair(22, 2.0),
                new MatchingPair(33, 1.0), new MatchingPair(44, 3.0))));
        input.put(12, new Vector<>(Arrays.asList(new MatchingPair(33, 3.0),
                new MatchingPair(44, 3.0), new MatchingPair(55, 2.0))));
        String output = new MaxMatchingScore().work(input);
        String expectedAns =
                "We have 3 pairs with a total matching degree of 106.0\n";
        expectedAns += "10 <-> 11\n";
        expectedAns += "11 <-> 44\n";
        expectedAns += "12 <-> 33\n";
        assertEquals(expectedAns, output);
    }
}
