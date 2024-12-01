package org.cs3343.safepaws.ui.menu;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.Logout;
import org.cs3343.safepaws.ui.animalShelter.PlanRoute;
import org.cs3343.safepaws.ui.animalShelter.SetLocation;
import org.cs3343.safepaws.util.Session;
public class ShelterMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Shelter Menu";
    /**
     * The description of the menu.
     */
    private static final String DESCRIPTION = "Welcome to SafePaws!";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new Logout(this),
            new SetLocation(this),
            new PlanRoute(this)
    };
    /**
     * Constructs a new MainMenu.
     */
    public ShelterMenu() {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }
    @Override
    public final boolean isVisibleTo(final Session session) {
        return session.getAccount() instanceof Shelter;
    }
}
