package task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class TaskManager {
    private boolean initializedFile;
    private ArrayList<Task> taskList;
    private static final Path FILEPATH = Paths.get("./temp/tasks.txt");

    public TaskManager() {
        this.initializedFile = false;
        this.taskList = new ArrayList<>();
        try {
            Files.createDirectories(FILEPATH.getParent()); // creates directory if it does not exist
            FileWriter f = new FileWriter(FILEPATH.toFile());
            f.write(""); // reset file contents if it exists
            f.close();
            this.initializedFile = true;
        } catch (IOException e) {
            System.err.println("WARNING - Unable to save tasks to file.");
        }
    }

    public void updateFile() {
        if (this.initializedFile) {
            try {
                FileWriter f = new FileWriter(FILEPATH.toFile());
                for (int i = 0; i < this.taskList.size(); i++) {
                    f.write(this.taskList.get(i).toFormattedString() + System.lineSeparator());
                }
                f.close();
            } catch (IOException e) {
                System.err.println("WARNING - Unable to save tasks to file.");
            }
        }
    }

    public boolean hasTasks() {
        return !this.taskList.isEmpty();
    }

    public int numTasks() {
        return this.taskList.size();
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
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        this.updateFile();
    }

    public void unmarkTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.unmarkTask();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        this.updateFile();
    }

    public void removeTask(int taskNumber) {
        Task task = this.taskList.remove(taskNumber - 1);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.numTasks()));
        this.updateFile();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println(String.format("\tNow you have %d tasks in the list.", this.numTasks()));
        this.updateFile();
    }
}
