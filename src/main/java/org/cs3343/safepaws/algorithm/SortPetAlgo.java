package org.cs3343.safepaws.algorithm;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.handler.ReadPetHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * SortPetAlgo class implements the Algorithm interface to provide sorting
 * functionality for pets based on match scores.
 */
public final class SortPetAlgo implements Algorithm {

    // Private constructor to prevent instantiation
    private SortPetAlgo() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Sorts pets by match score for a given user.
     *
     * @param user the member for whom the pets are being sorted
     * @return a list of pets sorted by match score
     */
    public static List<Pet> sortPetsByMatch(final Member user) {
        ReadPetHandler handler = new ReadPetHandler();
        List<Pet> pets = handler.findAllPet();
        List<PetMatchScore> petMatchScores = new ArrayList<>();

        for (Pet pet : pets) {
            double matchScore = PetMatchingAlgo.calculateMatch(user, pet);
            petMatchScores.add(new PetMatchScore(pet, matchScore));
        }

        petMatchScores.sort((o1, o2) -> Double.compare(o2.getMatchScore(),
                o1.getMatchScore()));

        List<Pet> sortedPets = new ArrayList<>();
        for (PetMatchScore petMatchScore : petMatchScores) {
            sortedPets.add(petMatchScore.getPet());
        }

        return sortedPets;
    }

    /**
     * PetMatchScore class represents a pet and its match score.
     */
    public static class PetMatchScore {
        /**
         * The pet.
         */
        private final Pet pet;

        /**
         * The match score.
         */
        private final double matchScore;

        /**
         * Constructs a PetMatchScore with the specified pet and match score.
         *
         * @param newP  the pet
         * @param newSc the match score
         */
        public PetMatchScore(final Pet newP, final double newSc) {
            this.pet = newP;
            this.matchScore = newSc;
        }

        /**
         * Gets the pet.
         *
         * @return the pet
         */
        public Pet getPet() {
            return pet;
        }

        /**
         * Gets the match score.
         *
         * @return the match score
         */
        public double getMatchScore() {
            return matchScore;
        }
    }
}
