package org.cs3343.safepaws.entity;

/**
 * Represents a member profile with various attributes.
 * <p>
 * This class stores information about a member's personal details,
 * preferences, and pet-related attributes.
 * </p>
 */
public class MemberProfile implements Cloneable {

    /**
     * The id of the member.
     */
    private int id;

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
     * Constructs a MemberProfile with additional information (extroversion
     * level, activity level, etc.).
     *
     * @param newId the id of the member
     * @param newExtroversionLevel the extroversion level of the member
     * @param newDailyActivityLevel the daily activity level of the member
     * @param newHouseSize the house size of the member
     * @param newWorkHours the work hours of the member
     * @param newNumberOfFamilyMembers the number of family members
     * @param newPreviousPetExperience the previous pet experience of the member
     */
    public MemberProfile(
            final int newId,
            final int newExtroversionLevel,
            final int newDailyActivityLevel,
            final int newHouseSize,
            final int newWorkHours,
            final int newNumberOfFamilyMembers,
            final int newPreviousPetExperience
    ) {
        this.id = newId;
        this.extroversionLevel = newExtroversionLevel;
        this.dailyActivityLevel = newDailyActivityLevel;
        this.houseSize = newHouseSize;
        this.workHours = newWorkHours;
        this.numberOfFamilyMembers = newNumberOfFamilyMembers;
        this.previousPetExperience = newPreviousPetExperience;
        this.financialBudget = 0;
        this.preferredSpecies = ""; // default value
        this.preferredBreed = ""; // default value
        this.gender = ""; // default value
    }

    /**
     * Constructs a MemberProfile with essential information
     * (id, species, breed, gender).
     *
     * @param newId the id of the member
     * @param newPreferredSpecies the preferred species of the member
     * @param newPreferredBreed the preferred breed of the member
     * @param newGender the gender of the member
     * @param newFinancialBudget the financial budge for pet of the member
     */
    public MemberProfile(
            final int newId,
            final String newPreferredSpecies,
            final String newPreferredBreed,
            final String newGender,
            final int newFinancialBudget) {
        this.id = newId;
        this.extroversionLevel = 0; // default value
        this.dailyActivityLevel = 0; // default value
        this.houseSize = 0; // default value
        this.workHours = 0; // default value
        this.numberOfFamilyMembers = 0; // default value
        this.previousPetExperience = 0; // default value
        this.financialBudget = newFinancialBudget; // default value
        this.preferredSpecies = newPreferredSpecies;
        this.preferredBreed = newPreferredBreed;
        this.gender = newGender;
    }

    /**
     * Constructs a MemberProfile with additional information (extroversion
     * level, activity level, etc.).
     *
     */
    public MemberProfile(
    ) {
        this.id = -1;
        this.extroversionLevel = 0;
        this.dailyActivityLevel = 0;
        this.houseSize = 0;
        this.workHours = 0;
        this.numberOfFamilyMembers = 0;
        this.previousPetExperience = 0;
        this.financialBudget = 0;
        this.preferredSpecies = ""; // default value
        this.preferredBreed = ""; // default value
        this.gender = ""; // default value
    }
    /**
     * Copy constructor for creating a new {@code MemberProfile} object that is
     * a deep copy of an existing {@code MemberProfile} object.
     *
     * <p>This constructor initializes a new {@code MemberProfile} instance with
     * all the field values copied from the specified {@code profile} object.
     * Changes to the new object will not affect the original object,
     * and vice versa.
     *
     * @param profile The {@code MemberProfile} object to be copied. Must not be
     *                {@code null}.
     * @throws NullPointerException if {@code profile} is {@code null}.
     */
    public MemberProfile(final MemberProfile profile) {
        this.id = profile.id;
        this.extroversionLevel = profile.extroversionLevel;
        this.dailyActivityLevel = profile.dailyActivityLevel;
        this.houseSize = profile.houseSize;
        this.workHours = profile.workHours;
        this.numberOfFamilyMembers = profile.numberOfFamilyMembers;
        this.previousPetExperience = profile.previousPetExperience;
        this.financialBudget = profile.financialBudget;
        this.preferredSpecies = profile.preferredSpecies;
        this.preferredBreed = profile.preferredBreed;
        this.gender = profile.gender;
    }

    /**
     * Gets the id of the member.
     *
     * @return the id of the member
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the extroversion level of the member.
     *
     * @return the extroversion level of the member
     */
    public int getExtroversionLevel() {
        return extroversionLevel;
    }

    /**
     * Gets the daily activity level of the member.
     *
     * @return the daily activity level of the member
     */
    public int getDailyActivityLevel() {
        return dailyActivityLevel;
    }

    /**
     * Gets the house size of the member.
     *
     * @return the house size of the member
     */
    public int getHouseSize() {
        return houseSize;
    }

    /**
     * Gets the work hours of the member.
     *
     * @return the work hours of the member
     */
    public int getWorkHours() {
        return workHours;
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
     * Gets the previous pet experience of the member.
     *
     * @return the previous pet experience of the member
     */
    public int getPreviousPetExperience() {
        return previousPetExperience;
    }

    /**
     * Gets the financial budget of the member.
     *
     * @return the financial budget of the member
     */
    public int getFinancialBudget() {
        return financialBudget;
    }

    /**
     * Gets the preferred species of the member.
     *
     * @return the preferred species of the member
     */
    public String getPreferredSpecies() {
        return preferredSpecies;
    }

    /**
     * Gets the preferred breed of the member.
     *
     * @return the preferred breed of the member
     */
    public String getPreferredBreed() {
        return preferredBreed;
    }

    /**
     * Gets the gender of the member.
     *
     * @return the gender of the member
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the ID of the member.
     *
     * @param newId the new ID to set
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Sets the extroversion level of the member.
     *
     * @param newExtroversionLevel the new extroversion level to set
     */
    public void setExtroversionLevel(final int newExtroversionLevel) {
        this.extroversionLevel = newExtroversionLevel;
    }

    /**
     * Sets the daily activity level of the member.
     *
     * @param newDailyActivityLevel the new daily activity level to set
     */
    public void setDailyActivityLevel(final int newDailyActivityLevel) {
        this.dailyActivityLevel = newDailyActivityLevel;
    }

    /**
     * Sets the house size of the member.
     *
     * @param newHouseSize the new house size to set
     */
    public void setHouseSize(final int newHouseSize) {
        this.houseSize = newHouseSize;
    }

    /**
     * Sets the work hours of the member.
     *
     * @param newWorkHours the new work hours to set
     */
    public void setWorkHours(final int newWorkHours) {
        this.workHours = newWorkHours;
    }

    /**
     * Sets the number of family members.
     *
     * @param newNumberOfFamilyMembers the new number of family members to set
     */
    public void setNumberOfFamilyMembers(final int newNumberOfFamilyMembers) {
        this.numberOfFamilyMembers = newNumberOfFamilyMembers;
    }

    /**
     * Sets the previous pet experience of the member.
     *
     * @param newPreviousPetExperience the new previous pet experience to set
     */
    public void setPreviousPetExperience(final int newPreviousPetExperience) {
        this.previousPetExperience = newPreviousPetExperience;
    }

    /**
     * Sets the financial budget of the member.
     *
     * @param newFinancialBudget the new financial budget to set
     */
    public void setFinancialBudget(final int newFinancialBudget) {
        this.financialBudget = newFinancialBudget;
    }

    /**
     * Sets the preferred species of the member.
     *
     * @param newPreferredSpecies the new preferred species to set
     */
    public void setPreferredSpecies(final String newPreferredSpecies) {
        this.preferredSpecies = newPreferredSpecies;
    }

    /**
     * Sets the preferred breed of the member.
     *
     * @param newPreferredBreed the new preferred breed to set
     */
    public void setPreferredBreed(final String newPreferredBreed) {
        this.preferredBreed = newPreferredBreed;
    }

    /**
     * Sets the gender of the member.
     *
     * @param newGender the new gender to set
     */
    public void setGender(final String newGender) {
        this.gender = newGender;
    }

    /**
     * Creates and returns a deep copy of this {@code MemberProfile} object.
     *
     * <p>The new object will have the same field values as this object,
     * but changes to the new object will not affect this object,
     * and vice versa.
     *
     * @return A deep copy of this {@code MemberProfile} object.
     */
    @Override
    public MemberProfile clone() {
        try {
            return (MemberProfile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
