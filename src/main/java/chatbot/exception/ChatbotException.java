package chatbot.exception;

public abstract class ChatbotException extends Exception {
    public ChatbotException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tINVALID COMMAND";
    }
}
