package exception;

public class MissingParameterException extends Exception {
    public MissingParameterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\tMISSING PARAMETER - %s must be included in the input.",
                super.getMessage());
    }
}
