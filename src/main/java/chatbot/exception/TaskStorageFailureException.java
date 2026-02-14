package chatbot.exception;

/**
 * Exception that occurs due to failure when saving or loading tasks.
 */
public class TaskStorageFailureException extends Exception {
    public static final String READ_FILE_FAILURE_WARNING =
            "Unable to read tasks from file.";
    public static final String SAVE_FILE_FAILURE_WARNING =
            "Unable to save tasks to file.";
    public static final String CORRUPTED_FILE_FAILURE_WARNING =
            "Save file is corrupted. Resetting save file.";

    public TaskStorageFailureException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("\t%s - %s", ExceptionCategory.TASK_STORAGE_FAILURE, super.getMessage());
    }
}
