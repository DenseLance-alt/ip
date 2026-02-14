package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final LocalDateTime date = LocalDateTime.of(2020, 12, 31, 16, 30, 0);
    private final Deadline result = new Deadline("deadline", date);

    @Test
    void testGetCategory() {
        assertEquals("D", result.getCategory());
    }

    @Test
    void testToStorageEntryString() {
        assertEquals("D |   | deadline | 31/12/2020 16:30", result.toStorageEntryString());
    }

    @Test
    void testMarkTask() {
        result.markTask();
        assertEquals("[D][X] deadline (by: 2020-12-31 4pm)", result.toString());
    }

    @Test
    void testUnmarkTask() {
        result.markTask();
        result.unmarkTask();
        assertEquals("[D][ ] deadline (by: 2020-12-31 4pm)", result.toString());
    }
}
