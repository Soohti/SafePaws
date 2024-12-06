package exception;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.AddPet;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAddPet {
    @Test
    public void testAddPetV1() throws Exception {
        String inputs = "Pet\nDog\nDog\n4\n30\nm\n5\n5\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        AddPet addPet = new AddPet(referrer);
        UI nextUI = addPet.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Add one pet to database", addPet.getName());
    }
}
