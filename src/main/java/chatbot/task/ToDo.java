package chatbot.task;

/**
 * Represents the task to do.
 */
public class ToDo extends Task {
    /**
     * Initializes the task to do.
     * @param name Name of to-do task.
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "T";
    }
}
