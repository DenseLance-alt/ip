package chatbot.exception;

public class InvalidCommandException extends ChatbotException {
    @Override
    public String getMessage() {
        return super.getMessage() + " - Invalid chatbot.command provided.";
    }
}
