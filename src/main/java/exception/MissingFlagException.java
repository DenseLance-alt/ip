package exception;

public class MissingFlagException extends Exception {
    public MissingFlagException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\tMISSING FLAG - '%s' must be included in input.",
                super.getMessage());
    }
}
