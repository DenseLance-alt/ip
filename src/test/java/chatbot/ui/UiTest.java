package chatbot.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    void testProgramTermination() {
        Ui ui = new Ui();
        assertFalse(ui.isProgramTerminated());
        ui.sayGoodbye();
        assertTrue(ui.isProgramTerminated());
    }
}
