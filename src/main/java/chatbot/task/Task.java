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
        this.completed = false;
    }

    /**
     * Marks task as complete.
     */
    public void markTask() {
        this.completed = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmarkTask() {
        this.completed = false;
    }

    /**
     * Returns completion status.
     * @return Completion status.
     */
    public String getStatusIcon() {
        return this.completed ? "X" : " "; // mark completed tasks with "X"
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
                this.getCategory(),
                this.getStatusIcon(),
                this.name);
    }

    /**
     * Returns string to be printed.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s",
                this.getCategory(),
                this.getStatusIcon(),
                this.name);
    }
}
