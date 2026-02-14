package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskCategoryTest {
    @Test
    void testToString() {
        assertEquals("T", TaskCategory.TODO.toString());
        assertEquals("D", TaskCategory.DEADLINE.toString());
        assertEquals("E", TaskCategory.EVENT.toString());
    }

}
