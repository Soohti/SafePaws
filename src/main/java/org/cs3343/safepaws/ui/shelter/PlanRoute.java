package org.cs3343.safepaws.ui.shelter;

import org.cs3343.safepaws.algorithm.MinPetPath;
import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.handler.PlanRouteHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.util.Vector;

public final class PlanRoute extends UI {

    /**
     * The name of the MatchPets UI.
     */
    private static final String NAME =
            "Plan a Shortest Route to Collect Stray Animals";

    /**
     * Constructs a new MatchPets UI.
     *
     * @param pReferrer the referrer of the UI
     */
    public PlanRoute(final UI pReferrer) {
        super(NAME, pReferrer);
    }

    @Override
    protected UI execute(final Session session) {
        PlanRouteHandler handler = new PlanRouteHandler();
        var startLocation = ((Shelter) session.getAccount()).getLocationPoint();
        var allAnimalLocations = handler.findAllLocationPoints(true);
        for (int i = 0; i < allAnimalLocations.size(); i++) {
            session.println("Stray Animal " + (i + 1) + ": "
                    + allAnimalLocations.get(i));
        }
        session.println("Please enter the ID(s) of the stray animals"
                + " you want to collect, separated by whitespace:");
        var input = session.requestInput();
        var animalIDs = input.split(" ");
        var selectedAnimalLocations = new Vector<LocationPoint>();
        try {
            for (var sAnimalID : animalIDs) {
                var iAnimalID = Integer.parseInt(sAnimalID);
                var animalLocation = allAnimalLocations.get(iAnimalID - 1);
                selectedAnimalLocations.add(animalLocation);
            }
        } catch (Exception e) {
            session.println("Invalid input. Please try again.");
            return this.getReferrer();
        }
        LocationPoint startLocationPoint = new LocationPoint(0, 0);
        if (startLocation != null) {
            startLocationPoint = new LocationPoint(startLocation.getXValue(),
                    startLocation.getYValue());
        }
        String output = new MinPetPath().work(startLocationPoint,
                selectedAnimalLocations);
        session.println(output);
        return this.getReferrer();
    }
}
