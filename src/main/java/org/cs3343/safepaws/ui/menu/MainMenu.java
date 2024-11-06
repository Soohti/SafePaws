package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.ReturnToPreviousMenu;
import org.cs3343.safepaws.ui.TestDb;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.account.*;
import org.cs3343.safepaws.ui.adoption.AdminCheckApplication;
import org.cs3343.safepaws.ui.adoption.AdminSeeAllApplication;
import org.cs3343.safepaws.ui.adoption.MemberSeeApplication;
import org.cs3343.safepaws.ui.adoption.SetProfile;
import org.cs3343.safepaws.ui.adoption.SubmitApplication;
import org.cs3343.safepaws.ui.adoption.ViewPets;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.util.Scanner;

import org.cs3343.safepaws.entity.Account;


public class MainMenu extends Menu {
    /**
     * The name of the menu.
     */
    private static final String NAME = "Main Menu";
    /**
     * The description of the menu.
     */
    private static final String DESCRIPTION = "Welcome to SafePaws!";
    /**
     * The menu items.
     */
    private final UI[] menuItems = new UI[] {
            new TestMenu(this),
            new TestDb(this),
            new CreateAccount(this),
            new Login(this),
            new Logout(this),
            new AdminCheckApplication(this),
            new AdminSeeAllApplication(this),
            new ViewPets(this),
            new SetProfile(this),
            new SubmitApplication(this),
            new MemberSeeApplication(this),
    };

    /**
     * Constructs a new MainMenu.
     */
    public MainMenu() {
        super(NAME, DESCRIPTION, null);
        setMenuItems(menuItems);
    }

    @Override
    public final boolean isVisibleTo(final Session session) {
        return true;
    }
    
}
