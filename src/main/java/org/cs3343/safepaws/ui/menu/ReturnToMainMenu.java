package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

public class ReturnToMainMenu extends UI {
    private final UI mainMenu;

    public ReturnToMainMenu(UI referrer, UI mainMenu) {
        super("Return to Main Menu", referrer);
        this.mainMenu = mainMenu;
    }

    @Override
    protected UI execute(Session session) {
        return mainMenu; // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
}
