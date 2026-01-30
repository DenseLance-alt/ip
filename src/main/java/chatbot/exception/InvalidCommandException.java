package chatbot.exception;

/**
 * Exception that occurs due to an invalid command provided.
 */
public class InvalidCommandException extends ChatbotException {
    @Override
    public String getMessage() {
        return super.getMessage() + " - Invalid command provided.";
    }
}
