package chatbot.task;

import java.time.LocalDateTime;

import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;

/**
 * Represents the task with deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Initializes the task with deadline.
     * @param name Name of event.
     * @param by Due date and time.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getCategory() {
        return "D";
    }

    @Override
    public String toFormattedString() {
        return String.format(
                "%s | %s",
                super.toFormattedString(),
                DateTimeParser.formatDateTime(this.by, TaskStorage.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (by: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(this.by, TaskList.DATE_FORMAT));
    }
}
