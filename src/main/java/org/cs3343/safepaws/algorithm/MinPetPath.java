package org.cs3343.safepaws.algorithm;

import org.cs3343.safepaws.entity.LocationPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public final class MinPetPath implements Algorithm {

    /**
     * This represents the infinity distance.
     */
    private static final double INF = Double.MAX_VALUE / 2;

    /**
     * Solve the shortest path.
     *
     * @param dist The distance matrix
     * @param path The path
     * @return The shortest path length
     */
    public double tsp(final double[][] dist, final List<Integer> path) {
        int n = dist.length;
        // dp[i][j] represents the shortest path length
        // through the set j starting from city i
        double[][] dp = new double[n][1 << n];
        int[][] parent = new int[n][1 << n];  // Use to track the path

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][1] = 0;  // Start from city 0

        // Use dynamic programming to fill the dp array
        for (int mask = 1; mask < (1 << n);
             mask += 2) {  // Only consider the set containing city 0
            for (int i = 1; i < n; i++) {
                if ((mask & (1 << i)) != 0) {  // City i is in the set mask
                    for (int j = 0; j < n; j++) {
                        if ((mask & (1 << j)) != 0 && j != i) {
                            // City j is in the set and is not the city i
                            double newDist =
                                    dp[j][mask ^ (1 << i)] + dist[j][i];
                            if (newDist < dp[i][mask]) {
                                dp[i][mask] = newDist;
                                parent[i][mask] = j;  // Record the path
                            }
                        }
                    }
                }
            }
        }

        // Find the shortest path length
        double minDistance = INF;
        int lastCity = -1;
        for (int i = 1; i < n; i++) {
            double distToStart = dp[i][(1 << n) - 1]
                    + dist[i][0];  // Go back to the starting point
            if (distToStart < minDistance) {
                minDistance = distToStart;
                lastCity = i;
            }
        }

        // Track the path
        int mask = (1 << n) - 1;
        while (lastCity != 0) {
            path.addFirst(lastCity);
            int prevCity = parent[lastCity][mask];
            mask ^= (1 << lastCity);
            lastCity = prevCity;
        }
        path.addFirst(0);

        return minDistance;
    }

    /**
     * Work on the shortest path.
     *
     * @param start The starting point
     * @param path  The path
     * @return The result
     */
    public String work(final LocationPoint start,
                       final Vector<LocationPoint> path) {
        path.addFirst(start);
        int n = path.size();
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = path.get(i).distanceTo(path.get(j));
            }
        }

        List<Integer> res = new ArrayList<>();
        double minDistance = tsp(dist, res);

        String output = "Best path is: " + path + "\n";
        output += "Minimum distance is: " + minDistance + "\n";
        return output;
    }
}
