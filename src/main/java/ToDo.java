public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s",
                super.toString());
    }
}
