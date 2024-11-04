package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;

public class ViewPets extends UI {

    public ViewPets(UI referrer) {
        super("View Pets", referrer);
    }

    @Override
    protected UI execute(Session session) throws IOException {
        session.println("Displaying available pets...");
        // 此处添加显示宠物列表的逻辑
        return this.getReferrer(); // 返回到主菜单
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}
