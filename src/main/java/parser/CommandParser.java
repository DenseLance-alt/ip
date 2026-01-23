package parser;

import command.ByeCommand;
import command.ClearCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnknownCommand;
import command.UnmarkCommand;
import exception.ChatbotException;
import exception.InvalidCommandException;

public class CommandParser {
    public static Command parseCommand(String input) throws ChatbotException {
        if (input.contains("|")) {
            throw new InvalidCommandException(); // delimiter is reserved for saving to file
        }

        String[] segments = input.split(" ", 2);
        String commandString = segments[0].toUpperCase();
        String fragment = segments.length == 2 ? segments[1] : "";
        return switch (commandString) {
        case "BYE" -> new ByeCommand(fragment);
        case "CLEAR" -> new ClearCommand(fragment);
        case "LIST" -> new ListCommand(fragment);
        case "MARK" -> new MarkCommand(fragment);
        case "UNMARK" -> new UnmarkCommand(fragment);
        case "TODO" -> new ToDoCommand(fragment);
        case "DEADLINE" -> new DeadlineCommand(fragment);
        case "EVENT" -> new EventCommand(fragment);
        case "DELETE" -> new DeleteCommand(fragment);
        default -> new UnknownCommand(fragment);
        };
    }
}
