package chatbot.storage;

import java.util.ArrayList;

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

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    protected TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Converts task into a formatted string for printing.
     * @param taskList List of tasks to convert.
     * @return Formatted string containing list of tasks
     */
    private static String formatTaskListAsString(ArrayList<Task> taskList) {
        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            formattedString.append(formatTaskAsString(taskList.get(i), i));
        }
        return formattedString.toString();
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
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String lowerCaseTaskName = task.getName().toLowerCase();
            if (lowerCaseTaskName.contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    public boolean hasTasks() {
        return !taskList.isEmpty();
    }

    public int countTasks() {
        return taskList.size();
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Clears the task list.
     * @return Notification to user about outcome.
     */
    public String clearList() {
        taskList.clear();
        return CLEAR_LIST_MESSAGE;
    }

    /**
     * Displays the list of tasks.
     * @return Notification to user about outcome.
     */
    public String listTasks() {
        return hasTasks()
                ? LIST_TASK_PREFIX_MESSAGE + formatTaskListAsString(taskList)
                : NO_TASK_MESSAGE;
    }

    /**
     * Displays the list of tasks that contain the keyword.
     * @param keyword Keyword to search for.
     * @return Notification to user about outcome.
     */
    public String findTasks(String keyword) {
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
        Task task = taskList.remove(taskNumber - 1);
        return String.format(REMOVE_TASK_FORMATTED_MESSAGE, task, countTasks());
    }

    /**
     * Adds task to task list.
     * @param task Task to add.
     * @return Notification to user about outcome.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return String.format(ADD_TASK_FORMATTED_MESSAGE, task, countTasks());
    }
}
