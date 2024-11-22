
package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 */
public class MemberProfile {

    /**
     * The maximum extroversion level.
     */
    private static final int MAX_EXTROVERSION_LEVEL = 10;

    /**
     * The maximum daily activity level.
     */
    private static final int MAX_DAILY_ACTIVITY_LEVEL = 10;

    /**
     * The maximum house size.
     */
    private static final int MAX_HOUSE_SIZE = 1000000000;

    /**
     * The maximum work hours.
     */
    private static final int MAX_WORK_HOURS = 24;

    /**
     * The maximum number of family members.
     */
    private static final int MAX_FAMILY_MEMBERS = 100000000;

    /**
     * The maximum previous pet experience.
     */
    private static final int MAX_PREVIOUS_PET_EXPERIENCE = 100000000;

    /**
     * The maximum financial budget.
     */
    private static final int MAX_FINANCIAL_BUDGET = 100000000;

    /**
     * The maximum length of the species.
     */
    private static final int MAX_SPECIES_LENGTH = 30;

    /**
     * The maximum length of the breed.
     */
    private static final int MAX_BREED_LENGTH = 30;

    /**
     * The extroversion level of the member.
     */
    private int extroversionLevel;

    /**
     * The daily activity level of the member.
     */
    private int dailyActivityLevel;

    /**
     * The house size of the member.
     */
    private int houseSize;

    /**
     * The work hours of the member.
     */
    private int workHours;

    /**
     * The number of family members.
     */
    private int numberOfFamilyMembers;

    /**
     * The previous pet experience of the member.
     */
    private int previousPetExperience;

    /**
     * The financial budget of the member.
     */
    private int financialBudget;

    /**
     * The preferred species of the member.
     */
    private String preferredSpecies;

    /**
     * The preferred breed of the member.
     */
    private String preferredBreed;

    /**
     * The gender of the member.
     */
    private String gender;

    /**
     * Constructs a new MemberProfile with the specified attributes.
     *
     * @param species the preferred species
     * @param breed   the preferred breed
     * @param gen     the gender
     * @param numeric an array of numeric attributes
     */
    public MemberProfile(final String species, final String breed,
            final String gen, final int[] numeric) {
        this.preferredSpecies = species;
        this.preferredBreed = breed;
        this.gender = gen;
        this.extroversionLevel = numeric[0];
        this.dailyActivityLevel = numeric[1];
        this.houseSize = numeric[2];
        this.workHours = numeric[3];
        this.numberOfFamilyMembers = numeric[4];
        this.previousPetExperience = numeric[5];
        this.financialBudget = numeric[6];
    }

    /**
     * Gets the preferred species.
     *
     * @return the preferred species
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * Sets the preferred species.
     *
     * @param newPreferredSpecies the new preferred species
     */
    public void setPreferredSpecies(final
            String newPreferredSpecies) {
        this.preferredSpecies = newPreferredSpecies;
    }

    /**
     * Gets the extroversion level.
     *
     * @return the extroversion level
     */
    public int getExtroversionLevel() {
        return extroversionLevel;
    }

    /**
     * Sets the extroversion level.
     *
     * @param newExtroversionLevel the new extroversion level
     */
    public void setExtroversionLevel(final
            int newExtroversionLevel) {
        this.extroversionLevel = newExtroversionLevel;
    }

    /**
     * Gets the daily activity level.
     *
     * @return the daily activity level
     */
    public int getDailyActivityLevel() {
        return dailyActivityLevel;
    }

    /**
     * Sets the daily activity level.
     *
     * @param newDailyActivityLevel
     * the new daily activity level
     */
    public void setDailyActivityLevel(final
            int newDailyActivityLevel) {
        this.dailyActivityLevel = newDailyActivityLevel;
    }

    /**
     * Gets the house size.
     *
     * @return the house size
     */
    public int getHouseSize() {
        return houseSize;
    }

    /**
     * Sets the house size.
     *
     * @param newHouseSize the new house size
     */
    public void setHouseSize(final int newHouseSize) {
        this.houseSize = newHouseSize;
    }

    /**
     * Gets the work hours.
     *
     * @return the work hours
     */
    public int getWorkHours() {
        return workHours;
    }

    /**
     * Sets the work hours.
     *
     * @param newWorkHours the new work hours
     */
    public void setWorkHours(final int newWorkHours) {
        this.workHours = newWorkHours;
    }

    /**
     * Gets the number of family members.
     *
     * @return the number of family members
     */
    public int getNumberOfFamilyMembers() {
        return numberOfFamilyMembers;
    }

    /**
     * Sets the number of family members.
     *
     * @param newNumberOfFamilyMembers the new number of family members
     */
    public void setNumberOfFamilyMembers(final
            int newNumberOfFamilyMembers) {
        this.numberOfFamilyMembers = newNumberOfFamilyMembers;
    }

    /**
     * Gets the previous pet experience.
     *
     * @return the previous pet experience
     */
    public int getPreviousPetExperience() {
        return previousPetExperience;
    }

    /**
     * Sets the previous pet experience.
     *
     * @param newPreviousPetExperience
     * the new previous pet experience
     */
    public void setPreviousPetExperience(final
            int newPreviousPetExperience) {
        this.previousPetExperience = newPreviousPetExperience;
    }

    /**
     * Gets the financial budget.
     *
     * @return the financial budget
     */
    public int getFinancialBudget() {
        return financialBudget;
    }

    /**
     * Sets the financial budget.
     *
     * @param newFinancialBudget the new financial budget
     */
    public void setFinancialBudget(final int newFinancialBudget) {
        this.financialBudget = newFinancialBudget;
    }

    /**
     * Gets the preferred breed.
     *
     * @return the preferred breed
     */
    public String getPreferredBreed() {
        return preferredBreed;
    }

    /**
     * Sets the preferred breed.
     *
     * @param newPreferredBreed the new preferred breed
     */
    public void setPreferredBreed(final
            String newPreferredBreed) {
        this.preferredBreed = newPreferredBreed;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param newGender the new gender
     */
    public void setGender(final String newGender) {
        this.gender = newGender;
    }

    /**
     * Validates the extroversion level.
     *
     * @param extroversionLevel the extroversion level
     * @return true if valid, false otherwise
     */
    public static boolean isValidExtroversionLevel(
            final int extroversionLevel) {
        if (extroversionLevel < 0
                || extroversionLevel > MAX_EXTROVERSION_LEVEL) {
            return false;
        }
        return true;
    }

    /**
     * Validates the daily activity level.
     *
     * @param dailyActivityLevel
     * the daily activity level
     * @return true if valid, false otherwise
     */
    public static boolean isValidDailyActivityLevel(
            final int dailyActivityLevel) {
        if (dailyActivityLevel < 0
                || dailyActivityLevel > MAX_DAILY_ACTIVITY_LEVEL) {
            return false;
        }
        return true;
    }

    /**
     * Validates the house size.
     *
     * @param houseSize the house size
     * @return true if valid, false otherwise
     */
    public static boolean isValidHouseSize(
            final int houseSize) {
        if (houseSize < 0 || houseSize > MAX_HOUSE_SIZE) {
            return false;
        }
        return true;
    }

    /**
     * Validates the work hours.
     *
     * @param workHours the work hours
     * @return true if valid, false otherwise
     */
    public static boolean isValidWorkHours(
            final int workHours) {
        if (workHours < 0 || workHours > MAX_WORK_HOURS) {
            return false;
        }
        return true;
    }

    /**
     * Validates the number of family members.
     *
     * @param numberOfFamilyMembers
     * the number of family members
     * @return true if valid, false otherwise
     */
    public static boolean isValidNumberOfFamilyMembers(
            final int numberOfFamilyMembers) {
        if (numberOfFamilyMembers < 1
                || numberOfFamilyMembers > MAX_FAMILY_MEMBERS) {
            return false;
        }
        return true;
    }

    /**
     * Validates the previous pet experience.
     *
     * @param previousPetExperience
     * the previous pet experience
     * @return true if valid, false otherwise
     */
    public static boolean isValidPreviousPetExperience(
            final int previousPetExperience) {
        if (previousPetExperience < 0
                || previousPetExperience > MAX_PREVIOUS_PET_EXPERIENCE) {
            return false;
        }
        return true;
    }

    /**
     * Validates the financial budget.
     *
     * @param financialBudget the financial budget
     * @return true if valid, false otherwise
     */
    public static boolean isValidFinancialBudget(
            final int financialBudget) {
        if (financialBudget < 0
                || financialBudget > MAX_FINANCIAL_BUDGET) {
            return false;
        }
        return true;
    }

    /**
     * Validates the preferred species.
     *
     * @param preferredSpecies the preferred species
     * @return true if valid, false otherwise
     */
    public static boolean isValidPreferredSpecies(
            final String preferredSpecies) {
        if (preferredSpecies.length() > MAX_SPECIES_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * Validates the preferred breed.
     *
     * @param preferredBreed the preferred breed
     * @return true if valid, false otherwise
     */
    public static boolean isValidPreferredBreed(
            final String preferredBreed) {
        if (preferredBreed.length() > MAX_BREED_LENGTH) {
            return false;
        }
        return true;
    }

    /**
     * Validates the gender.
     *
     * @param gender the gender
     * @return true if valid, false otherwise
     */
    public static boolean isValidGender(
            final String gender) {
        if ("m".equals(gender) || "f".equals(gender)) {
            return true;
        }
        return false;
    }
}
