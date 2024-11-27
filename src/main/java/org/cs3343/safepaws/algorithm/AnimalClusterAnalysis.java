/**
 * AnimalClusterAnalysis Clustering Algorithm Implementation.
 * This class provides an implementation of the AnimalClusterAnalysis
 * clustering algorithm, which divides a set of locations
 * (represented as {@link Location} objects)
 * into k clusters. The algorithm iteratively updates the cluster centers until
 * they no longer change significantly.
 *
 * @version 1.0
 * @since 2023-xx-xx
 */
package org.cs3343.safepaws.algorithm;

import org.cs3343.safepaws.entity.LocationPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The AnimalClusterAnalysis class for performing AnimalClusterAnalysis
 * clustering.
 * <p>This class offers functionality to perform AnimalClusterAnalysis
 * clustering on a list of
 * locations. Users can specify the number of clusters k and invoke the
 * {@link #fit(List)} method to cluster the locations.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * List<LocationPoint> locations = ...; // Initialize the list of locations
 * int k = 3; // Specify the number of clusters
 * AnimalClusterAnalysis kMeans = new AnimalClusterAnalysis(k);
 * kMeans.fit(locations);
 * List<List<LocationPoint>> clusters = kMeans.getClusters();
 * </pre>
 */
public class AnimalClusterAnalysis implements Algorithm {

    /**
     * The number of clusters.
     */
    private int k;

    /**
     * The value of THRESHOLD distance for new centre.
     */
    private static final double THRESHOLD = 1e-6;

    /**
     * The list of cluster centers.
     */
    private List<LocationPoint> centers;

    /**
     * The list of clusters, where each cluster contains a list of locations.
     */
    private List<List<LocationPoint>> clusters;

    /**
     * Constructs a new AnimalClusterAnalysis object with the specified
     * number of clusters.
     * @param kValue the number of clusters
     */
    public AnimalClusterAnalysis(final int kValue) {
        this.k = kValue;
        this.centers = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    /**
     * Fits the AnimalClusterAnalysis algorithm to the provided list
     * of locationPoints.
     * <p>This method initializes the cluster centers, assigns locationPoints
     * to the nearest center, and iteratively updates the centers until
     * they no longer change significantly.</p>
     *
     * @param locationPoints the list of locationPoints to cluster
     */
    public void fit(final List<LocationPoint> locationPoints) {
        initializeCenters(locationPoints);
        boolean changed = true;

        while (changed) {
            assignClusters(locationPoints);
            changed = updateCenters();
        }
    }

    /**
     * Returns the list of clusters generated by the AnimalClusterAnalysis
     * algorithm.
     * @return the list of clusters, where each cluster is a list of locations
     */
    public List<List<LocationPoint>> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    /**
     * Initializes the cluster centers by randomly selecting
     * locationPoints from the provided list.
     *
     * @param locationPoints the list of locationPoints to select centers from
     */
    private void initializeCenters(final List<LocationPoint> locationPoints) {
        centers.clear();
        for (int i = 0; i < k; i++) {
            centers.add(locationPoints.get(ThreadLocalRandom.current()
                    .nextInt(locationPoints.size())));
        }
    }


    /**
     * Assigns each location to the cluster with the nearest center.
     *
     * @param locationPoints the list of locationPoints to assign to clusters
     */
    private void assignClusters(final List<LocationPoint> locationPoints) {
        clusters.clear();
        for (int i = 0; i < k; i++) {
            clusters.add(new ArrayList<>());
        }

        for (LocationPoint locationPoint : locationPoints) {
            int closestCenterIndex = getClosestCenterIndex(locationPoint);
            clusters.get(closestCenterIndex).add(locationPoint);
        }
    }

    /**
     * Finds the index of the cluster center closest to the given locationPoint.
     *
     * @param locationPoint the locationPoint to find the closest center for
     * @return the index of the closest cluster center
     */
    private int getClosestCenterIndex(final LocationPoint locationPoint) {
        double minDistance = Double.MAX_VALUE;
        int closestIndex = -1;

        for (int i = 0; i < centers.size(); i++) {
            double distance = locationPoint.distanceTo(centers.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    /**
     * Updates the cluster centers based on the current cluster assignments.
     * This method calculates the average location of each cluster and updates
     * the corresponding center. It returns true if any center has moved
     * significantly, indicating that another iteration is needed.
     *
     * @return true if any center has moved significantly, false otherwise
     */
    private boolean updateCenters() {
        boolean changed = false;
        for (int i = 0; i < k; i++) {
            double avgX = 0;
            double avgY = 0;
            List<LocationPoint> cluster = clusters.get(i);

            if (!cluster.isEmpty()) {
                for (LocationPoint locationPoint : cluster) {
                    avgX += locationPoint.getX();
                    avgY += locationPoint.getY();
                }
                avgX /= cluster.size();
                avgY /= cluster.size();

                LocationPoint newCenter = new LocationPoint(avgX, avgY);
                if (newCenter.distanceTo(centers.get(i)) > THRESHOLD) {
                    centers.set(i, newCenter);
                    changed = true;
                }
            }
        }
        return changed;
    }

    /**
     * Calculates the Sum of Squared Errors (SSE) for the current clustering.
     * The SSE is a measure of the dispersion of the locations within the
     * clusters. A lower SSE indicates a better fit of the clusters to the data.
     *
     * @return the Sum of Squared Errors for the current clustering
     */
    public double calculateSSE() {
        double sse = 0.0;
        for (int i = 0; i < clusters.size(); i++) {
            for (LocationPoint locationPoint : clusters.get(i)) {
                sse += Math.pow(locationPoint.distanceTo(centers.get(i)), 2);
            }
        }
        return sse;
    }
}
