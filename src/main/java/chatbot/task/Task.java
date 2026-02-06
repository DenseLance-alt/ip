package chatbot.task;

import java.time.LocalDateTime;

/**
 * Represents the general task.
 */
public abstract class Task implements Comparable<Task> {
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
     * Returns date and time when task is due or task starts.
     * Returns null if task does not have datetime element.
     * @return Datetime of task.
     */
    public LocalDateTime getDateTime() {
        return null;
    }

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

    /**
     * Compares task by datetime first, followed by the task description.
     * @param otherTask The object to be compared.
     * @return Comparison of the two tasks.
     */
    @Override
    public int compareTo(Task otherTask) {
        // Main: Datetime order
        int dateComparison = compareDates(getDateTime(), otherTask.getDateTime());
        if (dateComparison != 0) {
            return dateComparison;
        }

        // Tiebreaker: Lexicographical order
        return name.compareTo(otherTask.name);
    }

    /**
     * Compares datetime.
     * Returns 0 if both datetime are equal or null.
     * Returns 1 if dt1 is null or dt2 happens earlier.
     * Returns -1 if dt2 is null or dt1 happens earlier.
     * @param dt1 Datetime of current task.
     * @param dt2 Datetime of another task.
     * @return Comparison of the two datetime.
     */
    private int compareDates(LocalDateTime dt1, LocalDateTime dt2) {
        if (dt1 == null && dt2 == null) {
            return 0;
        } else if (dt1 == null) {
            return 1;
        } else if (dt2 == null) {
            return -1;
        }
        return dt1.compareTo(dt2);
    }
}
