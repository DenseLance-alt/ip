package task;

public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getCategory() {
        return "D";
    }

    @Override
    public String toFormattedString() {
        return String.format(
                "%s | %s",
                super.toFormattedString(),
                this.by);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (by: %s)",
                super.toString(),
                this.by);
    }
}
