package org.cs3343.safepaws.algorithm;

import org.cs3343.safepaws.entity.LocationPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Elbow Method, which is a technique used to
 * determine the optimal number of clusters (K) in K-Means clustering.
 * It evaluates the Sum of Squared Errors (SSE) for a range of
 * K values and identifies the "elbow"
 * point where the SSE starts to decrease at a slower rate.
 */
public final class ElbowUtil {
    private ElbowUtil() throws Exception {
        throw new Exception("This is a utility class"
                + " and cannot be instantiated");
    }
    /**
     * Finds the optimal number of clusters (K) using the Elbow Method.
     *
     * @param locationPoints a list of {@link LocationPoint} objects
     * representing he data points
     * @param maxK the maximum number of clusters to evaluate
     * @return the optimal number of clusters (K)
     */
    public static int findOptimalK(final List<LocationPoint> locationPoints,
                                   final int maxK) {
        List<Double> sseList = new ArrayList<>();

        for (int k = 1; k <= maxK; k++) {
            KMeans kmeans = new KMeans(k);
            kmeans.fit(locationPoints);
            sseList.add(kmeans.calculateSSE());
        }

        System.out.println("SSE values for each K:");
        for (int i = 0; i < sseList.size(); i++) {
            System.out.println("K = " + (i + 1) + ": " + sseList.get(i));
        }

        int optimalK = 1;
        double minDrop = Double.MAX_VALUE;
        for (int i = 1; i < sseList.size(); i++) {
            double drop = sseList.get(i - 1) - sseList.get(i);
            if (drop < minDrop) {
                minDrop = drop;
                optimalK = i;
            }
        }

        // Return the optimal K value, adjusting for 1-based index
        return optimalK + 1;
    }
}
