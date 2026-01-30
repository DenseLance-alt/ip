package chatbot.exception;

/**
 * Exception that occurs due to an unknown command provided.
 */
public class UnknownCommandException extends ChatbotException {
    public UnknownCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " - Unknown command provided.";
    }
}
