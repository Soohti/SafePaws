package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;

/**
 * Represents a member account in the SafePaws system.
 * <p>
 * The Member class inherits from the Account class and includes additional
 * functionality specific to member users, such as their profile.
 * </p>
 */
public class Member extends Account {

    @OneToOne(columnName = "Id", tableName = "MEMBER_PROFILE")
    private MemberProfile profile;

    /**
     * Constructs a new Member account with the given parameters.
     *
     * @param newId       the unique identifier of the member account
     * @param newUsername the username for the member account
     * @param newPassword the password for the member account
     * @param newRole     the role of the account, typically "member"
     * @param newProfile  the profile associated with the member
     */
    public Member(final int newId, final String newUsername, final String newPassword, final String newRole, final MemberProfile newProfile) {
        super(newId, newUsername, newPassword, newRole);
        this.profile = newProfile;
    }

    /**
     * Gets the profile of the member.
     *
     * @return the member's profile
     */
    public MemberProfile getProfile() {
        return profile;
    }

    /**
     * Sets the profile of the member.
     *
     * @param newProfile the new profile to set for the member
     */
    public void setProfile(final MemberProfile newProfile) {
        this.profile = newProfile;
    }
}
