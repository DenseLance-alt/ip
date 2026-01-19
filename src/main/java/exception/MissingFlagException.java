package exception;

public class MissingFlagException extends Exception {
    public MissingFlagException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\tMISSING FLAG - '%s' and its positional argument must be \n\tincluded in the input.",
                super.getMessage());
    }
}
