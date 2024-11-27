package org.cs3343.safepaws.entity;

import java.util.Objects;

/**
 * Represents a member in the SafePaws system.
 */
public class Member extends Account {

    /**
     * The profile of the member.
     */
    private MemberProfile profile;

    /**
     * Constructs a new Member with the specified username, password, and role.
     *
     * @param username the username of the member
     * @param password the password of the member
     * @param role     the role of the member
     */
    public Member(final String username, final String password,
                  final String role) {
        super(username, password, role);
    }

    /**
     * Constructs a new Member with default values.
     */
    public Member() {
        super();
    }


    /**
     * Constructs a new Member by copying the specified Member.
     *
     * @param other the Member to copy
     */
    public Member(final Member other) {
        super(other.getUsername(), other.getPassword(), other.getRole());
        this.profile = other.profile;
        this.setId(other.getId());
    }


    /**
     * Sets the profile of the member.
     *
     * @param memberProfile the profile to set
     */
    public void setProfile(final MemberProfile memberProfile) {
        this.profile = new MemberProfile(memberProfile);
    }

    /**
     * Sets the profile of the member with the specified attributes.
     *
     * @param preferredSpecies  the preferred species of the member
     * @param preferredBreed    the preferred breed of the member
     * @param gender            the gender of the member
     * @param numericAttributes the numeric attributes of the member
     */
    public void setProfile(final String preferredSpecies,
                           final String preferredBreed, final String gender,
                           final int[] numericAttributes) {
        this.profile = new MemberProfile(preferredSpecies,
                preferredBreed, gender, numericAttributes.clone());
    }

    /**
     * Gets the ID of the member.
     *
     * @return the ID of the member
     */
    public int getId() {
        return super.getId();
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;
        Member otherAccount = (Member) obj;
        if()
        return username.equals(otherAccount.username) 
                && password.equals(otherAccount.password)
                && role.equals(otherAccount.role)
                && id == otherAccount.id;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, id);
    }


}
