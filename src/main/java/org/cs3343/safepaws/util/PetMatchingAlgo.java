package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;

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
     * @param value
     * @param min
     * @param max
     * @return
     */
    private static double normalizeValue(double value, double min, double max) {
        return 10 * (value - min) / (max - min);
    }

    /**
     * @param user
     * @param pet
     * @return
     */
    private static double calculateEuclideanDistance(Member user, Pet pet) {
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
     * @param user
     * @param pet
     * @return
     */
    private static double calculateWeightedCosineSimilarity(Member user,
                                                            Pet pet) {
        MemberProfile profile = user.getProfile();
        double normalizedHouseSize = normalizeValue(profile.houseSize, 20, 300);
        double normalizedWeight = normalizeValue(pet.weight, 1, 100);

        double userMagnitude = Math.sqrt(
                Math.pow(profile.extroversionLevel * EXTROVERSION_WEIGHT, 2)
                        + Math.pow(
                        profile.dailyActivityLevel * DAILY_ACTIVITY_WEIGHT, 2)
                        + Math.pow(normalizedHouseSize * HOUSE_SIZE_WEIGHT, 2)
                        + Math.pow(profile.workHours * WORK_HOURS_WEIGHT, 2)
                        + Math.pow(
                        profile.numberOfFamilyMembers * FAMILY_MEMBERS_WEIGHT,
                        2)
                        + Math.pow(
                        profile.previousPetExperience * PET_EXPERIENCE_WEIGHT,
                        2)
                        + Math.pow(
                        profile.financialBudget * FINANCIAL_BUDGET_WEIGHT, 2));

        double petMagnitude =
                Math.sqrt(Math.pow(pet.activityLevel * EXTROVERSION_WEIGHT, 2)
                                + Math.pow(normalizedWeight * HOUSE_SIZE_WEIGHT, 2)
                                + Math.pow(pet.healthStatus * 1.0, 2) // Assuming
                        // healthStatus
                        // has
                        // default
                        // weight
                        // 1.0
                );

        double dotProduct = (profile.extroversionLevel * EXTROVERSION_WEIGHT
                * pet.activityLevel
                * EXTROVERSION_WEIGHT)
                + (profile.dailyActivityLevel * DAILY_ACTIVITY_WEIGHT
                * pet.activityLevel * DAILY_ACTIVITY_WEIGHT)
                + (normalizedHouseSize * HOUSE_SIZE_WEIGHT * normalizedWeight
                * HOUSE_SIZE_WEIGHT)
                + (profile.workHours * WORK_HOURS_WEIGHT * 8
                * WORK_HOURS_WEIGHT)
                + (profile.numberOfFamilyMembers * FAMILY_MEMBERS_WEIGHT * 3
                * FAMILY_MEMBERS_WEIGHT)
                + (profile.previousPetExperience * PET_EXPERIENCE_WEIGHT * 5
                * PET_EXPERIENCE_WEIGHT)
                + (profile.financialBudget * FINANCIAL_BUDGET_WEIGHT * 500
                * FINANCIAL_BUDGET_WEIGHT);

        return dotProduct / (userMagnitude * petMagnitude);
    }

    /**
     * @param user
     * @param pet
     * @return
     */
    public static double calculateMatch(Member user, Pet pet) {
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
        double speciesSimilarity =
                profile.preferredSpecies.equalsIgnoreCase(pet.species) ? 1.0 :
                        0.0;
        double breedSimilarity =
                profile.preferredBreed.equalsIgnoreCase(pet.breed) ? 1.0 : 0.0;
        double genderSimilarity =
                profile.gender.equalsIgnoreCase(pet.gender) ? 1.0 : 0.0;

        double speciesScore = speciesSimilarity * SPECIES_WEIGHT;
        double breedScore = breedSimilarity * BREED_WEIGHT;
        double genderScore = genderSimilarity * GENDER_WEIGHT;

        // Calculate final match score
        return (normalizedEuclideanDistance * weightEuclidean) + (
                normalizedCosineSimilarity * weightCosine)
                + speciesScore + breedScore + genderScore;
    }
}
