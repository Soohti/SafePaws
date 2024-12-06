package exception;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.SuggestShelter;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSuggestShelter {
    @Test
    public void testSuggestShelterV1() {
        String inputs = "1\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        SuggestShelter suggestShelter = new SuggestShelter(referrer);
        UI nextUI = suggestShelter.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Recommend New Shelter Locations for Stray Animals",
                suggestShelter.getName());
    }
}
