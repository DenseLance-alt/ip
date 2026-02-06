package chatbot.parser;

import chatbot.command.ByeCommand;
import chatbot.command.ClearCommand;
import chatbot.command.Command;
import chatbot.command.DeadlineCommand;
import chatbot.command.DeleteCommand;
import chatbot.command.EventCommand;
import chatbot.command.FindCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.ToDoCommand;
import chatbot.command.UnknownCommand;
import chatbot.command.UnmarkCommand;
import chatbot.exception.ChatbotException;
import chatbot.exception.InvalidCommandException;
import chatbot.storage.TaskStorage;

/**
 * Parses commands.
 */
public class CommandParser {
    /**
     * Parses commands from user input.
     * @param input User input.
     * @return Command to execute.
     * @throws ChatbotException Exceptions that occur due to invalid or unknown commands.
     */
    public static Command parseCommand(String input) throws ChatbotException {
        checkInputForInvalidCharacter(input);
        String commandString = getCommandFromInput(input);
        String inputFragment = getFragmentFromInput(input);
        return createCommand(commandString, inputFragment);
    }

    private static void checkInputForInvalidCharacter(String input) throws InvalidCommandException {
        if (input.contains(TaskStorage.STORAGE_ENTRY_DELIMITER)) {
            throw new InvalidCommandException(); // delimiter is reserved for saving to file
        }
    }

    /**
     * Creates command given its associated string, flags and parameters.
     * @param commandString String associated to a command.
     * @param inputFragment Remaining user input to process.
     * @return Command to execute.
     */
    private static Command createCommand(String commandString, String inputFragment) {
        return switch (commandString) {
        case "BYE" -> new ByeCommand(inputFragment);
        case "CLEAR" -> new ClearCommand(inputFragment);
        case "LIST" -> new ListCommand(inputFragment);
        case "FIND" -> new FindCommand(inputFragment);
        case "MARK" -> new MarkCommand(inputFragment);
        case "UNMARK" -> new UnmarkCommand(inputFragment);
        case "TODO" -> new ToDoCommand(inputFragment);
        case "DEADLINE" -> new DeadlineCommand(inputFragment);
        case "EVENT" -> new EventCommand(inputFragment);
        case "DELETE" -> new DeleteCommand(inputFragment);
        default -> new UnknownCommand(inputFragment);
        };
    }

    /**
     * Retrieves a command string from input.
     * @param input User input.
     * @return String associated to a command.
     */
    private static String getCommandFromInput(String input) {
        return input.split(" ", 2)[0].toUpperCase();
    }

    /**
     * Retrieves flags and parameters from input.
     * @param input User input.
     * @return Flags and parameters.
     */
    private static String getFragmentFromInput(String input) {
        String[] inputSegments = input.split(" ", 2);
        return inputSegments.length == 2 ? inputSegments[1] : "";
    }
}
