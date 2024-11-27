package org.cs3343.safepaws.entity;

import org.cs3343.safepaws.util.OneToOne;

/**
 * Represents a member in the SafePaws system.
 */
public class Member extends Account {

    /**
     * The profile of the member.
     */
    @OneToOne(columnName =  "Id", tableName = "MEMBER_PROFILE")
    private MemberProfile profile;


    /**
     * Constructs a new Member with the specified username, password, and role.
     * @param id the id of the member
     * @param username the username of the member
     * @param password the password of the member
     * @param role     the role of the member
     * @param pProfile the profile of the member
     */
    public Member(final int id, final String username, final String password,
                  final String role, final MemberProfile pProfile) {
        super(id, username, password, role);
        this.profile = pProfile;
    }

    /**
     * Gets the profile of the member.
     *
     * @return the profile of the member
     */
    public MemberProfile getProfile() {
        return profile;
    }
}
