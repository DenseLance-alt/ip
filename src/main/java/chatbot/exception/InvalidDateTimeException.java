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
        return String.format("\t%s - Start datetime is later than end datetime.",
                ExceptionCategory.INVALID_PARAMETER);
    }
}
