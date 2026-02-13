package chatbot.exception;

/**
 * Set of exception categories used by chatbot.
 */
public enum ExceptionCategory {
    CHATBOT_ERROR("CHATBOT ERROR"),
    CORRUPTED_FILE("CORRUPTED FILE"),
    MISSING_FLAG("MISSING FLAG"),
    MISSING_PARAMETER("MISSING PARAMETER"),
    INVALID_PARAMETER("INVALID PARAMETER");

    private final String category;

    ExceptionCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
