package chatbot.exception;

public class InvalidDateTimeException extends ChatbotException {
    @Override
    public String getMessage() {
        return super.getMessage() + " - Start datetime is later than end datetime.";
    }
}
