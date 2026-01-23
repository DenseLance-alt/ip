import java.util.Scanner;
import java.time.DateTimeException;

import command.Command;
import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import exception.InvalidCommandException;
import exception.UnknownCommandException;
import parser.DateTimeParser;
import task.Deadline;
import task.Event;
import task.TaskManager;
import task.ToDo;
import ui.Ui;

public class YoshikageKira {
    private Ui ui;
    private TaskManager taskManager;

    public YoshikageKira() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    public void run() {
        System.out.println(Ui.SEPARATOR);
        this.ui.sayHello();
        System.out.println(Ui.SEPARATOR);

        boolean isDone = false;
        while (!isDone) {
            String input = this.ui.getUserInput();
            String[] segments = input.split(" ", 2);
            Command command = Command.convertToCommand(segments[0]);

            // Respond to Commands
            System.out.println(Ui.SEPARATOR);
            try {
                if (input.contains("|")) {
                    throw new InvalidCommandException(); // delimiter is reserved for saving to file
                }
                switch (command) {
                case BYE -> {
                    isDone = true;
                    this.ui.sayGoodbye();
                }
                case LIST -> taskManager.listTasks();
                case MARK -> {
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingParameterException("Task ID");
                    }
                    int taskNumber = Integer.parseInt(segments[1]);
                    taskManager.markTask(taskNumber);
                }
                case UNMARK -> {
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingParameterException("Task ID");
                    }
                    int taskNumber = Integer.parseInt(segments[1]);
                    taskManager.unmarkTask(taskNumber);
                }
                case TODO -> {
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingParameterException("Task name");
                    }
                    ToDo task = new ToDo(segments[1]);
                    taskManager.addTask(task);
                }
                case DEADLINE -> {
                    if (segments.length < 2 ||
                            "".equals(segments[1]) ||
                            segments[1].startsWith("/by")) {
                        throw new MissingParameterException("Task name");
                    }
                    segments = segments[1].split(" /by ", 2);
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingFlagException("/by");
                    }
                    Deadline task = new Deadline(segments[0],
                            DateTimeParser.parseDateTime(segments[1], TaskManager.DATE_FORMAT_FILE));
                    taskManager.addTask(task);
                }
                case EVENT -> {
                    if (segments.length < 2 ||
                            "".equals(segments[1]) ||
                            segments[1].startsWith("/from")) {
                        throw new MissingParameterException("Task name");
                    }
                    segments = segments[1].split(" /from ", 2);
                    String event = segments[0];
                    if (segments.length < 2 ||
                            "".equals(segments[1]) ||
                            segments[1].startsWith("/to")) {
                        throw new MissingFlagException("/from");
                    }
                    segments = segments[1].split(" /to ", 2);
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingFlagException("/to");
                    }
                    Event task = new Event(event,
                            DateTimeParser.parseDateTime(segments[0], TaskManager.DATE_FORMAT_FILE),
                            DateTimeParser.parseDateTime(segments[1], TaskManager.DATE_FORMAT_FILE));
                    taskManager.addTask(task);
                }
                case DELETE -> {
                    if (segments.length < 2 || "".equals(segments[1])) {
                        throw new MissingParameterException("Task ID");
                    }
                    int taskNumber = Integer.parseInt(segments[1]);
                    taskManager.removeTask(taskNumber);
                }
                case CLEAR -> {
                    taskManager.clearList(true);
                }
                default -> throw new UnknownCommandException();
                }
            } catch (ChatbotException e) {
                System.out.println(e.getMessage());
            } catch (MissingParameterException e) {
                System.out.println(e.getMessage());
            } catch (MissingFlagException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\tINVALID PARAMETER - Task ID is not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tINVALID PARAMETER - Task ID is not found in list of tasks.");
            } catch (DateTimeException e) {
                System.out.println("\tINVALID PARAMETER - Datetime is not in correct format.");
            } finally {
                System.out.println(Ui.SEPARATOR);
            }
        }
    }

    public static void main(String[] args) {
        new YoshikageKira().run();
    }
}
