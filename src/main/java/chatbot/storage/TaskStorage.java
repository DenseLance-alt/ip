package chatbot.storage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import chatbot.exception.CorruptedFileException;
import chatbot.parser.DateTimeParser;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

/**
 * Stores and loads tasks externally.
 */
public class TaskStorage {
    public static final String DATE_FORMAT = "d/M/yyyy H:mm";

    private static final Path FILE_PATH = Paths.get("./temp/tasks.txt");

    // STRING PROCESSING
    private static final String STORAGE_ENTRY_SEPARATOR = " \\| ";
    private static final String TASK_STATUS_ICON = "X";

    // WARNINGS
    private static final String SAVE_FILE_FAILURE_WARNING = "WARNING - Unable to save tasks to file.";
    private static final String CORRUPTED_FILE_FAILURE_WARNING = "WARNING - Save file is corrupted. Resetting save file.";
    private static final String RESET_FILE_FAILURE_WARNING = "WARNING - Unable to reset and create new file.";
    private static final String READ_FILE_FAILURE_WARNING = "WARNING - Unable to read tasks from file.";

    /**
     * Loads task from file.
     * @return List of tasks.
     */
    public static TaskList loadTasks() {
        try {
            createFileIfNotExists();
            ArrayList<Task> taskList = getTaskListFromFile();
            return new TaskList(taskList);
        } catch (IOException e) {
            System.err.println(READ_FILE_FAILURE_WARNING);
        } catch (CorruptedFileException e) {
            System.err.println(CORRUPTED_FILE_FAILURE_WARNING);
            createFile();
        }
        return new TaskList();
    }

    /**
     * Saves tasks to file.
     * @param taskList List of tasks to save.
     */
    public static void saveTasks(TaskList taskList) {
        try {
            FileWriter f = openFileWriter();
            writeTasksToFile(taskList, f);
            closeFileWriter(f);
        } catch (IOException e) {
            System.err.println(SAVE_FILE_FAILURE_WARNING);
        }
    }

    private static LocalDateTime convertToDateTime(String string) {
        return DateTimeParser.parseDateTime(string, DATE_FORMAT);
    }

    private static FileWriter openFileWriter() throws IOException {
        return new FileWriter(FILE_PATH.toFile());
    }

    private static void closeFileWriter(FileWriter f) throws IOException {
        f.close();
    }

    private static Scanner getScanner() throws FileNotFoundException {
        return new Scanner(FILE_PATH.toFile());
    }

    private static void createFile() {
        try {
            FileWriter f = openFileWriter();
            closeFileWriter(f);
        } catch (IOException e) {
            System.err.println(RESET_FILE_FAILURE_WARNING);
        }
    }

    private static void createDirectory() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
    }

    private static boolean doesFileExist() {
        return Files.exists(FILE_PATH);
    }

    private static void createFileIfNotExists() throws IOException {
        if (!doesFileExist()) {
            createDirectory();
            createFile();
        }
    }

    private static void writeTasksToFile(TaskList taskList, FileWriter f) throws IOException {
        for (int i = 1; i <= taskList.countTasks(); i++) {
            String entryToStore = taskList.getTask(i).toStorageEntryString()
                    + System.lineSeparator();
            f.write(entryToStore);
        }
    }

    private static ArrayList<Task> getTaskListFromFile() throws CorruptedFileException {
        try {
            Scanner reader = getScanner();
            return readFile(reader);
        } catch (FileNotFoundException e) {
            System.err.println(READ_FILE_FAILURE_WARNING);
        }
        return new ArrayList<>();
    }

    private static ArrayList<Task> readFile(Scanner reader) throws CorruptedFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        while (reader.hasNext()) {
            String taskStorageEntryString = reader.nextLine();
            Task task = getTaskFromStorageEntryString(taskStorageEntryString);
            taskList.add(task);
        }
        return taskList;
    }

    private static Task createTaskByCategory(String taskCategory, String... taskParameters) throws CorruptedFileException {
        try {
            return switch (taskCategory) {
                case "T" -> new ToDo(taskParameters[0]);
                case "D" -> new Deadline(taskParameters[0],
                        convertToDateTime(taskParameters[1]));
                case "E" -> new Event(taskParameters[2],
                        convertToDateTime(taskParameters[1]),
                        DateTimeParser.parseDateTime(taskParameters[2], DATE_FORMAT));
                default -> throw new CorruptedFileException();
            };
        } catch (ArrayIndexOutOfBoundsException | DateTimeException e) {
            throw new CorruptedFileException();
        }
    }

    private static boolean isStorageEntryCorrupted(String[] segments) {
        return segments.length < 3 || segments.length > 5;
    }

    private static Task getTaskFromStorageEntryString(String taskStorageEntryString) throws CorruptedFileException {
        String[] segments = taskStorageEntryString.split(STORAGE_ENTRY_SEPARATOR);

        if (isStorageEntryCorrupted(segments)) {
            throw new CorruptedFileException();
        }

        String taskCategory = segments[0];
        String taskStatusIcon = segments[1];
        boolean isMarkedTask = taskStatusIcon.equals(TASK_STATUS_ICON);
        String[] taskParameters = Arrays.copyOfRange(segments, 2, segments.length);

        Task task = createTaskByCategory(taskCategory, taskParameters);
        if (isMarkedTask) {
            task.markTask();
        }

        return task;
    }
}
