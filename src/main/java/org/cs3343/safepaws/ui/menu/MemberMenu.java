package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public class MemberMenu extends Menu {
    private static final String NAME = "Member Menu";
    private static final String DESCRIPTION = "Welcome to the Member Menu!";

    public MemberMenu(UI referrer) {
        super(NAME, DESCRIPTION, referrer);

        UI[] menuItems = new UI[]{
            new ViewPets(this),  
        };
        setMenuItems(menuItems);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}
