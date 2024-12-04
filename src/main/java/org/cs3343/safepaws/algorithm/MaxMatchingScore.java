package org.cs3343.safepaws.algorithm;

import org.cs3343.safepaws.entity.MatchingEdge;
import org.cs3343.safepaws.entity.MatchingPair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;


public final class MaxMatchingScore implements Algorithm {

    /**
     * The redundancy factor to account for additional nodes.
     */
    private static final int REDUNDANCY = 10;

    /**
     * Array of edges representing the graph for matching.
     */
    private MatchingEdge[] edges;

    /**
     * Array representing the match pair for each user.
     */
    private int[] p;

    /**
     * Array indicating whether a node has been visited during graph traversal.
     */
    private boolean[] vis;

    /**
     * Array storing the predecessor of each node in the path in SPFA.
     */
    private int[] pre;

    /**
     * Array storing the distances from the source node to each node.
     */
    private double[] d;

    /**
     * The current edge identifier used for managing the edges.
     */
    private int eid;

    /**
     * The number of users (nodes) in the matching process.
     */
    private int n;

    /**
     * The number of pets (nodes) in the matching process.
     */
    private int m;

    /**
     * The source node identifier in the flow network.
     */
    private int source;

    /**
     * The terminal node identifier in the flow network.
     */
    private int terminal;

    /**
     * The total result score accumulated from the matching process.
     */
    private double res = 0;

    /**
     * The total number of matching pairs found.
     */
    private int ans = 0;

    /**
     * Normalizer instance for user data.
     */
    private final Normalizer normForUser;

    /**
     * Normalizer instance for pet data.
     */
    private final Normalizer normForPet;

    /**
     * Constructs a new MaxMatchingScore.
     */
    public MaxMatchingScore() {
        normForUser = new Normalizer();
        normForPet = new Normalizer();
    }

    private void init(final int nodeCount, final int edgeCount) {
        edges = new MatchingEdge[edgeCount
                * 2]; // edgeCount * 2 because of bidirectional edges
        p = new int[nodeCount];
        vis = new boolean[nodeCount];
        pre = new int[nodeCount];
        d = new double[nodeCount];

        Arrays.fill(p, -1);
        eid = 0;
    }

    private void insert(final int u, final int v, final int capacity,
                        final double weight) {
        edges[eid] = new MatchingEdge(v, capacity, weight, p[u]);
        p[u] = eid++;
    }

    private void addEdge(final int u, final int v, final double weight) {
        insert(u, v, 1, weight);
        insert(v, u, 0, -weight);
    }

    private boolean spfa() {
        Arrays.fill(vis, false);
        Arrays.fill(pre, -1);
        Arrays.fill(d, Double.MAX_VALUE / 2);
        d[source] = 0.0;
        vis[source] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(source);

        while (!q.isEmpty()) {
            int u = q.poll();
            vis[u] = false;

            for (int i = p[u]; i != -1; i = edges[i].getNext()) {
                int v = edges[i].getV();
                int c = edges[i].getCapacity();
                double w = edges[i].getWeight();

                if (c > 0 && d[v] > d[u] + w) {
                    d[v] = d[u] + w;
                    pre[v] = i;

                    if (!vis[v]) {
                        q.add(v);
                        vis[v] = true;
                    }
                }
            }
        }
        return pre[terminal] != -1;
    }

    HashMap<Integer, Integer> getAns() {
        while (spfa()) {
            int flow = Integer.MAX_VALUE;

            for (int i = terminal; i != source; i = edges[pre[i] ^ 1].getV()) {
                flow = Math.min(flow, edges[pre[i]].getCapacity());
            }

            ans += flow;

            for (int i = terminal; i != source; i = edges[pre[i] ^ 1].getV()) {
                edges[pre[i]].setCapacity(edges[pre[i]].getCapacity() - flow);
                edges[pre[i] ^ 1].setCapacity(
                        edges[pre[i] ^ 1].getCapacity() + flow);
                res += flow * edges[pre[i]].getWeight();
            }
        }
        HashMap<Integer, Integer> allocation = new HashMap<>();

        for (int u = 1; u <= n; u++) {
            for (int i = p[u]; i != -1; i = edges[i].getNext()) {
                int v = edges[i].getV();
                int c = edges[i].getCapacity();
                if (c == 0) {
                    allocation.put(u, v - n);
                }
            }
        }

        return allocation;
    }

    /**
     * Work on the user with pets.
     *
     * @param userWithPets The user with pets.
     * @return The result.
     */
    public String work(
            final HashMap<String, Vector<MatchingPair>> userWithPets) {
        Vector<String> users = new Vector<>();
        Vector<String> pets = new Vector<>();

        for (Map.Entry<String, Vector<MatchingPair>> entry
                : userWithPets.entrySet()) {
            Vector<MatchingPair> thesePets = entry.getValue();
            users.add(entry.getKey());
            for (MatchingPair pet : thesePets) {
                pets.add(pet.getFirst());
            }
        }

        normForUser.init();
        normForPet.init();

        normForUser.normalize(users);
        normForPet.normalize(pets);

        n = normForUser.getSize();
        m = normForPet.getSize();

        source = n + m + 1;
        terminal = n + m + 2;

        init(n + m + REDUNDANCY, (n + m + REDUNDANCY) * (n + m + REDUNDANCY));

        for (Map.Entry<String, Vector<MatchingPair>> entry
                : userWithPets.entrySet()) {
            Vector<MatchingPair> petIds = entry.getValue();
            for (MatchingPair petInfo : petIds) {
                addEdge(normForUser.getNormalized(entry.getKey()),
                        normForPet.getNormalized(petInfo.getFirst()) + n,
                        -petInfo.getSecond());
            }
        }

        for (int i = 1; i <= n; i++) {
            addEdge(source, i, 0);
        }

        for (int i = 1; i <= m; i++) {
            addEdge(n + i, terminal, 0);
        }

        HashMap<Integer, Integer> allocation = getAns();
        StringBuilder output = new StringBuilder(
                "We have " + ans + " pairs"
                        + " with a total matching score of " + -res + "\n");
        output.append("Username <-> Pet ID\n");
        for (Map.Entry<Integer, Integer> entry : allocation.entrySet()) {
            int userId = entry.getKey();
            int petId = entry.getValue();
            output.append(normForUser.getOriginal(userId)).append(" <-> ")
                    .append(normForPet.getOriginal(petId)).append("\n");
        }
        return output.toString();
    }
}
