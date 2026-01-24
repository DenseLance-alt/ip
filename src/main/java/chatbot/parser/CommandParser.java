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
        case "FIND" -> new FindCommand(fragment);
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
