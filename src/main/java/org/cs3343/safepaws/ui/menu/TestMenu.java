package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.TestDb;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class TestMenu extends Menu {
    /**
     * Name of the menu.
     */
    private static final String NAME = "Test Menu";
    /**
     * Description of the menu.
     */
    private static final String DESCRIPTION = null;
    /**
     * Menu items.
     */
    private final UI[] menuItems = new UI[] {
            new TestDb(this),
    };

    /**
     * Creates a new TestMenu.
     *
     * @param referrer The referrer.
     */
    public TestMenu(final UI referrer) {
        super(NAME, DESCRIPTION, referrer);
        setMenuItems(menuItems);
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return true;
    }
}
