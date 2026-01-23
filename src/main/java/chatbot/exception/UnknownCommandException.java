package chatbot.exception;

public class UnknownCommandException extends ChatbotException {
    public UnknownCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " - Unknown chatbot.command provided.";
    }
}
