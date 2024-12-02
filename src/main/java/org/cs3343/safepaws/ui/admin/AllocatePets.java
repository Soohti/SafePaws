package org.cs3343.safepaws.ui.admin;

import org.cs3343.safepaws.algorithm.MaxMatchingScore;
import org.cs3343.safepaws.algorithm.PetMatchingAlgo;
import org.cs3343.safepaws.entity.MatchingPair;
import org.cs3343.safepaws.handler.AllocatePetsHandler;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.Session;

import java.util.HashMap;
import java.util.Vector;

public final class AllocatePets extends UI {

    /**
     * The name of the AllocatePets UI.
     */
    private static final String NAME =
            "Allocate Idle Pets to Members";

    /**
     * Constructs a new AllocatePets UI.
     *
     * @param pReferrer the referrer of the UI
     */
    public AllocatePets(final UI pReferrer) {
        super(NAME, pReferrer);
    }

    @Override
    protected UI execute(final Session session) {
        var userWithPets = new HashMap<String, Vector<MatchingPair>>();
        AllocatePetsHandler handler = new AllocatePetsHandler();
        var members = handler.getAllMembers();
        var freePets = handler.getAllFreePets();
        for (var member : members) {
            var matchingPairs = new Vector<MatchingPair>();
            for (var pet : freePets) {
                var score = PetMatchingAlgo.calculateMatch(member, pet);
                matchingPairs.add(
                        new MatchingPair(String.valueOf(pet.getId()), score));
            }
            userWithPets.put(member.getUsername(), matchingPairs);
        }
        String output = new MaxMatchingScore().work(userWithPets);
        session.println(output);
        return this.getReferrer();
    }
}
