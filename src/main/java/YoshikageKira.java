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

public class YoshikageKira {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";

        // Adapted from the copypasta https://www.reddit.com/r/copypasta/comments/mmaq30/my_name_is_yoshikage_kira/
        System.out.println(separator);
        System.out.println("\tMy name is Yoshikage Kira. I'm 33 years old. My house is in ");
        System.out.println("\tthe northeast section of Morioh, where all the villas are, ");
        System.out.println("\tand I am not married. I work as an employee for the Kame Yu ");
        System.out.println("\tdepartment stores, and I get home every day by 8 PM at the ");
        System.out.println("\tlatest. I don't smoke, but I occasionally drink. I'm in bed ");
        System.out.println("\tby 11 PM, and make sure I get eight hours of sleep, no ");
        System.out.println("\tmatter what. After having a glass of warm milk and doing ");
        System.out.println("\tabout twenty minutes of stretches before going to bed, I ");
        System.out.println("\tusually have no problems sleeping until morning. Just like ");
        System.out.println("\ta baby, I wake up without any fatigue or stress in the ");
        System.out.println("\tmorning. I was told there were no issues at my last ");
        System.out.println("\tcheck-up. I'm trying to explain that I'm a person who ");
        System.out.println("\twishes to live a very quiet life. I take care not to ");
        System.out.println("\ttrouble myself with any enemies, like winning and losing, ");
        System.out.println("\tthat would cause me to lose sleep at night. That is how I ");
        System.out.println("\tdeal with society, and I know that is what brings me ");
        System.out.println("\thappiness. Although, if I were to fight I wouldn't lose to ");
        System.out.println("\tanyone.");
        System.out.println(System.lineSeparator() + "\tWhat can I do for you?");
        System.out.println(separator);

        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (!done) {
            String input = scanner.nextLine();
            String[] segments = input.split(" ", 2);
            Command command = Command.convertToCommand(segments[0]);

            // Respond to Commands
            System.out.println(separator);
            try {
                if (input.contains("|")) {
                    throw new InvalidCommandException(); // delimiter is reserved for saving to file
                }
                switch (command) {
                case BYE -> {
                    done = true;
                    System.out.println("\tNo... No, No, No! Where are they taking me!? ");
                    System.out.println("\tThey're dragging me away! Nooo!");
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
                            DateTimeParser.parseDateTime(segments[1], TaskManager.FILE_DATE_FORMAT));
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
                            DateTimeParser.parseDateTime(segments[0], TaskManager.FILE_DATE_FORMAT),
                            DateTimeParser.parseDateTime(segments[1], TaskManager.FILE_DATE_FORMAT));
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
                System.out.println(separator);
            }
        }
        scanner.close();
    }
}
