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
        if (input.contains(TaskStorage.STORAGE_ENTRY_DELIMITER)) {
            throw new InvalidCommandException(); // delimiter is reserved for saving to file
        }

        String[] inputSegments = input.split(" ", 2);
        String commandString = inputSegments[0].toUpperCase();
        String inputFragment = inputSegments.length == 2 ? inputSegments[1] : "";
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
}
