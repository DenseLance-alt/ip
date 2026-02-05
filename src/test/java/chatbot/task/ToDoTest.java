package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testGetCategory() {
        ToDo result = new ToDo("task");
        assertEquals("T", result.getCategory());
    }

    @Test
    void testToStorageEntryString() {
        ToDo result = new ToDo("task");
        assertEquals("T |   | task", result.toStorageEntryString());
    }

    @Test
    void testMarkTask() {
        ToDo result = new ToDo("task");
        result.markTask();
        assertEquals("[T][X] task", result.toString());
    }

    @Test
    void testUnmarkTask() {
        ToDo result = new ToDo("task");
        result.markTask();
        result.unmarkTask();
        assertEquals("[T][ ] task", result.toString());
    }
}
