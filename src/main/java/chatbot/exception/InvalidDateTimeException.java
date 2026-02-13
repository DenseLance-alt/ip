package chatbot.exception;

/**
 * Exception that occurs due to an invalid datetime range provided.
 */
public class InvalidDateTimeException extends Exception {
    public InvalidDateTimeException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("\t%s - Datetime provided is invalid.",
                ExceptionCategory.INVALID_PARAMETER);
    }
}
