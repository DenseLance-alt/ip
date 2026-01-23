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
                DateTimeParser.formatDateTime(this.from, TaskManager.FILE_DATE_FORMAT),
                DateTimeParser.formatDateTime(this.to, TaskManager.FILE_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(this.from, TaskManager.PRINT_DATE_FORMAT),
                DateTimeParser.formatDateTime(this.to, TaskManager.PRINT_DATE_FORMAT));
    }
}
