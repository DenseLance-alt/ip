package chatbot.task;

import java.time.LocalDateTime;

import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;

/**
 * Represents the event.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes the event.
     * @param name Name of event.
     * @param from Starting datetime.
     * @param to Ending datetime.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getCategory() {
        return "E";
    }

    @Override
    public LocalDateTime getDateTime() {
        return from;
    }

    @Override
    public String toStorageEntryString() {
        return String.format(
                "%s | %s | %s",
                super.toStorageEntryString(),
                DateTimeParser.formatDateTime(from, TaskStorage.DATE_FORMAT),
                DateTimeParser.formatDateTime(to, TaskStorage.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(from, TaskList.DATE_FORMAT),
                DateTimeParser.formatDateTime(to, TaskList.DATE_FORMAT));
    }
}
