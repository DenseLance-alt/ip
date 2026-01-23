package task;

import parser.DateTimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

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
                DateTimeParser.formatDateTime(this.by, TaskManager.FILE_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return String.format(
                "%s (by: %s)",
                super.toString(),
                DateTimeParser.formatDateTime(this.by, TaskManager.PRINT_DATE_FORMAT));
    }
}
