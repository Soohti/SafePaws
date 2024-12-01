package org.cs3343.safepaws.util;

public final class TableSchema {

    private TableSchema() {
    }

    public enum Name {
        /**
         * The pet table.
         */
        PET,
        /**
         * The animal location table.
         */
        ANIMAL_LOCATION,
        /**
         * The application table.
         */
        APPLICATION,
        /**
         * The account table.
         */
        ACCOUNT,
        /**
         * The shelter location table.
         */
        SHELTER_LOCATION,
        /**
         * The member profile table.
         */
        MEMBER_PROFILE,
    }

    public enum Column {
        // PET
        /**
         * The ID column.
         */
        Id,
        /**
         * The name column.
         */
        Name,
        /**
         * The species column.
         */
        Species,
        /**
         * The breed column.
         */
        Breed,
        /**
         * The age column.
         */
        Age,
        /**
         * The weight column.
         */
        Weight,
        /**
         * The gender column.
         */
        Gender,
        /**
         * The activity level column.
         */
        ActivityLevel,
        /**
         * The health status column.
         */
        HealthStatus,
        /**
         * The location column.
         */
        State,
        // ANIMAL_LOCATION
        /**
         * The XValue column.
         */
        XValue,
        /**
         * The YValue column.
         */
        YValue,
        /**
         * The Verified column.
         */
        Verified,
        // APPLICATION
//        Id,
        /**
         * The member ID column.
         */
        MId,
        /**
         * The pet ID column.
         */
        PId,
//        State,

        // ACCOUNT
//        Id,
        /**
         * The username column.
         */
        Username,
        /**
         * The password column.
         */
        Password,
        /**
         * The role column.
         */
        Role,

        // SHELTER_LOCATION
//        Id,
//        XValue,
//        YValue,

        // MEMBER_PROFILE
//        Id,
        /**
         * The member ID column.
         */
        PreferredSpecies,
        /**
         * The preferred breed column.
         */
        PreferredBreed,
        /**
         * The extroversion level column.
         */
        ExtroversionLevel,
        /**
         * The daily activity level column.
         */
        DailyActivityLevel,
        /**
         * The house size column.
         */
        HouseSize,
        /**
         * The work hours column.
         */
        WorkHours,
        /**
         * The number of family members column.
         */
        NumberOfFamilyMembers,
        /**
         * The previous pet experience column.
         */
        PreviousPetExperience,
        /**
         * The financial budget column.
         */
        FinancialBudget,
//        Gender,
    }
}
