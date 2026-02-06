package chatbot.storage;

import java.util.ArrayList;
import java.util.stream.Stream;

import chatbot.task.Task;

/**
 * Stores and loads tasks locally.
 */
public class TaskList {
    public static final String DATE_FORMAT = "yyyy-MM-dd ha";

    private static final String LIST_TASK_PREFIX_MESSAGE =
            "\tHere are the tasks in your list:";
    private static final String NO_TASK_MESSAGE =
            "\tYou have no tasks!";
    private static final String NO_MATCHING_TASK_MESSAGE =
            "\tThere are no matching tasks!";
    private static final String CLEAR_LIST_MESSAGE =
            "\tI have cleared your entire task list!";
    private static final String LIST_MATCHING_TASK_PREFIX_MESSAGE =
            "\tHere are the matching tasks in your list:";
    private static final String MARK_TASK_PREFIX_MESSAGE =
            "\tNice! I've marked this task as done:\n\t  ";
    private static final String UNMARK_TASK_PREFIX_MESSAGE =
            "\tOK, I've marked this task as not done yet:\n\t  ";
    private static final String REMOVE_TASK_FORMATTED_MESSAGE =
            "\tNoted. I've removed this task:\n\t  %s\n\tNow you have %d tasks in the list.";
    private static final String ADD_TASK_FORMATTED_MESSAGE =
            "\tGot it. I've added this task:\n\t  %s\n\tNow you have %d tasks in the list.";

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Converts task into a formatted string for printing.
     * @param tasks List of tasks to convert.
     * @return Formatted string containing list of tasks
     */
    private static String formatTaskListAsString(ArrayList<Task> tasks) {
        return Stream.iterate(0, i -> i + 1)
                .limit(tasks.size())
                .map(i -> formatTaskAsString(tasks.get(i), i))
                .reduce("", String::concat);
    }

    /**
     * Converts task into a formatted string for printing.
     * @param task Task to convert.
     * @param index Index of the task in the task list.
     * @return Formatted string containing a task.
     */
    private static String formatTaskAsString(Task task, int index) {
        return String.format("\n\t%d.%s", index + 1, task);
    }

    /**
     * Retrieves all task with a specific keyword.
     * @param keyword Keyword
     * @return A list of tasks that contains the keyword.
     */
    private ArrayList<Task> filterTaskList(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        toStream()
                .filter(task -> task.getName().toLowerCase().contains(keyword))
                .forEach(filteredTasks::add);
        return filteredTasks;
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public int countTasks() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public Stream<Task> toStream() {
        return tasks.stream();
    }

    /**
     * Clears the task list.
     * @return Notification to user about outcome.
     */
    public String clearList() {
        tasks.clear();
        return CLEAR_LIST_MESSAGE;
    }

    /**
     * Displays the list of tasks.
     * @return Notification to user about outcome.
     */
    public String listTasks() {
        return hasTasks()
                ? LIST_TASK_PREFIX_MESSAGE + formatTaskListAsString(tasks)
                : NO_TASK_MESSAGE;
    }

    /**
     * Displays the list of tasks that contain the keyword.
     * @param keyword Keyword to search for.
     * @return Notification to user about outcome.
     */
    public String findTasks(String keyword) {
        assert keyword != null : "Keyword should not be NULL!";
        String lowerCaseKeyword = keyword.toLowerCase();
        ArrayList<Task> filteredTaskList = filterTaskList(lowerCaseKeyword);
        return filteredTaskList.isEmpty()
                ? NO_MATCHING_TASK_MESSAGE
                : LIST_MATCHING_TASK_PREFIX_MESSAGE
                + formatTaskListAsString(filteredTaskList);
    }

    /**
     * Marks task as complete.
     * @param taskNumber Task to mark.
     * @return Notification to user about outcome.
     */
    public String markTask(int taskNumber) {
        Task task = getTask(taskNumber);
        task.markTask();
        return MARK_TASK_PREFIX_MESSAGE + task;
    }

    /**
     * Marks task as incomplete.
     * @param taskNumber Task to unmark.
     * @return Notification to user about outcome.
     */
    public String unmarkTask(int taskNumber) {
        Task task = getTask(taskNumber);
        task.unmarkTask();
        return UNMARK_TASK_PREFIX_MESSAGE + task;
    }

    /**
     * Removes task from task list.
     * @param taskNumber Task to remove.
     * @return Notification to user about outcome.
     */
    public String removeTask(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        return String.format(REMOVE_TASK_FORMATTED_MESSAGE, task, countTasks());
    }

    /**
     * Adds task to task list.
     * @param task Task to add.
     * @return Notification to user about outcome.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return String.format(ADD_TASK_FORMATTED_MESSAGE, task, countTasks());
    }
}
