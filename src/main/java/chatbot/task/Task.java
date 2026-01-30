package chatbot.task;

/**
 * Represents the general task.
 */
public abstract class Task {
    private String name;
    private boolean completed;

    /**
     * Initializes task.
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
        completed = false;
    }

    /**
     * Marks task as complete.
     */
    public void markTask() {
        completed = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmarkTask() {
        completed = false;
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
        return completed ? "X" : " "; // mark completed tasks with "X"
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
    public String toFormattedString() {
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
