package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Application;
import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Singleton handler for checking member-related information.
 */
public final class CheckApplicationHandler {

    /**
     * Public constructor for instantiation.
     */
    public CheckApplicationHandler() {
    }


    /**
     * Inserts a new application record into the database.
     *
     * @param member the member applying
     * @param pet    the pet being applied for
     * @param state  the state of the application
     * @throws SQLException if an error occurs during the database operation
     */
    public void insertApplication(final Member member,
                                  final Pet pet,
                                  final Application.State state)
            throws SQLException {
        DbManager.insertWithAutoValue(
                Map.of(TableSchema.Column.MId,
                        String.valueOf(member.getId()),
                        TableSchema.Column.PId,
                        String.valueOf(pet.getId()),
                        TableSchema.Column.State,
                        String.valueOf(state.ordinal())
                ),
                TableSchema.Name.APPLICATION);
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
            return DbManager
                    .readWithCondition(Application.class,
                            TableSchema.Name.APPLICATION,
                            Map.of(TableSchema.Column.MId,
                                    String.valueOf(mid)));
        } catch (SQLException ex) {
            System.out.println("Error during finding all applications: "
                    + ex.getMessage());
        }
        return null;
    }
}
