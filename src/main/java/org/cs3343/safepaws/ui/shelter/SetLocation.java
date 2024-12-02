package org.cs3343.safepaws.ui.shelter;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.ShelterLocation;
import org.cs3343.safepaws.handler.UpdateLocationHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.AccountFactory;
import org.cs3343.safepaws.util.Session;

public final class SetLocation extends UI {
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
    protected UI execute(final Session session) {
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
            Account account = session.getAccount();
            var location =
                    new ShelterLocation(account.getId(), x, y);
            session.setAccount(AccountFactory.createAccount(account.getId(),
                    account.getUsername(), account.getPassword(),
                    account.getRole(), null, location));
            UpdateLocationHandler handler = new UpdateLocationHandler();
            handler.updateShelterLocation(location);
            session.println("Location update complete.");
        } catch (NumberFormatException e) {
            session.println("Invalid input. Please try again.");
            return this;
        }
        return this.getReferrer();
    }
}
