import org.cs3343.safepaws.algorithm.MinPetPath;
import org.cs3343.safepaws.entity.LocationPoint;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMinPetPath {

    @Test
    public void testWork() {
        LocationPoint startPoint = new LocationPoint(0, 0);
        LocationPoint point1 = new LocationPoint(1, 1);
        LocationPoint point2 = new LocationPoint(2, 2);
        LocationPoint point3 = new LocationPoint(2, 0);

        Vector<LocationPoint> path = new Vector<>();
        path.add(point1);
        path.add(point2);
        path.add(point3);
        String output = new MinPetPath().work(startPoint, path);
        String expectedPath =
                "Best path is: [(0.0, 0.0), (1.0, 1.0), (2.0, 2.0), (2.0, 0.0)]";
        double expectedDistance =
                startPoint.distanceTo(point1) + point1.distanceTo(point2)
                        + point2.distanceTo(point3) + point3.distanceTo(
                        startPoint);
        assertEquals(
                expectedPath + "\n" + "Minimum distance is: " + expectedDistance
                        + "\n", output);
    }
}
