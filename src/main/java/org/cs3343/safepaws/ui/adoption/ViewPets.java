package org.cs3343.safepaws.ui.adoption;

import java.util.*;
import org.cs3343.safepaws.entity.*;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 */
public class ViewPets extends UI {

    private static final String NAME = "View Pets";

    /**
     * @param referrer
     */
    public ViewPets(final UI referrer) {
        super("View Pets", referrer);
    }

    @Override
    protected UI execute(final Session session) throws IOException {
    	Member member = (Member)(session.getAccount());
        session.println("Displaying available pets...");
        List<Pet> sortedPets = SortPetAlgo.sortPetsByMatch(member);
        for (Pet pet : sortedPets) {

            session.println("Id: " + pet.getId() + ", Name: " + pet.getName() + ", Species: " + pet.getSpecies() + ", Breed: "
                    + pet.getBreed() + ", Age: " + pet.getAge() + ", Weight: " + pet.getWeight() + ", Gender: "
                    + pet.getGender() + ", ActivityLevel: " + pet.getActivityLevel() + ", HealthStatus: "
                    + pet.getHealthStatus() + " MatchingScore: " + PetMatchingAlgo.calculateMatch(member, pet));
        }
        return this.getReferrer();
    }

    @Override
    public boolean isVisibleTo(final Session session) {
        return session.getAccount() != null && "M".equals(session.getAccount().getRole());
    }
}