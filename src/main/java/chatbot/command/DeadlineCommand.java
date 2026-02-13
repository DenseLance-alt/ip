package chatbot.command;

import java.time.LocalDateTime;

import chatbot.exception.InvalidDateTimeException;
import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;
import chatbot.task.Deadline;
import chatbot.ui.Ui;

/**
 * Provides a way to execute DEADLINE commands.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String[] getParameters() throws MissingParameterException, MissingFlagException {
        String fragment = getFragment();
        if ("".equals(fragment) || fragment.startsWith("/by")) {
            throw new MissingParameterException("Task name");
        }

        String[] parameters = fragment.split(" /by ", 2);
        if (parameters.length < 2 || "".equals(parameters[1])) {
            throw new MissingFlagException("/by");
        }

        return parameters;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) throws MissingParameterException, MissingFlagException, InvalidDateTimeException {
        String[] parameters = getParameters();

        LocalDateTime completionDate = DateTimeParser.parseDateTime(parameters[1], TaskStorage.DATE_FORMAT);
        Deadline task = new Deadline(parameters[0], completionDate);
        return tasks.addTask(task);
    }
}
