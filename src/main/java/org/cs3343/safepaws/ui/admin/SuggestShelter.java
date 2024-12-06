/**
 * The `SuggestShelter` class is a part of
 * the user interface (UI) for the SafePaws application,
 * specifically designed for the animal shelter module.
 * This class allows an admin user to determine the frequent
 * sighting areas of stray animals using AnimalClusterAnalysis clustering.
 *
 * <p>The class extends the {@link UI} class and provides functionality to:
 * 1. Retrieve all animal location points from the database.
 * 2. Allow the user to choose between optimal K clustering
 * (determined using the Elbow Method) or custom K clustering.
 * 3. Run AnimalClusterAnalysis clustering with the chosen K value.
 * 4. Display the resulting shelter locations and clusters.
 * 5. (Currently unimplemented) Select an application for
 * adoption and check its status.
 *
 * <p>This class is intended to be used by admin users only.
 *
 * @see UI
 * @see DbManager
 * @see ElbowUtil
 * @see KMeans
 * @see Shelter
 */
package org.cs3343.safepaws.ui.admin;

import org.cs3343.safepaws.algorithm.AnimalClusterAnalysis;
import org.cs3343.safepaws.algorithm.FindingOptimalShelterNumber;
import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.RecommendShelter;
import org.cs3343.safepaws.handler.ReadLocationPointHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.util.ArrayList;
import java.util.List;

public final class SuggestShelter extends UI {

    /**
     * The maximum number of K.
     */
    private static final int MAXK = 10;
    /**
     * The name of this UI interface.
     */
    private static final String NAME =
            "Recommend New Shelter Locations for Stray Animals";

    /**
     * Constructor for the `SuggestShelter` class.
     *
     * @param referrer The UI component that referred to this component.
     */
    public SuggestShelter(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the functionality of this UI component.
     * This method retrieves all animal location points from the
     * database, allows the user to choose the clustering mode
     * (optimal or custom K), runs AnimalClusterAnalysis clustering,
     * and displays the resulting shelter locations and clusters.
     *
     * @param session The current user session.
     * @return The referring UI component, or {@code null} if the
     * execution was successful.
     */
    @Override
    protected UI execute(final Session session) {
        ReadLocationPointHandler handler = new ReadLocationPointHandler();
        var animalLocations = handler.findAllPetLocations();
        session.println("Choose mode: 1 for optimal number of shelters"
                + ", 2 for custom number of shelters:");
        int mode = Integer.parseInt(session.requestInput());

        int k;
        if (mode == 1) {
            k = FindingOptimalShelterNumber
                    .findOptimalK(animalLocations, MAXK);
            session.println("Optimal number of"
                    + " recommendShelters (K) is: " + k);
        } else if (mode == 2) {
            session.print("Enter the number of recommendShelters"
                    + " (K) you want to use: ");
            k = Integer.parseInt(session.requestInput());
        } else {
            session.println("Invalid mode selected. Exiting program.");
            return this.getReferrer();
        }
        AnimalClusterAnalysis kmeans = new AnimalClusterAnalysis(k);
        kmeans.fit(animalLocations);

        List<List<LocationPoint>> clusters = kmeans.getClusters();
        List<RecommendShelter> recommendShelters = new ArrayList<>();

        for (List<LocationPoint> cluster : clusters) {
            if (!cluster.isEmpty()) {
                LocationPoint firstLocation = cluster.getFirst();
                recommendShelters.add(new RecommendShelter(firstLocation));
            }
        }

        session.println("RecommendShelter Locations:");
        for (RecommendShelter recommendShelter : recommendShelters) {
            session.print(recommendShelter.toString());
        }

        return this.getReferrer();
    }
}
