package exception;

import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.ui.admin.ReviewApplication;
import org.cs3343.safepaws.util.Session;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestReviewApplication {
    @Test
    public void testReviewApplicationV1() {
        String inputs = "20\nE\n";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        Session session = new Session(System.in, System.out);
        UI referrer = new UI("Referrer", null) {
            @Override
            protected UI execute(final Session session) {
                return null;
            }
        };
        ReviewApplication reviewApplication = new ReviewApplication(referrer);
        UI nextUI = reviewApplication.getNextUI(session);
        assertNotNull(nextUI);
        assertEquals("Review adoption applications",
                reviewApplication.getName());
    }
}
