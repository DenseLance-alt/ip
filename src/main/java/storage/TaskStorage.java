package storage;

import exception.CorruptedFileException;
import parser.DateTimeParser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    private static final Path FILE_PATH = Paths.get("./temp/tasks.txt");
    public static final String DATE_FORMAT = "d/M/yyyy H:mm";

    private static void clearFile() {
        try {
            FileWriter f = new FileWriter(FILE_PATH.toFile());
            f.write("");
            f.close();
        } catch (IOException e) {
            System.err.println("WARNING - Unable to reset and create new file.");
        }
    }

    private static ArrayList<Task> readFile() throws CorruptedFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(FILE_PATH.toFile());
            while (reader.hasNext()) {
                Task task = getTaskFromString(reader.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("WARNING - Unable to read tasks from file.");
        } catch (DateTimeException e) {
            throw new CorruptedFileException();
        }
        return taskList;
    }

    private static Task getTaskFromString(String taskFormattedString) throws CorruptedFileException {
        String[] segments = taskFormattedString.split(" \\| ");
        if (segments.length > 2) {
            Task task = null;
            if (segments[0].equals("T") && segments.length == 3) {
                task = new ToDo(segments[2]);
            } else if (segments[0].equals("D") && segments.length == 4) {
                task = new Deadline(segments[2],
                        DateTimeParser.parseDateTime(segments[3], DATE_FORMAT));
            } else if (segments[0].equals("E") && segments.length == 5) {
                task = new Event(segments[2],
                        DateTimeParser.parseDateTime(segments[3], DATE_FORMAT),
                        DateTimeParser.parseDateTime(segments[4], DATE_FORMAT));
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

    public static TaskList loadTasks() {
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.createDirectories(FILE_PATH.getParent()); // create new directory
                clearFile(); // create new file
            } else {
                return new TaskList(readFile());
            }
        } catch (IOException e) {
            System.err.println("WARNING - Unable to save tasks to file.");
        } catch (CorruptedFileException e) {
            System.err.println("WARNING - Save file is corrupted. Resetting save file.");
            clearFile();
        }
        return new TaskList();
    }

    public static void saveTasks(TaskList taskList) {
        try {
            FileWriter f = new FileWriter(FILE_PATH.toFile());
            for (int i = 1; i <= taskList.numTasks(); i++) {
                f.write(taskList.getTask(i).toFormattedString() + System.lineSeparator());
            }
            f.close();
        } catch (IOException e) {
            System.err.println("WARNING - Unable to save tasks to file.");
        }
    }
}
