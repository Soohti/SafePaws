package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.Exit;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public abstract class Menu extends UI {
    /**
     * The description of the menu.
     */
    private final String description;
    /**
     * The menu items.
     */
    private UI[] menuItems;

    /**
     * Constructs a new Menu.
     *
     * @param name     the name of the menu
     * @param descr    the description of the menu
     * @param referrer the referrer of the menu
     */
    public Menu(final String name, final String descr, final UI referrer) {
        super(name, referrer);
        this.description = descr;
    }

    protected final void setMenuItems(final UI[] pMenuItems) {
        this.menuItems = pMenuItems;
    }

    @Override
    protected final UI execute(final Session session) throws IOException {
        if (description != null) {
            session.println(description);
        }
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i].isVisibleTo(session)) {
                session.println((i + 1) + ". " + menuItems[i].getName());
            }
        }
        if (this.getReferrer() != null) {
            session.println("B. Back");
        }
        if(this.getReferrer() == null) {
        	session.println("E. Exit");
        }
        session.print("Please enter your choice: ");

        do {
            String choice = session.requestInput();
            if ("E".equals(choice)) {
                return new Exit(this);
            } else if (this.getReferrer() != null && "B".equals(choice)) {
                return this.getReferrer();
            } else {
                try {
                    int choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 0 && choiceInt <= menuItems.length
                            && menuItems[choiceInt - 1].isVisibleTo(session)) {
                        return menuItems[choiceInt - 1];
                    } else {
                        session.print("Invalid choice, please try again: ");
                    }
                } catch (NumberFormatException e) {
                    session.print("Invalid choice, please try again: ");
                }
            }
        } while (true);
    }
}
