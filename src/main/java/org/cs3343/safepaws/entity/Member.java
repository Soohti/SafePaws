package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;
import org.cs3343.safepaws.util.TableSchema;

/**
 * Represents a member account in the SafePaws system.
 * <p>
 * The Member class inherits from the Account class and includes additional
 * functionality specific to member users, such as their profile.
 * </p>
 */
public class Member extends Account {
    /**
     * The member profile associated with this entity.
     * <p>
     * This field establishes a one-to-one relationship with the
     * {@code MemberProfile} entity. It maps the {@code Id} column
     * in the {@code MEMBER_PROFILE} table to this field.
     * </p>
     * <p>
     * The {@code MemberProfile} contains detailed information about
     * the member's attributes and preferences.
     * </p>
     *
     * @see MemberProfile
     */
    @OneToOne(columnName = TableSchema.Column.Id,
            tableName = TableSchema.Name.MEMBER_PROFILE)
    private MemberProfile profile;


    /**
     * Constructs a new Member account with the given parameters.
     *
     * @param newId       the unique identifier of the member account
     * @param newUsername the username for the member account
     * @param newPassword the password for the member account
     * @param newRole     the role of the account, typically "M"
     * @param newProfile  the profile associated with the member
     */
    public Member(final int newId, final String newUsername,
                  final String newPassword, final String newRole,
                  final MemberProfile newProfile) {
        super(newId, newUsername, newPassword, newRole);
        this.profile = newProfile.clone();
    }

    /**
     * Constructs a new Member account with the given parameters.
     */
    public Member() {
        super(0, "", "", "");
        this.profile = null;
    }

    /**
     * Gets the profile of the member.
     *
     * @return the member's profile
     */
    public MemberProfile getProfile() {
        return this.profile.clone();
    }

    /**
     * Sets the profile of the member.
     *
     * @param newProfile the new profile to set for the member
     */
    public void setProfile(final MemberProfile newProfile) {
        this.profile = newProfile.clone();
    }

    /**
     * Clones the Member object.
     *
     * @return a deep copy of the Member object
     */
    @Override
    public Member clone() {
        Member account = (Member) super.clone();
        account.setProfile(this.profile.clone());
        return account;
    }
}
