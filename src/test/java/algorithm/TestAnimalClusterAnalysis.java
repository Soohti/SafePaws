package algorithm;

import org.cs3343.safepaws.algorithm.AnimalClusterAnalysis;
import org.cs3343.safepaws.algorithm.FindingOptimalShelterNumber;
import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.RecommendShelter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for AnimalClusterAnalysis.
 */
public class TestAnimalClusterAnalysis {

    /**
     * Tests the private constructor of FindingOptimalShelterNumber.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<FindingOptimalShelterNumber> algoConstructor =
                FindingOptimalShelterNumber.class.getDeclaredConstructor();
        algoConstructor.setAccessible(true);
        int cntFail = 0;
        try {
            algoConstructor.newInstance();
        } catch (Throwable e) {
            cntFail++;
        }
        assertEquals(1, cntFail);
    }

    /**
     * Tests the creation of a LocationPoint.
     */
    @Test
    public void testAnimalLocation() {
        LocationPoint locationPoint = new LocationPoint(10.0, 20.0);
        assertEquals(10.0, locationPoint.getXValue(), 1e-6);
        assertEquals(20.0, locationPoint.getYValue(), 1e-6);
    }

    /**
     * Tests the distance calculation between two points.
     */
    @Test
    public void testDistanceCalculation() {
        LocationPoint loc1 = new LocationPoint(0.0, 0.0);
        LocationPoint loc2 = new LocationPoint(3.0, 4.0);
        assertEquals(5.0, loc1.distanceTo(loc2), 1e-6);
    }

    /**
     * Tests the initialization of K-means clustering.
     */
    @Test
    public void testKMeansInitialization() {
        List<LocationPoint> locationPoints = new ArrayList<>();
        locationPoints.add(new LocationPoint(1.0, 2.0));
        locationPoints.add(new LocationPoint(3.0, 4.0));
        locationPoints.add(new LocationPoint(5.0, 6.0));

        AnimalClusterAnalysis kmeans = new AnimalClusterAnalysis(2);
        kmeans.fit(locationPoints);
        assertNotNull(kmeans.getClusters());
        assertEquals(2, kmeans.getClusters().size());
    }

    /**
     * Tests the K-means clustering algorithm.
     */
    @Test
    public void testKMeansClustering() {
        List<LocationPoint> locationPoints = new ArrayList<>();
        locationPoints.add(new LocationPoint(1.0, 1.0));
        locationPoints.add(new LocationPoint(2.0, 2.0));
        locationPoints.add(new LocationPoint(8.0, 8.0));
        locationPoints.add(new LocationPoint(9.0, 9.0));

        AnimalClusterAnalysis kmeans = new AnimalClusterAnalysis(2);
        kmeans.fit(locationPoints);

        List<List<LocationPoint>> clusters = kmeans.getClusters();
        assertEquals(2, clusters.size());
        assertTrue(clusters.get(0).size() > 0);
        assertTrue(clusters.get(1).size() > 0);
    }

    /**
     * Tests the calculation of SSE (Sum of Squared Errors).
     */
    @Test
    public void testCalculateSSE() {
        List<LocationPoint> locationPoints = new ArrayList<>();
        locationPoints.add(new LocationPoint(1.0, 1.0));
        locationPoints.add(new LocationPoint(2.0, 2.0));
        locationPoints.add(new LocationPoint(3.0, 3.0));

        AnimalClusterAnalysis kmeans = new AnimalClusterAnalysis(2);
        kmeans.fit(locationPoints);

        double sse = kmeans.calculateSSE();
        assertTrue(sse >= 0);
    }

    /**
     * Tests the elbow method for finding the optimal number of clusters.
     */
    @Test
    public void testElbowMethod() {
        List<LocationPoint> locationPoints = new ArrayList<>();
        locationPoints.add(new LocationPoint(1.0, 1.0));
        locationPoints.add(new LocationPoint(2.0, 2.0));
        locationPoints.add(new LocationPoint(3.0, 3.0));
        locationPoints.add(new LocationPoint(10.0, 10.0));
        locationPoints.add(new LocationPoint(11.0, 11.0));
        locationPoints.add(new LocationPoint(12.0, 12.0));

        int maxK = 5;
        int optimalK = FindingOptimalShelterNumber
                .findOptimalK(locationPoints, maxK);
        assertTrue(optimalK > 0 && optimalK <= maxK);
    }

    /**
     * Tests the display of recommended shelters.
     */
    @Test
    public void testShelterDisplay() {
        List<LocationPoint> shelterLocationPoints = new ArrayList<>();
        shelterLocationPoints.add(new LocationPoint(1.0, 2.0));
        shelterLocationPoints.add(new LocationPoint(3.0, 4.0));

        List<RecommendShelter> recommendShelters = new ArrayList<>();
        for (LocationPoint locationPoint : shelterLocationPoints) {
            recommendShelters.add(new RecommendShelter(locationPoint));
        }
        System.out.println("RecommendShelter Locations:");
        for (RecommendShelter recommendShelter : recommendShelters) {
            System.out.print(recommendShelter.toString());
        }
    }
}
