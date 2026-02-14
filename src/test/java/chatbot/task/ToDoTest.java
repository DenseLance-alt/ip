package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private final ToDo result = new ToDo("task");

    @Test
    void testGetCategory() {
        assertEquals("T", result.getCategory());
    }

    @Test
    void testToStorageEntryString() {
        assertEquals("T |   | task", result.toStorageEntryString());
    }

    @Test
    void testMarkTask() {
        result.markTask();
        assertEquals("[T][X] task", result.toString());
    }

    @Test
    void testUnmarkTask() {
        result.markTask();
        result.unmarkTask();
        assertEquals("[T][ ] task", result.toString());
    }
}
