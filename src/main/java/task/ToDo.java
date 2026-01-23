package task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "T";
    }
}
