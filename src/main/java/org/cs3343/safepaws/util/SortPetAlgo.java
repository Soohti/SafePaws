package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;

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
        List<Pet> pets = DbManager.getAllPets();
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
            this.pet = new Pet(newP);
            this.matchScore = newSc;
        }

        /**
         * Gets the pet.
         *
         * @return the pet
         */
        public Pet getPet() {
            return new Pet(pet);
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
