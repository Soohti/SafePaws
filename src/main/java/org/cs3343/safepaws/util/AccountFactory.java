package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;
import org.cs3343.safepaws.entity.Shelter;
import org.cs3343.safepaws.entity.ShelterLocation;

/**
 * Factory class for creating Account instances.
 */
public final class AccountFactory {
    private AccountFactory() {
    }

    /**
     * Creates an account based on the role.
     *
     * @param id            the id of the account
     * @param username      the username of the account
     * @param password      the password of the account
     * @param role          the role of the account
     * @param memberProfile the profile of the member (if applicable)
     * @param locationPoint the location point of the member (if applicable)
     * @return the created account
     */
    public static Account createAccount(final int id,
                                        final String username,
                                        final String password,
                                        final String role,
                                        final MemberProfile memberProfile,
                                        final ShelterLocation locationPoint) {
        return switch (role.toLowerCase()) {
            case "m" -> new Member(id, username,
                    password, role, memberProfile);
            case "a" -> new Admin(id, username,
                    password, role);
            case "s" -> new Shelter(id, username,
                    password, role, locationPoint);
            default ->
                    throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
