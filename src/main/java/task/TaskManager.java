package task;

import exception.CorruptedFileException;
import parser.DateTimeParser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> taskList;
    private static final Path FILE_PATH = Paths.get("./temp/tasks.txt");
    public static final String FILE_DATE_FORMAT = "d/M/yyyy H:mm";
    public static final String PRINT_DATE_FORMAT = "yyyy-MM-dd ha";

    public TaskManager() {
        this.taskList = new ArrayList<>();
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.createDirectories(FILE_PATH.getParent()); // create new directory
                this.clearFile(); // create new file
            } else {
                this.readFile();
            }
        } catch (IOException e) {
            System.err.println("WARNING - Unable to save tasks to file.");
        } catch (CorruptedFileException e) {
            System.err.println("WARNING - Save file is corrupted. Resetting save file.");
            this.clearList(); // implicitly also clears the file
        }
    }

    private void clearList() {
        this.taskList.clear();
        this.clearFile();
    }

    public void clearList(boolean shouldPrint) {
        this.clearList();
        if (shouldPrint) {
            System.out.println("\tI have cleared your entire task list!");
        }
    }

    public void clearFile() {
        try {
            FileWriter f = new FileWriter(FILE_PATH.toFile());
            f.write("");
            f.close();
        } catch (IOException e) {
            System.err.println("WARNING - Unable to reset and create new file.");
        }
    }

    public void readFile() throws CorruptedFileException {
        try {
            Scanner reader = new Scanner(FILE_PATH.toFile());
            while (reader.hasNext()) {
                Task task = this.getTaskFromString(reader.nextLine());
                this.taskList.add(task); // add task quietly
            }
        } catch (FileNotFoundException e) {
            System.err.println("WARNING - Unable to read tasks from file.");
        } catch (DateTimeException e) {
            throw new CorruptedFileException();
        }
    }

    private Task getTaskFromString(String taskFormattedString) throws CorruptedFileException {
        String[] segments = taskFormattedString.split(" \\| ");
        if (segments.length > 2) {
            Task task = null;
            if (segments[0].equals("T") && segments.length == 3) {
                task = new ToDo(segments[2]);
            } else if (segments[0].equals("D") && segments.length == 4) {
                task = new Deadline(segments[2],
                        DateTimeParser.parseDateTime(segments[3], FILE_DATE_FORMAT));
            } else if (segments[0].equals("E") && segments.length == 5) {
                task = new Event(segments[2],
                        DateTimeParser.parseDateTime(segments[3], FILE_DATE_FORMAT),
                        DateTimeParser.parseDateTime(segments[4], FILE_DATE_FORMAT));
            }

            if (task != null) {
                if (segments[1].equals("X")) {
                    task.markTask();
                }
                return task;
            }
        }
        throw new CorruptedFileException();
    }

    public void updateFile() {
        try {
            FileWriter f = new FileWriter(FILE_PATH.toFile());
            for (int i = 0; i < this.taskList.size(); i++) {
                f.write(this.taskList.get(i).toFormattedString() + System.lineSeparator());
            }
            f.close();
        } catch (IOException e) {
            System.err.println("WARNING - Unable to save tasks to file.");
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
