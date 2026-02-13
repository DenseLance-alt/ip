package chatbot.exception;

/**
 * Exception that occurs due to a missing flag or its positional argument in the command.
 */
public class MissingFlagException extends Exception {
    public MissingFlagException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format(
                "\t%s - '%s' and its positional argument must be %s\tincluded in the input.",
                ExceptionCategory.MISSING_FLAG,
                super.getMessage(),
                System.lineSeparator());
    }
}
