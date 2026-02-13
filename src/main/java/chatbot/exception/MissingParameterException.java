package chatbot.exception;

/**
 * Exception that occurs due to a missing task description in the command.
 */
public class MissingParameterException extends Exception {
    public MissingParameterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\t%s - %s must be included in the input.",
                ExceptionCategory.MISSING_PARAMETER,
                super.getMessage());
    }
}
