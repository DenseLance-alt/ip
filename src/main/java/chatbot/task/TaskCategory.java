package chatbot.task;

/**
 * Set of task categories.
 */
public enum TaskCategory {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String category;

    TaskCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
