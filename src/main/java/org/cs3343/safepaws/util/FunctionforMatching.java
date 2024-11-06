package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;

public class FunctionforMatching implements Algorithm {
    private static final double EXTROVERSION_WEIGHT = 1.5;
    private static final double DAILY_ACTIVITY_WEIGHT = 1.5;
    private static final double HOUSE_SIZE_WEIGHT = 2.0;
    private static final double WORK_HOURS_WEIGHT = 1.0;
    private static final double FAMILY_MEMBERS_WEIGHT = 1.0;
    private static final double PET_EXPERIENCE_WEIGHT = 1.5;
    private static final double FINANCIAL_BUDGET_WEIGHT = 2.0;
    private static final double BREED_WEIGHT = 2.0;
    private static final double GENDER_WEIGHT = 1.0;
    private static final double AGE_WEIGHT = 1.0;

    private double calculateEuclideanDistance(Member user, Pet pet) {
        double distance = 0.0;
        distance += Math.pow(user.profile.extroversionLevel - pet.activityLevel, 2);
        distance += Math.pow(user.profile.dailyActivityLevel - pet.activityLevel, 2);
        distance += Math.pow(user.profile.houseSize - pet.size, 2);
        distance += Math.pow(user.profile.workHours - 10, 2);
        distance += Math.pow(user.profile.numberOfFamilyMembers - 3, 2);
        distance += Math.pow(user.profile.previousPetExperience - 5, 2);
        distance += Math.pow(user.profile.financialBudget - 5, 2);
        return Math.sqrt(distance);
    }

    private double calculateCosineSimilarity(Member user, Pet pet) {
        double userMagnitude = Math.sqrt(
            Math.pow(user.profile.extroversionLevel, 2) +
            Math.pow(user.profile.dailyActivityLevel, 2) +
            Math.pow(user.profile.houseSize, 2) +
            Math.pow(user.profile.workHours, 2) +
            Math.pow(user.profile.numberOfFamilyMembers, 2) +
            Math.pow(user.profile.previousPetExperience, 2) +
            Math.pow(user.profile.financialBudget, 2)
        );

        double petMagnitude = Math.sqrt(
            Math.pow(pet.activityLevel, 2) +
            Math.pow(pet.size, 2) +
            Math.pow(pet.healthStatus, 2)
        );

        double dotProduct = (user.profile.extroversionLevel * pet.activityLevel) +
                            (user.profile.dailyActivityLevel * pet.activityLevel) +
                            (user.profile.houseSize * pet.size) +
                            (user.profile.workHours * 10) +
                            (user.profile.numberOfFamilyMembers * 3) +
                            (user.profile.previousPetExperience * 5) +
                            (user.profile.financialBudget * 5);

        return dotProduct / (userMagnitude * petMagnitude);
    }

    public double calculateMatch(Member user, Pet pet) {
        double euclideanDistance = calculateEuclideanDistance(user, pet);
        double cosineSimilarity = calculateCosineSimilarity(user, pet);

        double normalizedCosineSimilarity = (cosineSimilarity + 1) / 2;

        double weightEuclidean = 0.5;
        double weightCosine = 0.5;

        double normalizedEuclideanDistance = 1 / (1 + euclideanDistance);

        double breedSimilarity = user.profile.preferredBreed.equalsIgnoreCase(pet.breed) ? 1.0 : 0.0;
        double genderSimilarity = user.profile.gender.equalsIgnoreCase(pet.gender) ? 1.0 : 0.0;

        double breedScore = breedSimilarity * BREED_WEIGHT;
        double genderScore = genderSimilarity * GENDER_WEIGHT;

        return (normalizedEuclideanDistance * weightEuclidean) +
               (normalizedCosineSimilarity * weightCosine) +
               breedScore + genderScore;
    }
}


