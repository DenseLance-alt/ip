package task;

public abstract class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void markTask() {
        this.isCompleted = true;
    }

    public void unmarkTask() {
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return this.isCompleted ? "X" : " "; // mark completed tasks with "X"
    }

    public abstract String getCategory();

    public String toFormattedString() {
        return String.format(
                "%s | %s | %s",
                this.getCategory(),
                this.getStatusIcon(),
                this.name);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s",
                this.getCategory(),
                this.getStatusIcon(),
                this.name);
    }
}
