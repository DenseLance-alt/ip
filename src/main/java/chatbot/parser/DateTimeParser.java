package chatbot.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import chatbot.exception.InvalidDateTimeException;

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
    public static LocalDateTime parseDateTime(String string, String pattern) throws InvalidDateTimeException {
        assert string != null : "String representing datetime should not be null!";
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(string, formatter);
        } catch (DateTimeException e) {
            throw new InvalidDateTimeException();
        }
    }
    /**
     * Formats date and time.
     * @param dt Date and time object.
     * @param pattern Format for date and time.
     * @return Date and time in the format provided.
     */
    public static String formatDateTime(LocalDateTime dt, String pattern) {
        assert dt != null : "Datetime object should not be null!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dt.format(formatter).toLowerCase();
    }
    //@@author
}
