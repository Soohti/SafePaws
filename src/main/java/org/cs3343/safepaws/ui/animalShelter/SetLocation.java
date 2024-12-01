package org.cs3343.safepaws.ui.animalShelter;

import org.cs3343.safepaws.entity.LocationPoint;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.handler.SetLocationHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;
public class SetLocation extends UI {
    /**
     * The minimum coordinate.
     */
    private static final double MIN_COORDINATE = 0;
    /**
     * The maximum coordinate.
     */
    private static final double MAX_COORDINATE = 100;
    /**
     * The name of the SetLocation UI.
     */
    private static final String NAME =
            "Set Location";
    /**
     * Constructs a new MatchPets UI.
     *
     * @param pReferrer the referrer of the UI
     */
    public SetLocation(final UI pReferrer) {
        super(NAME, pReferrer);
    }
    @Override
    protected final UI execute(final Session session) {
        session.println(
                "Please input float numbers from " + MIN_COORDINATE + " to "
                        + MAX_COORDINATE + ".");
        session.println("Please enter your X coordinate: ");
        String sX = session.requestInput();
        session.println("Please enter your Y coordinate: ");
        String sY = session.requestInput();
        try {
            float x = Float.parseFloat(sX);
            float y = Float.parseFloat(sY);
            if (x < MIN_COORDINATE || x > MAX_COORDINATE || y < MIN_COORDINATE
                    || y > MAX_COORDINATE) {
                session.println("Invalid input. Please try again.");
                return this;
            }
            var location = new LocationPoint(x, y);
            SetLocationHandler handler = new SetLocationHandler();
            handler.updateShelterLocation((Shelter) session.getAccount(),
                    location);
            session.println("Location update complete.");
        } catch (NumberFormatException e) {
            session.println("Invalid input. Please try again.");
            return this;
        }
        return this.getReferrer();
    }

    /**
     * Checks if is visible to.
     *
     * @param session the session
     * @return true, if is visible to
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() instanceof Shelter;
    }
}
