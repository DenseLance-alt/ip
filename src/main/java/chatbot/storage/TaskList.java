package chatbot.storage;

import java.util.ArrayList;

import chatbot.task.Task;

/**
 * Stores and loads tasks locally.
 */
public class TaskList {
    public static final String DATE_FORMAT = "yyyy-MM-dd ha";
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
        return "\tI have cleared your entire task list!";
    }

    /**
     * Displays the list of tasks.
     * @return Notification to user about outcome.
     */
    public String listTasks() {
        return hasTasks()
                ? "\tHere are the tasks in your list:" + formatTaskListAsString(taskList)
                : "\tYou have no tasks!";
    }

    /**
     * Displays the list of tasks that contain the keyword.
     * @param keyword Keyword to search for.
     * @return Notification to user about outcome.
     */
    public String findTasks(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        return hasTasks()
                ? "\tHere are the matching tasks in your list:"
                + formatTaskListAsString(filterTaskList(lowerCaseKeyword))
                : "\tThere are no matching tasks!";
    }

    /**
     * Marks task as complete.
     * @param taskNumber Task to mark.
     * @return Notification to user about outcome.
     */
    public String markTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markTask();
        return "\tNice! I've marked this task as done:"
                + "\n\t  "
                + task;
    }

    /**
     * Marks task as incomplete.
     * @param taskNumber Task to unmark.
     * @return Notification to user about outcome.
     */
    public String unmarkTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmarkTask();
        return "\tOK, I've marked this task as not done yet:"
                + "\n\t  "
                + task;
    }

    /**
     * Removes task from task list.
     * @param taskNumber Task to remove.
     * @return Notification to user about outcome.
     */
    public String removeTask(int taskNumber) {
        Task task = taskList.remove(taskNumber - 1);
        return "\tNoted. I've removed this task:"
                + "\n\t  "
                + task
                + String.format("\n\tNow you have %d tasks in the list.", countTasks());
    }

    /**
     * Adds task to task list.
     * @param task Task to add.
     * @return Notification to user about outcome.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return "\tGot it. I've added this task:"
                + "\n\t  "
                + task
                + String.format("\n\tNow you have %d tasks in the list.", countTasks());
    }
}
