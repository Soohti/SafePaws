
package org.cs3343.safepaws.entity;

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
     * @param role the role of the member
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
    public Member(Member other) {
        super(other.getUsername(), other.getPassword(), other.getRole());
        this.profile = other.profile; // Assuming MemberProfile is immutable or has its own copy constructor
    }


    /**
     * Sets the profile of the member.
     *
     * @param memberProfile the profile to set
     */
    public void setProfile(final MemberProfile memberProfile) {
        this.profile = memberProfile;
    }

    /**
     * Sets the profile of the member with the specified attributes.
     *
     * @param preferredSpecies  the preferred species of the member
     * @param preferredBreed    the preferred breed of the member
     * @param gender            the gender of the member
     * @param numericAttributes the numeric attributes of the member
     */
    public void setProfile(final String preferredSpecies, final String preferredBreed, final String gender,
            final int[] numericAttributes) {
        this.profile = new MemberProfile(preferredSpecies, preferredBreed, gender, numericAttributes.clone());
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
     * Sets the ID of the member.
     *
     * @param id the ID to set
     */
    public void setId(final int id) {
        super.setId(id);
    }


    /**
     * Gets the profile of the member.
     *
     * @return a defensive copy of the profile of the member
     */
    public MemberProfile getProfile() {
        return new MemberProfile(this.profile.getPreferredSpecies(), this.profile.getPreferredBreed(), this.profile.getGender(), this.profile.getNumericAttributes());
    }


}
