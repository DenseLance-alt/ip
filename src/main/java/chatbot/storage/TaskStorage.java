package chatbot.storage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import chatbot.exception.CorruptedFileException;
import chatbot.exception.InvalidDateTimeException;
import chatbot.exception.TaskStorageFailureException;
import chatbot.parser.DateTimeParser;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

/**
 * Stores and loads tasks externally.
 */
public class TaskStorage {
    public static final String DATE_FORMAT = "d/M/uuuu H:mm";

    public static final String STORAGE_ENTRY_DELIMITER = "\\|";
    public static final String STORAGE_ENTRY_SEPARATOR = " " + STORAGE_ENTRY_DELIMITER + " ";
    public static final String TASK_STATUS_ICON = "X";

    private static final Path FILE_PATH = Paths.get("./temp/tasks.txt");

    /**
     * Loads task from file.
     * @return List of tasks.
     */
    public static TaskList loadTasks() throws TaskStorageFailureException {
        try {
            createFileIfNotExists();
            ArrayList<Task> tasks = getTaskListFromFile();
            return new TaskList(tasks);
        } catch (IOException e) {
            throw new TaskStorageFailureException(TaskStorageFailureException.READ_FILE_FAILURE_WARNING);
        } catch (CorruptedFileException e) {
            createFile();
            throw new TaskStorageFailureException(TaskStorageFailureException.CORRUPTED_FILE_FAILURE_WARNING);
        }
    }

    /**
     * Saves tasks to file.
     * @param tasks List of tasks to save.
     */
    public static void saveTasks(TaskList tasks) throws TaskStorageFailureException {
        try {
            createFileIfNotExists();
            FileWriter f = openFileWriter();
            writeTasksToFile(tasks, f);
            closeFileWriter(f);
        } catch (IOException e) {
            throw new TaskStorageFailureException(TaskStorageFailureException.SAVE_FILE_FAILURE_WARNING);
        }
    }

    private static LocalDateTime convertToDateTime(String string) throws CorruptedFileException {
        try {
            return DateTimeParser.parseDateTime(string, DATE_FORMAT);
        } catch (InvalidDateTimeException e) {
            throw new CorruptedFileException();
        }
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

    private static void createFile() throws TaskStorageFailureException {
        try {
            FileWriter f = openFileWriter();
            closeFileWriter(f);
        } catch (IOException e) {
            throw new TaskStorageFailureException(TaskStorageFailureException.READ_FILE_FAILURE_WARNING);
        }
    }

    private static void createDirectory() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
    }

    private static boolean doesFileExist() {
        return Files.exists(FILE_PATH);
    }

    private static void createFileIfNotExists() throws IOException, TaskStorageFailureException {
        if (doesFileExist()) {
            return;
        }
        createDirectory();
        createFile();
    }

    private static void writeTasksToFile(TaskList tasks, FileWriter f) throws IOException {
        String entriesToStore = tasks.toStream()
                .map(task -> task.toStorageEntryString() + System.lineSeparator())
                .reduce("", String::concat);
        f.write(entriesToStore);
    }

    private static ArrayList<Task> getTaskListFromFile() throws CorruptedFileException, TaskStorageFailureException {
        try {
            Scanner reader = getScanner();
            return readFile(reader);
        } catch (FileNotFoundException e) {
            throw new TaskStorageFailureException(TaskStorageFailureException.READ_FILE_FAILURE_WARNING);
        }
    }

    private static ArrayList<Task> readFile(Scanner reader) throws CorruptedFileException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (reader.hasNext()) {
            String taskStorageEntryString = reader.nextLine();
            Task task = getTaskFromStorageEntryString(taskStorageEntryString);
            tasks.add(task);
        }
        return tasks;
    }

    private static Task createTaskByCategory(String taskCategory, String... taskParameters)
            throws CorruptedFileException {
        try {
            return switch (taskCategory) {
            case "T" -> new ToDo(taskParameters[0]);
            case "D" -> new Deadline(taskParameters[0],
                    convertToDateTime(taskParameters[1]));
            case "E" -> new Event(taskParameters[0],
                    convertToDateTime(taskParameters[1]),
                    convertToDateTime(taskParameters[2]));
            default -> throw new CorruptedFileException();
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CorruptedFileException();
        }
    }

    private static boolean isStorageEntryCorrupted(String[] segments) {
        return segments.length < 3 || segments.length > 5;
    }

    private static Task getTaskFromStorageEntryString(String taskStorageEntryString)
            throws CorruptedFileException {
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
