package task;

import parser.DateTimeParser;

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
                DateTimeParser.formatDateTime(this.from, TaskManager.DATE_FORMAT_FILE),
                DateTimeParser.formatDateTime(this.to, TaskManager.DATE_FORMAT_FILE));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(this.from, TaskManager.DATE_FORMAT_PRINT),
                DateTimeParser.formatDateTime(this.to, TaskManager.DATE_FORMAT_PRINT));
    }
}
