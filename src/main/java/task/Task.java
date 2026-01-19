package task;

public abstract class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markTask() {
        this.completed = true;
    }

    public void unmarkTask() {
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " "; // mark completed tasks with "X"
    }

    public String getCategory() {
        return "";
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
