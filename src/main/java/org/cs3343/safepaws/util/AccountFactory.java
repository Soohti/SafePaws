package org.cs3343.safepaws.util;

import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.entity.Admin;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.MemberProfile;

/**
 * Factory class for creating Account instances.
 */
public class AccountFactory {
    /**
     * Creates an account based on the role.
     *
     * @param id the id of the account
     * @param username the username of the account
     * @param password the password of the account
     * @param role the role of the account
     * @param memberProfile the profile of the member (if applicable)
     * @return the created account
     */
    public static Account createAccount(final int id,
                                        final String username,
                                        final String password,
                                        final String role,
                                        final MemberProfile memberProfile) {
        switch (role.toLowerCase()) {
            case "member":
                return new Member.Builder()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .setRole(role)
                        .setProfile(memberProfile)
                        .build();
            case "admin":
                return new Admin.Builder()
                        .setId(id)
                        .setUsername(username)
                        .setPassword(password)
                        .setRole(role)
                        .build();
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
