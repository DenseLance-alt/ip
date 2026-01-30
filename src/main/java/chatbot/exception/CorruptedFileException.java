package chatbot.exception;

/**
 * Exception that occurs due to a corrupted save file.
 */
public class CorruptedFileException extends Exception {
    public CorruptedFileException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tCORRUPTED FILE - File is not in correct format.";
    }
}
