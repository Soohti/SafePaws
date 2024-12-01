package org.cs3343.safepaws.ui.member;

import org.cs3343.safepaws.algorithm.PetMatchingAlgo;
import org.cs3343.safepaws.algorithm.SortPetAlgo;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.util.List;

/**
 * The ViewPets class is responsible for
 * displaying available pets to the user.
 */
public final class ViewPets extends UI {

    /**
     * The name of this UI component.
     */
    private static final String NAME = "View Pet Details";

    /**
     * Constructs a new ViewPets instance.
     *
     * @param referrer the UI that referred to this instance
     */
    public ViewPets(final UI referrer) {
        super(NAME, referrer);
    }

    /**
     * Executes the ViewPets UI.
     *
     * @param session the current session
     * @return the referrer UI
     */
    @Override
    protected UI execute(final Session session) {
        Member member = (Member) (session.getAccount());
        session.println("Displaying all pets...");
        List<Pet> sortedPets = SortPetAlgo.sortPetsByMatch(member);
        for (Pet pet : sortedPets) {
            session.println("Id: " + pet.getId() + ", Name: "
                    + pet.getName() + ", Species: " + pet.getSpecies()
                    + ", Breed: " + pet.getBreed() + ", Age: "
                    + pet.getAge() + ", Weight: " + pet.getWeight()
                    + ", Gender: " + pet.getGender() + ", ActivityLevel: "
                    + pet.getActivityLevel() + ", HealthStatus: "
                    + pet.getHealthStatus() + ", AdoptionState: "
                    + pet.getState() + ", MatchingScore: "
                    + PetMatchingAlgo.calculateMatch(member, pet));
        }
        return this.getReferrer();
    }
}
