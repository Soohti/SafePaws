/**
 * The `FrequentSightingAreaOfStrayAnimals` class is a part of
 * the user interface (UI) for the SafePaws application,
 * specifically designed for the animal shelter module.
 * This class allows an admin user to determine the frequent
 * sighting areas of stray animals using KMeans clustering.
 *
 * <p>The class extends the {@link UI} class and provides functionality to:
 * 1. Retrieve all animal location points from the database.
 * 2. Allow the user to choose between optimal K clustering
 * (determined using the Elbow Method) or custom K clustering.
 * 3. Run KMeans clustering with the chosen K value.
 * 4. Display the resulting shelter locations and clusters.
 * 5. (Currently unimplemented) Select an application for
 * adoption and check its status.
 *
 * <p>This class is intended to be used by admin users only, as indicated by the
 * {@link #isVisibleTo(Session)} method.
 *
 * @see UI
 * @see DbManager
 * @see ElbowUtil
 * @see KMeans
 * @see Shelter
 */
package org.cs3343.safepaws.ui.animalShelter;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.handler.ElbowUtil;
import org.cs3343.safepaws.handler.KMeans;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FrequentSightingAreaOfStrayAnimals extends UI {

    /**
     * The maximum number of K.
     */
    private final int maxK = 10;
    /**
     * The name of this UI interface.
     */
    private static final String NAME =
            "Admin search efficiency point for starting capture";
    /**
     * Constructor for the `FrequentSightingAreaOfStrayAnimals` class.
     *
     * @param referrer The UI component that referred to this component.
     */
    public FrequentSightingAreaOfStrayAnimals(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the functionality of this UI component.
     * This method retrieves all animal location points from the
     * database, allows the user to choose the clustering mode
     * (optimal or custom K), runs KMeans clustering,
     * and displays the resulting shelter locations and clusters.
     * @param session The current user session.
     * @return The referring UI component, or {@code null} if the
     * execution was successful.
     * @throws IOException  If an I/O error occurs.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    protected UI execute(final Session session)
            throws IOException, SQLException {
        try {
            var animalLocations = DbManager.listAllLocationPoint();
            session.println("Choose mode: 1 for optimal k clustering"
                    + ", 2 for custom k clustering:");
            int mode = Integer.parseInt(session.requestInput());

            int k = 0;
            if (mode == 1) {
                k = ElbowUtil.findOptimalK(animalLocations, maxK);
                session.println("Optimal number of shelters (K) is: " + k);
            } else if (mode == 2) {
                session.print("Enter the number of shelters"
                        + " (K) you want to use: ");
                k = Integer.parseInt(session.requestInput());
            } else {
                session.println("Invalid mode selected. Exiting program.");
                return this.getReferrer();
            }
            KMeans kmeans = new KMeans(k);
            kmeans.fit(animalLocations);

            List<List<LocationPoint>> clusters = kmeans.getClusters();

            Shelter shelter = new Shelter(clusters.stream()
                    .map(List::getFirst)
                    .toList());
            shelter.displayShelters();
        } catch (SQLException e) {
            session.println("Error creating account: " + e.getMessage());
        }

        return this.getReferrer();
    }

    /**
     * Determines if this UI component is visible to the given user session.
     * This method checks if the user is logged in and has the 'A' (admin) role.
     *
     * @param session The current user session.
     * @return {@code true} if the component is visible to the user,
     * {@code false} otherwise.
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        //TODO please help to change this part after merge
        //return session.getAccount() != null && "A".equals(session
        //        .getAccount().getRole());
        return true;
    }
}
