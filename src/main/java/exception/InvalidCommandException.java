package exception;

public class InvalidCommandException extends ChatbotException {
    @Override
    public String getMessage() {
        return super.getMessage() + " - Invalid command provided.";
    }
}
