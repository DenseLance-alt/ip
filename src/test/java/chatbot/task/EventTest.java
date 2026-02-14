package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    private final LocalDateTime startDate = LocalDateTime.of(2020, 12, 31, 16, 30, 0);
    private final LocalDateTime endDate = startDate.plusYears(1);
    private final Event result = new Event("event", startDate, endDate);

    @Test
    void testGetCategory() {
        assertEquals("E", result.getCategory());
    }

    @Test
    void testToStorageEntryString() {
        assertEquals("E |   | event | 31/12/2020 16:30 | 31/12/2021 16:30", result.toStorageEntryString());
    }

    @Test
    void testMarkTask() {
        result.markTask();
        assertEquals("[E][X] event (from: 2020-12-31 4pm to: 2021-12-31 4pm)", result.toString());
    }

    @Test
    void testUnmarkTask() {
        result.markTask();
        result.unmarkTask();
        assertEquals("[E][ ] event (from: 2020-12-31 4pm to: 2021-12-31 4pm)", result.toString());
    }
}
