package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 */
public class SubmitApplication extends UI {

    private static final String NAME = "Submit an application";

    /**
     * @param referrer
     */
    public SubmitApplication(final UI referrer) {
        super(NAME, referrer);
    }

    @Override
    protected UI execute(final Session session) throws IOException {
        if (!(session.getAccount() instanceof Member)) {
            session.println("Only members can submit an application.");
            return this.getReferrer();
        }

        Member user = (Member) session.getAccount();

        session.println("Enter the ID of the pet you want to apply for:");
        String userInput = session.requestInput();
        int petId = Integer.parseInt(userInput);
        Pet pet;
        try {
            pet = DbManager.selectPetById(petId);
        } catch (SQLException e) {
            pet = null;
            e.printStackTrace();
        }
        int state = 0;
        if (pet != null) {
            Application application = new Application(user, pet, state);
            try {
                DbManager.insertApplication(application);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.getReferrer();
    }

    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}