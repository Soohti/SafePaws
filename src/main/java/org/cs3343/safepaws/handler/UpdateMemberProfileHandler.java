package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.util.DbManager;

import java.util.Map;

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
     */
    public void updateMemberProfile(final MemberProfile memberProfile)
    throws Exception {
        DbManager.getInstance().update(MemberProfile.class,
                "MEMBER_PROFILE",
                Map.of("Id", String.valueOf(memberProfile.getId())),
                Map.of("PreferredSpecies", String.valueOf(
                        memberProfile.getPreferredSpecies()),
                        "PreferredBreed", String.valueOf(
                                memberProfile.getPreferredBreed()),
                        "ExtroversionLevel", String.valueOf(
                                memberProfile.getExtroversionLevel()),
                        "DailyActivityLevel", String.valueOf(
                                memberProfile.getDailyActivityLevel()),
                        "HouseSize", String.valueOf(
                                memberProfile.getHouseSize()),
                        "WorkHours", String.valueOf(
                                memberProfile.getWorkHours()),
                        "NumberOfFamilyMembers", String.valueOf(
                                memberProfile.getNumberOfFamilyMembers()),
                        "PreviousPetExperience", String.valueOf(
                                memberProfile.getPreviousPetExperience()),
                        "FinancialBudget", String.valueOf(
                                memberProfile.getFinancialBudget()),
                        "Gender", memberProfile.getGender()
                        )
        );
    }
}
