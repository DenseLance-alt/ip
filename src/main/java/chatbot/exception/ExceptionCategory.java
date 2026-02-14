package chatbot.exception;

import java.util.stream.Stream;

/**
 * Set of exception categories used by chatbot.
 */
public enum ExceptionCategory {
    CHATBOT_ERROR("CHATBOT ERROR"),
    CORRUPTED_FILE("CORRUPTED FILE"),
    TASK_STORAGE_FAILURE("WARNING"),
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

    /**
     * Checks if chatbot response is an error message.
     * @param response Chatbot response.
     * @return Whether an error message was returned.
     */
    public static boolean isErrorMessage(String response) {
        assert response != null : "Response cannot be null!";
        return Stream.of(values())
                .anyMatch(category -> response.indexOf(category.toString()) == 0);
    }
}
