package org.cs3343.safepaws.handler;

import org.cs3343.safepaws.entity.Member;
import org.cs3343.safepaws.entity.Pet;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.TableSchema;

import java.util.List;
import java.util.Map;

import static org.cs3343.safepaws.util.TableSchema.Column.State;

public final class AllocatePetsHandler {

    /**
     * Constructs an AllocatePetsHandler instance.
     */
    public AllocatePetsHandler() {
    }

    /**
     * Gets all members from the database.
     *
     * @return all members
     */
    public List<Member> getAllMembers() {
        try {
            return DbManager.readWithCondition(Member.class,
                    TableSchema.Name.ACCOUNT,
                    Map.of(TableSchema.Column.Role, "M"));
        } catch (Exception e) {
            System.out.println("Error during reading all members");
        }
        return null;
    }

    /**
     * Gets all free pets from the database.
     *
     * @return all free pets
     */
    public List<Pet> getAllFreePets() {
        try {
            return DbManager.readWithCondition(Pet.class, TableSchema.Name.PET,
                    Map.of(State, Pet.State.Free.toString()));
        } catch (Exception e) {
            System.out.println("Error during reading all free pets");
        }
        return null;
    }
}
