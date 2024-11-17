package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortPetAlgo implements Algorithm {

    /**
     * @param user
     * @return
     */
    public static List<Pet> sortPetsByMatch(Member user) {
        List<Pet> pets = DbManager.getAllPets();
        List<PetMatchScore> petMatchScores = new ArrayList<>();

        for (Pet pet : pets) {
            double matchScore = PetMatchingAlgo.calculateMatch(user, pet);
            petMatchScores.add(new PetMatchScore(pet, matchScore));
        }

        Collections.sort(petMatchScores, new Comparator<PetMatchScore>() {
            @Override
            public int compare(PetMatchScore o1, PetMatchScore o2) {
                return Double.compare(o2.getMatchScore(), o1.getMatchScore());
            }
        });

        List<Pet> sortedPets = new ArrayList<>();
        for (PetMatchScore petMatchScore : petMatchScores) {
            sortedPets.add(petMatchScore.getPet());
        }

        return sortedPets;
    }

    /**
     *
     */
    public static class PetMatchScore {
        private Pet pet;
        private double matchScore;

        public PetMatchScore(Pet pet, double matchScore) {
            this.pet = pet;
            this.matchScore = matchScore;
        }

        public Pet getPet() {
            return pet;
        }

        public double getMatchScore() {
            return matchScore;
        }
    }
}
