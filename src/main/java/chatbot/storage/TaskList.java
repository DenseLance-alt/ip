package chatbot.storage;

import java.util.ArrayList;

import chatbot.task.Task;

public class TaskList {
    public static final String DATE_FORMAT = "yyyy-MM-dd ha";
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    protected TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean hasTasks() {
        return !this.taskList.isEmpty();
    }

    public int numTasks() {
        return this.taskList.size();
    }

    public Task getTask(int taskNumber) {
        return this.taskList.get(taskNumber - 1);
    }

    public void clearList() {
        this.taskList.clear();
        System.out.println("\tI have cleared your entire chatbot.task list!");
    }

    public void listTasks() {
        if (this.hasTasks()) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < this.taskList.size(); i++) {
                System.out.println(String.format("\t%d.%s", i + 1, this.taskList.get(i)));
            }
        } else {
            System.out.println("\tYou have no tasks!");
        }
    }

    public void markTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.markTask();
        System.out.println("\tNice! I've marked this chatbot.task as done:");
        System.out.println("\t  " + task);
    }

    public void unmarkTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.unmarkTask();
        System.out.println("\tOK, I've marked this chatbot.task as not done yet:");
        System.out.println("\t  " + task);
    }

    public void removeTask(int taskNumber) {
        Task task = this.taskList.remove(taskNumber - 1);
        System.out.println("\tNoted. I've removed this chatbot.task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.numTasks()));
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\tGot it. I've added this chatbot.task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.numTasks()));
    }
}
