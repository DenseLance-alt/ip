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
     */
    public void clearList() {
        taskList.clear();
        System.out.println("\tI have cleared your entire task list!");
    }

    /**
     * Displays the list of tasks.
     */
    public void listTasks() {
        if (hasTasks()) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("\t%d.%s", i + 1, taskList.get(i)));
            }
        } else {
            System.out.println("\tYou have no tasks!");
        }
    }

    /**
     * Displays the list of tasks that contain the keyword.
     * @param keyword Keyword to search for.
     */
    public void findTasks(String keyword) {
        if (hasTasks()) {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.getName().contains(keyword)) {
                    System.out.println(String.format("\t%d.%s", i + 1, task));
                }
            }
        } else {
            System.out.println("\tThere are no matching tasks!");
        }
    }

    /**
     * Marks task as complete.
     * @param taskNumber Task to mark.
     */
    public void markTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markTask();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
    }

    /**
     * Marks task as incomplete.
     * @param taskNumber Task to unmark.
     */
    public void unmarkTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmarkTask();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
    }

    /**
     * Removes task from task list.
     * @param taskNumber Task to remove.
     */
    public void removeTask(int taskNumber) {
        Task task = taskList.remove(taskNumber - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", countTasks()));
    }

    /**
     * Adds task to task list.
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", countTasks()));
    }
}
