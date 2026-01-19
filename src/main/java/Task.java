public class Task {
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

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.getStatusIcon(),
                this.name);
    }
}
