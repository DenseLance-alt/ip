public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format(
                "%s",
                super.toString());
    }
}
