package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;

import java.util.List;
import java.util.Map;

/**
 * Singleton handler for checking member-related information.
 */
public final class CheckMemberHandler {

    /**
     * Public constructor for instantiation.
     */
    public CheckMemberHandler() {
    }


    /**
     * Inserts a new application record into the database.
     *
     * @param member the member applying
     * @param pet    the pet being applied for
     * @param state  the state of the application
     * @throws Exception if an error occurs during the database operation
     */
    public void insertApplication(final Member member,
                                  final Pet pet,
                                  final Application.State state)
            throws Exception {
        DbManager.getInstance().insertWithAutoValue(
                Map.of("MId", String.valueOf(member.getId()),
                        "PId", String.valueOf(pet.getId()),
                        "State", String.valueOf(state.ordinal())
                ),
                "APPLICATION");
        System.out.println("Application inserted successfully");
    }

    /**
     * Finds applications by member ID.
     *
     * @param mid the member ID
     * @return a list of applications for the specified member, or null
     * if an error occurs
     */
    public List<Application> findApplicationByMid(final int mid) {
        try {
            return DbManager.getInstance()
                    .readWithCondition(Application.class,
                            "APPLICATION",
                            Map.of("MId", String.valueOf(mid)));
        } catch (Exception ex) {
            System.out.println("Error during finding all applications: "
                    + ex.getMessage());
        }
        return null;
    }
}
