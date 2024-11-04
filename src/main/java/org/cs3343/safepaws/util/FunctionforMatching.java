package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Adopter;
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

    public static int sizeToNumeric(String size) {
        switch (size.toLowerCase()) {
            case "s": return 1;
            case "m": return 2;
            case "l": return 3;
            default: return 0;
        }
    }

    private double calculateEuclideanDistance(Adopter user, Pet pet) {
        double distance = 0.0;
        distance += Math.pow(user.extroversionLevel - pet.activityLevel, 2);
        distance += Math.pow(user.dailyActivityLevel - pet.activityLevel, 2);
        distance += Math.pow(user.houseSize - sizeToNumeric(pet.size), 2);
        distance += Math.pow(user.workHours - 10, 2);
        distance += Math.pow(user.numberOfFamilyMembers - 3, 2);
        distance += Math.pow(user.previousPetExperience - 5, 2);
        distance += Math.pow(user.financialBudget - 5, 2);
        return Math.sqrt(distance);
    }

    private double calculateCosineSimilarity(Adopter user, Pet pet) {
        double userMagnitude = Math.sqrt(
            Math.pow(user.extroversionLevel, 2) +
            Math.pow(user.dailyActivityLevel, 2) +
            Math.pow(user.houseSize, 2) +
            Math.pow(user.workHours, 2) +
            Math.pow(user.numberOfFamilyMembers, 2) +
            Math.pow(user.previousPetExperience, 2) +
            Math.pow(user.financialBudget, 2)
        );

        double petMagnitude = Math.sqrt(
            Math.pow(pet.activityLevel, 2) +
            Math.pow(sizeToNumeric(pet.size), 2) +
            Math.pow(pet.healthStatus, 2)
        );

        double dotProduct = (user.extroversionLevel * pet.activityLevel) +
                            (user.dailyActivityLevel * pet.activityLevel) +
                            (user.houseSize * sizeToNumeric(pet.size)) +
                            (user.workHours * 10) +
                            (user.numberOfFamilyMembers * 3) +
                            (user.previousPetExperience * 5) +
                            (user.financialBudget * 5);

        return dotProduct / (userMagnitude * petMagnitude);
    }

    public double calculateMatch(Adopter user, Pet pet) {
        double euclideanDistance = calculateEuclideanDistance(user, pet);
        double cosineSimilarity = calculateCosineSimilarity(user, pet);

        double normalizedCosineSimilarity = (cosineSimilarity + 1) / 2;

        double weightEuclidean = 0.5;
        double weightCosine = 0.5;

        double normalizedEuclideanDistance = 1 / (1 + euclideanDistance);

        double breedSimilarity = user.preferredBreed.equalsIgnoreCase(pet.breed) ? 1.0 : 0.0;
        double genderSimilarity = user.gender.equalsIgnoreCase(pet.gender) ? 1.0 : 0.0;

        double breedScore = breedSimilarity * BREED_WEIGHT;
        double genderScore = genderSimilarity * GENDER_WEIGHT;

        return (normalizedEuclideanDistance * weightEuclidean) +
               (normalizedCosineSimilarity * weightCosine) +
               breedScore + genderScore;
    }
}


