package org.cs3343.safepaws.ui.adoption;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.PetMatchingAlgo;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.util.SortPetAlgo;

import java.io.IOException;
import java.util.List;

/**
 * The ViewPets class is responsible for displaying available pets to the user.
 */
public class ViewPets extends UI {

    /**
     * The name of this UI component.
     */
    private static final String NAME = "View Pets";

    /**
     * Constructs a new ViewPets instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public ViewPets(final UI referrer) {
        super("View Pets", referrer);
    }

    /**
     * Executes the ViewPets UI.
     *
     * @param session the current session
     * @return the referrer UI
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected UI execute(final Session session) throws IOException {
        Member member = (Member) (session.getAccount());
        session.println("Displaying available pets...");
        List<Pet> sortedPets = SortPetAlgo.sortPetsByMatch(member);
        for (Pet pet : sortedPets) {
            session.println("Id: " + pet.getId() + ", Name: " + pet.getName() + ", Species: " + pet.getSpecies()
                    + ", Breed: " + pet.getBreed() + ", Age: " + pet.getAge() + ", Weight: " + pet.getWeight()
                    + ", Gender: " + pet.getGender() + ", ActivityLevel: " + pet.getActivityLevel() + ", HealthStatus: "
                    + pet.getHealthStatus() + " MatchingScore: " + PetMatchingAlgo.calculateMatch(member, pet));
        }
        return this.getReferrer();
    }

    /**
     * Determines if the ViewPets UI is visible to the current session.
     *
     * @param session the current session
     * @return true if the UI is visible, false otherwise
     */
    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}
