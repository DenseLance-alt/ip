package chatbot.exception;

/**
 * Exception that occurs due to chatbot being unable to process command.
 */
public abstract class ChatbotException extends Exception {
    public ChatbotException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tINVALID COMMAND";
    }
}
