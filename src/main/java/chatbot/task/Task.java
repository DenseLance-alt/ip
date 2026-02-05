package chatbot.task;

/**
 * Represents the general task.
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Initializes task.
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        isCompleted = false;
    }

    /**
     * Marks task as complete.
     */
    public void markTask() {
        isCompleted = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmarkTask() {
        isCompleted = false;
    }

    /**
     * Returns name of task.
     * @return Name of task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns completion status.
     * @return Completion status.
     */
    public String getStatusIcon() {
        return isCompleted ? "X" : " "; // mark completed tasks with "X"
    }

    /**
     * Returns category.
     * @return Category for Task.
     */
    public abstract String getCategory();

    /**
     * Returns string to be saved to file.
     * @return String to be saved to file.
     */
    public String toStorageEntryString() {
        return String.format(
                "%s | %s | %s",
                getCategory(),
                getStatusIcon(),
                name);
    }

    /**
     * Returns string to be printed.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s",
                getCategory(),
                getStatusIcon(),
                name);
    }
}
