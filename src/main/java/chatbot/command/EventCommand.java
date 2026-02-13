package chatbot.command;

import java.time.LocalDateTime;

import chatbot.exception.InvalidDateTimeException;
import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;
import chatbot.task.Event;
import chatbot.ui.Ui;

/**
 * Provides a way to execute EVENT commands.
 */
public class EventCommand extends Command {
    public EventCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String[] getParameters() throws MissingParameterException, MissingFlagException {
        String[] parameters = new String[3];

        String fragment = getFragment();
        if ("".equals(fragment) || fragment.startsWith("/from")) {
            throw new MissingParameterException("Task name");
        }

        String[] processedFragments = fragment.split(" /from ", 2);
        parameters[0] = processedFragments[0];
        if (processedFragments.length < 2
                || "".equals(processedFragments[1])
                || processedFragments[1].startsWith("/to")) {
            throw new MissingFlagException("/from");
        }

        processedFragments = processedFragments[1].split(" /to ", 2);
        parameters[1] = processedFragments[0];
        if (processedFragments.length < 2 || "".equals(processedFragments[1])) {
            throw new MissingFlagException("/to");
        }
        parameters[2] = processedFragments[1];

        return parameters;
    }

    @Override
    public String execute(Ui ui, TaskList tasks)
            throws MissingParameterException, MissingFlagException, InvalidDateTimeException {
        String[] parameters = getParameters();

        LocalDateTime startDate = DateTimeParser.parseDateTime(parameters[1], TaskStorage.DATE_FORMAT);
        LocalDateTime endDate = DateTimeParser.parseDateTime(parameters[2], TaskStorage.DATE_FORMAT);
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateTimeException();
        }
        Event task = new Event(parameters[0], startDate, endDate);
        return tasks.addTask(task);
    }
}
