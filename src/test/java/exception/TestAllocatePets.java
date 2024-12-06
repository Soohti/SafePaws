package exception;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.AllocatePets;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAllocatePets {
    @Test
    public void testAllocatePetsV1() {
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        AllocatePets allocatePets = new AllocatePets(referrer);
        UI nextUI = allocatePets.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Allocate Idle Pets to Members", allocatePets.getName());
    }
}
