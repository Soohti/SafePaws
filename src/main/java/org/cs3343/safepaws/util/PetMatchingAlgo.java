package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Pet;

/**
 * This class provides algorithms for matching pets with members based on
 * various criteria.
 */
public final class PetMatchingAlgo implements Algorithm {
    /**
     * Weight for extroversion level.
     */
    private static final double EXTROVERSION_WEIGHT = 1.5;
    /**
     * Weight for daily activity level.
     */
    private static final double DAILY_ACTIVITY_WEIGHT = 1.5;
    /**
     * Weight for house size.
     */
    private static final double HOUSE_SIZE_WEIGHT = 2.0;
    /**
     * Weight for work hours.
     */
    private static final double WORK_HOURS_WEIGHT = 1.0;
    /**
     * Weight for number of family members.
     */
    private static final double FAMILY_MEMBERS_WEIGHT = 1.0;
    /**
     * Weight for pet experience.
     */
    private static final double PET_EXPERIENCE_WEIGHT = 1.5;
    /**
     * Weight for financial budget.
     */
    private static final double FINANCIAL_BUDGET_WEIGHT = 5.0;
    /**
     * Weight for species.
     */
    private static final double SPECIES_WEIGHT = 2.0;
    /**
     * Weight for breed.
     */
    private static final double BREED_WEIGHT = 2.0;
    /**
     * Weight for gender.
     */
    private static final double GENDER_WEIGHT = 1.0;

    /**
     * Minimum house size.
     */
    private static final double MIN_HOUSE_SIZE = 10.0;
    /**
     * Maximum house size.
     */
    private static final double MAX_HOUSE_SIZE = 300.0;
    /**
     * Minimum weight.
     */
    private static final double MIN_WEIGHT = 1.0;
    /**
     * Maximum weight.
     */
    private static final double MAX_WEIGHT = 40.0;
    /**
     * Default work hours.
     */
    private static final double DEFAULT_WORK_HOURS = 5.0;
    /**
     * Default number of family members.
     */
    private static final double DEFAULT_FAMILY_MEMBERS = 3.0;
    /**
     * Default pet experience.
     */
    private static final double DEFAULT_PET_EXPERIENCE = 5.0;
    /**
     * Default financial budget.
     */
    private static final double DEFAULT_FINANCIAL_BUDGET = 500.0;
    /**
     * Normalization factor.
     */
    private static final double NORMALIZATION_FACTOR = 10.0;
    /**
     * Weight for cosine similarity.
     */
    private static final double COSINE_SIMILARITY_WEIGHT = 0.5;

    // Private constructor to prevent instantiation
    private PetMatchingAlgo() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Normalizes a value within a given range.
     *
     * @param value the value to normalize
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return the normalized value
     */
    private static double normalizeValue(final double value,
                                         final double min, final double max) {
        return NORMALIZATION_FACTOR * (value - min) / (max - min);
    }

    /**
     * Calculates the Euclidean distance between a member and a pet.
     *
     * @param user the member
     * @param pet  the pet
     * @return the Euclidean distance
     */
    private static double calculateEuclideanDistance(final Member user,
                                                     final Pet pet) {
        MemberProfile profile = user.getProfile();
        double normalizedHouseSize = normalizeValue(profile.getHouseSize(),
                MIN_HOUSE_SIZE, MAX_HOUSE_SIZE);
        double normalizedWeight = normalizeValue(pet.getWeight(),
                MIN_WEIGHT, MAX_WEIGHT);

        double distance = 0.0;
        distance += Math.pow(profile.getExtroversionLevel()
                - pet.getActivityLevel(), 2);
        distance += Math.pow(profile.getDailyActivityLevel()
                - pet.getActivityLevel(), 2);
        distance += Math.pow(normalizedHouseSize - normalizedWeight, 2);
        distance += Math.pow(profile.getWorkHours() - DEFAULT_WORK_HOURS, 2);
        distance += Math.pow(profile.getNumberOfFamilyMembers()
                - DEFAULT_FAMILY_MEMBERS, 2);
        distance += Math.pow(profile.getPreviousPetExperience()
                - DEFAULT_PET_EXPERIENCE, 2);
        distance += Math.pow(profile.getFinancialBudget()
                - DEFAULT_FINANCIAL_BUDGET, 2);

        return Math.sqrt(distance);
    }

    /**
     * Calculates the weighted cosine similarity between a member and a pet.
     *
     * @param user the member
     * @param pet  the pet
     * @return the weighted cosine similarity
     */
    private static double calculateWeightedCosineSimilarity(final Member user,
                                                            final Pet pet) {
        MemberProfile profile = user.getProfile();
        double normalizedHouseSize = normalizeValue(profile.getHouseSize(),
                MIN_HOUSE_SIZE, MAX_HOUSE_SIZE);
        double normalizedWeight = normalizeValue(pet.getWeight(),
                MIN_WEIGHT, MAX_WEIGHT);

        double userMagnitude = Math.sqrt(Math.pow(profile.getExtroversionLevel()
                * EXTROVERSION_WEIGHT, 2)
                + Math.pow(profile.getDailyActivityLevel()
                * DAILY_ACTIVITY_WEIGHT, 2)
                + Math.pow(normalizedHouseSize * HOUSE_SIZE_WEIGHT, 2)
                + Math.pow(profile.getWorkHours() * WORK_HOURS_WEIGHT, 2)
                + Math.pow(profile.getNumberOfFamilyMembers()
                * FAMILY_MEMBERS_WEIGHT, 2)
                + Math.pow(profile.getPreviousPetExperience()
                * PET_EXPERIENCE_WEIGHT, 2)
                + Math.pow(profile.getFinancialBudget()
                * FINANCIAL_BUDGET_WEIGHT, 2));

        double petMagnitude = Math.sqrt(Math.pow(pet.getActivityLevel()
                * EXTROVERSION_WEIGHT, 2)
                + Math.pow(normalizedWeight * HOUSE_SIZE_WEIGHT, 2)
                + Math.pow(pet.getHealthStatus() * 1.0, 2));

        double dotProduct = (profile.getExtroversionLevel()
                * EXTROVERSION_WEIGHT * pet.getActivityLevel()
                * EXTROVERSION_WEIGHT)
                + (profile.getDailyActivityLevel()
                * DAILY_ACTIVITY_WEIGHT * pet.getActivityLevel()
                * DAILY_ACTIVITY_WEIGHT)
                + (normalizedHouseSize * HOUSE_SIZE_WEIGHT
                * normalizedWeight * HOUSE_SIZE_WEIGHT)
                + (profile.getWorkHours() * WORK_HOURS_WEIGHT
                * DEFAULT_WORK_HOURS * WORK_HOURS_WEIGHT)
                + (profile.getNumberOfFamilyMembers()
                * FAMILY_MEMBERS_WEIGHT * DEFAULT_FAMILY_MEMBERS
                * FAMILY_MEMBERS_WEIGHT)
                + (profile.getPreviousPetExperience()
                * PET_EXPERIENCE_WEIGHT
                * DEFAULT_PET_EXPERIENCE
                * PET_EXPERIENCE_WEIGHT)
                + (profile.getFinancialBudget()
                * FINANCIAL_BUDGET_WEIGHT
                * DEFAULT_FINANCIAL_BUDGET
                * FINANCIAL_BUDGET_WEIGHT);

        return dotProduct / (userMagnitude * petMagnitude);
    }

    /**
     * Calculates the match score between a member and a pet.
     *
     * @param user the member
     * @param pet  the pet
     * @return the match score
     */
    public static double calculateMatch(final Member user, final Pet pet) {
        MemberProfile profile = user.getProfile();
        double euclideanDistance = calculateEuclideanDistance(
                user, pet);
        double cosineSimilarity = calculateWeightedCosineSimilarity(
                user, pet);

        double normalizedCosineSimilarity = (cosineSimilarity + 1) / 2;

        // Normalize the Euclidean distance (inversely)
        double normalizedEuclideanDistance = 1 / (1 + euclideanDistance);

        // Add additional criteria for species, breed, and gender
        double speciesSimilarity = profile.getPreferredSpecies()
                .equalsIgnoreCase(pet.getSpecies())
                ? 1.0 : 0.0;
        double breedSimilarity = profile.getPreferredBreed()
                .equalsIgnoreCase(pet.getBreed())
                ? 1.0 : 0.0;
        double genderSimilarity = profile.getGender()
                .equalsIgnoreCase(pet.getGender())
                ? 1.0 : 0.0;

        double speciesScore = speciesSimilarity * SPECIES_WEIGHT;
        double breedScore = breedSimilarity * BREED_WEIGHT;
        double genderScore = genderSimilarity * GENDER_WEIGHT;

        // Calculate final match score
        return (normalizedEuclideanDistance * COSINE_SIMILARITY_WEIGHT)
                + (normalizedCosineSimilarity * COSINE_SIMILARITY_WEIGHT)
                + speciesScore + breedScore + genderScore;
    }
}
