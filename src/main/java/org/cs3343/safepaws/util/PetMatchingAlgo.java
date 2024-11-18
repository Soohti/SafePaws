package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;

/**
 * This class provides algorithms for matching pets with members
 * based on various criteria.
 */
public class PetMatchingAlgo implements Algorithm {
    private static final double EXTROVERSION_WEIGHT = 1.5;
    private static final double DAILY_ACTIVITY_WEIGHT = 1.5;
    private static final double HOUSE_SIZE_WEIGHT = 2.0;
    private static final double WORK_HOURS_WEIGHT = 1.0;
    private static final double FAMILY_MEMBERS_WEIGHT = 1.0;
    private static final double PET_EXPERIENCE_WEIGHT = 1.5;
    private static final double FINANCIAL_BUDGET_WEIGHT = 5.0;
    private static final double SPECIES_WEIGHT = 2.0;
    private static final double BREED_WEIGHT = 2.0;
    private static final double GENDER_WEIGHT = 1.0;

    /**
     * Normalizes a value within a given range.
     *
     * @param value the value to normalize
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @return the normalized value
     */
    private static double normalizeValue(final double value, final double min,
                                         final double max) {
        return 10 * (value - min) / (max - min);
    }

    /**
     * Calculates the Euclidean distance between a member and a pet.
     *
     * @param user the member
     * @param pet the pet
     * @return the Euclidean distance
     */
    private static double calculateEuclideanDistance(final Member user,
                                                     final Pet pet) {
        MemberProfile profile = user.getProfile();
        double normalizedHouseSize = normalizeValue(profile.houseSize, 10, 300);
        double normalizedWeight = normalizeValue(pet.weight, 1, 40);

        double distance = 0.0;
        distance += Math.pow(profile.extroversionLevel - pet.activityLevel, 2);
        distance += Math.pow(profile.dailyActivityLevel - pet.activityLevel, 2);
        distance += Math.pow(normalizedHouseSize - normalizedWeight, 2);
        distance += Math.pow(profile.workHours - 5, 2);
        distance += Math.pow(profile.numberOfFamilyMembers - 3, 2);
        distance += Math.pow(profile.previousPetExperience - 5, 2);
        distance += Math.pow(profile.financialBudget - 500, 2);

        return Math.sqrt(distance);
    }

    /**
     * Calculates the weighted cosine similarity between a member and a pet.
     *
     * @param user the member
     * @param pet the pet
     * @return the weighted cosine similarity
     */
    private static double calculateWeightedCosineSimilarity(final Member user,
                                                            final Pet pet) {
        MemberProfile profile = user.getProfile();
        double normalizedHouseSize = normalizeValue(profile.houseSize, 20, 300);
        double normalizedWeight = normalizeValue(pet.weight, 1, 100);

        double userMagnitude = Math.sqrt(
                Math.pow(profile.extroversionLevel * EXTROVERSION_WEIGHT, 2)
                + Math.pow(profile.dailyActivityLevel 
                           * DAILY_ACTIVITY_WEIGHT, 2)
                + Math.pow(normalizedHouseSize * HOUSE_SIZE_WEIGHT, 2)
                + Math.pow(profile.workHours * WORK_HOURS_WEIGHT, 2)
                + Math.pow(profile.numberOfFamilyMembers
                           * FAMILY_MEMBERS_WEIGHT, 2)
                + Math.pow(profile.previousPetExperience 
                           * PET_EXPERIENCE_WEIGHT, 2)
                + Math.pow(profile.financialBudget 
                           * FINANCIAL_BUDGET_WEIGHT, 2));

        double petMagnitude = Math.sqrt(
                Math.pow(pet.activityLevel * EXTROVERSION_WEIGHT, 2)
                + Math.pow(normalizedWeight * HOUSE_SIZE_WEIGHT, 2)
                + Math.pow(pet.healthStatus * 1.0, 2)); 

        double dotProduct = (profile.extroversionLevel 
                             * EXTROVERSION_WEIGHT
                * pet.activityLevel * EXTROVERSION_WEIGHT)
                + (profile.dailyActivityLevel * DAILY_ACTIVITY_WEIGHT
                * pet.activityLevel * DAILY_ACTIVITY_WEIGHT)
                + (normalizedHouseSize * HOUSE_SIZE_WEIGHT
                * normalizedWeight * HOUSE_SIZE_WEIGHT)
                + (profile.workHours * WORK_HOURS_WEIGHT 
                   * 8 * WORK_HOURS_WEIGHT)
                + (profile.numberOfFamilyMembers * FAMILY_MEMBERS_WEIGHT
                * 3 * FAMILY_MEMBERS_WEIGHT)
                + (profile.previousPetExperience * PET_EXPERIENCE_WEIGHT
                * 5 * PET_EXPERIENCE_WEIGHT)
                + (profile.financialBudget * FINANCIAL_BUDGET_WEIGHT
                * 500 * FINANCIAL_BUDGET_WEIGHT);

        return dotProduct / (userMagnitude * petMagnitude);
    }

    /**
     * Calculates the match score between a member and a pet.
     *
     * @param user the member
     * @param pet the pet
     * @return the match score
     */
    public static double calculateMatch(final Member user, final Pet pet) {
        MemberProfile profile = user.getProfile();
        double euclideanDistance = calculateEuclideanDistance(user, pet);
        double cosineSimilarity = calculateWeightedCosineSimilarity(user, pet);

        double normalizedCosineSimilarity = (cosineSimilarity + 1) / 2;

        // Weight for the similarity metrics
        double weightEuclidean = 0.5;
        double weightCosine = 0.5;

        // Normalize the Euclidean distance (inversely)
        double normalizedEuclideanDistance = 1 / (1 + euclideanDistance);

        // Add additional criteria for species, breed, and gender
        double speciesSimilarity = profile.preferredSpecies
                .equalsIgnoreCase(pet.species) ? 1.0 : 0.0;
        double breedSimilarity = profile.preferredBreed
                .equalsIgnoreCase(pet.breed) ? 1.0 : 0.0;
        double genderSimilarity = profile.gender
                .equalsIgnoreCase(pet.gender) ? 1.0 : 0.0;

        double speciesScore = speciesSimilarity * SPECIES_WEIGHT;
        double breedScore = breedSimilarity * BREED_WEIGHT;
        double genderScore = genderSimilarity * GENDER_WEIGHT;

        // Calculate final match score
        return (normalizedEuclideanDistance * weightEuclidean)
                + (normalizedCosineSimilarity * weightCosine)
                + speciesScore + breedScore + genderScore;
    }
}
