package chatbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses date and time.
 */
public class DateTimeParser {
    //@@author DenseLance-alt-reused
    //Reused from https://stackoverflow.com/a/22463063
    // with minor modifications
    /**
     * Parses date and time.
     * @param string Date and time in format provided.
     * @param pattern Format for date and time.
     * @return Date and time object.
     */
    public static LocalDateTime parseDateTime(String string, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(string, formatter);
    }
    /**
     * Formats date and time.
     * @param dt Date and time object.
     * @param pattern Format for date and time.
     * @return Date and time in the format provided.
     */
    public static String formatDateTime(LocalDateTime dt, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dt.format(formatter);
    }
    //@@author
}
