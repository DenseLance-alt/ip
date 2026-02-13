package chatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import chatbot.exception.InvalidDateTimeException;

public class DateTimeParserTest {
    @Test
    void testParseDateTime() {
        String input = "2026-01-01 23:59:59";
        String pattern = "uuuu-MM-dd HH:mm:ss";

        try {
            LocalDateTime result = DateTimeParser.parseDateTime(input, pattern);

            assertEquals(2026, result.getYear());
            assertEquals(1, result.getMonthValue());
            assertEquals(1, result.getDayOfMonth());
            assertEquals(23, result.getHour());
            assertEquals(59, result.getMinute());
            assertEquals(59, result.getSecond());
        } catch (InvalidDateTimeException e) {
            throw new RuntimeException();
        }


    }

    @Test
    void testParseDateTimeInvalidFormat() {
        String input = "01-01-2026 23:59";
        String pattern = "uuuu-MM-dd HH:mm:ss";

        assertThrows(InvalidDateTimeException.class, () -> DateTimeParser.parseDateTime(input, pattern));
    }

    @Test
    void testFormatDateTime() {
        LocalDateTime dt = LocalDateTime.of(2026, 1, 1, 23, 59, 59);
        String pattern = "uuuu-MM-dd HH:mm:ss";

        String result = DateTimeParser.formatDateTime(dt, pattern);

        assertEquals("2026-01-01 23:59:59", result);
    }
}
