package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.sql.SQLException;
import java.util.Map;

/**
 * The UpdateMemberProfileHandler class provides the functionality to update
 * a member profile.
 */
public final class UpdateMemberProfileHandler {

    /**
     * Public constructor for instantiation.
     */
    public UpdateMemberProfileHandler() {
    }

    /**
     * Update the member profile by its ID.
     *
     * @param memberProfile the application ID
     * @throws SQLException if an error occurs while updating the member profile
     */
    public void updateMemberProfile(final MemberProfile memberProfile)
            throws SQLException {
        DbManager.update(MemberProfile.class,
                TableSchema.Name.MEMBER_PROFILE,
                Map.of(TableSchema.Column.Id,
                        String.valueOf(memberProfile.getId())),
                Map.of(TableSchema.Column.PreferredSpecies, String.valueOf(
                                memberProfile.getPreferredSpecies()),
                        TableSchema.Column.PreferredBreed, String.valueOf(
                                memberProfile.getPreferredBreed()),
                        TableSchema.Column.ExtroversionLevel, String.valueOf(
                                memberProfile.getExtroversionLevel()),
                        TableSchema.Column.DailyActivityLevel, String.valueOf(
                                memberProfile.getDailyActivityLevel()),
                        TableSchema.Column.HouseSize, String.valueOf(
                                memberProfile.getHouseSize()),
                        TableSchema.Column.WorkHours, String.valueOf(
                                memberProfile.getWorkHours()),
                        TableSchema.Column.NumberOfFamilyMembers,
                        String.valueOf(
                                memberProfile.getNumberOfFamilyMembers()),
                        TableSchema.Column.PreviousPetExperience,
                        String.valueOf(
                                memberProfile.getPreviousPetExperience()),
                        TableSchema.Column.FinancialBudget, String.valueOf(
                                memberProfile.getFinancialBudget()),
                        TableSchema.Column.Gender, memberProfile.getGender()
                )
        );
    }
}
