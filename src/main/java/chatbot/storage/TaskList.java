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
        StringBuilder response = new StringBuilder();
        if (hasTasks()) {
            response.append("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                response.append(String.format("\n\t%d.%s", i + 1, taskList.get(i)));
            }
        } else {
            response.append("\tYou have no tasks!");
        }
        return response.toString();
    }

    /**
     * Displays the list of tasks that contain the keyword.
     * @param keyword Keyword to search for.
     * @return Notification to user about outcome.
     */
    public String findTasks(String keyword) {
        StringBuilder response = new StringBuilder();
        if (hasTasks()) {
            response.append("\tHere are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.getName().contains(keyword)) {
                    response.append(String.format("\n\t%d.%s", i + 1, task));
                }
            }
        } else {
            response.append("\tThere are no matching tasks!");
        }
        return response.toString();
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
                +"\n\t  "
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
