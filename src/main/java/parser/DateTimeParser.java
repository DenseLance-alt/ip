package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author DenseLance-alt-reused
//Reused from https://stackoverflow.com/a/22463063
// with minor modifications
public class DateTimeParser {
    public static LocalDateTime parseDateTime(String string, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(string, formatter);
    }

    public static String formatDateTime(LocalDateTime dt, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dt.format(formatter);
    }
}
