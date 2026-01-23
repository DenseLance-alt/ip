package chatbot.task;

import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

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
    public String toFormattedString() {
        return String.format(
                "%s | %s | %s",
                super.toFormattedString(),
                DateTimeParser.formatDateTime(this.from, TaskStorage.DATE_FORMAT),
                DateTimeParser.formatDateTime(this.to, TaskStorage.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(this.from, TaskList.DATE_FORMAT),
                DateTimeParser.formatDateTime(this.to, TaskList.DATE_FORMAT));
    }
}
