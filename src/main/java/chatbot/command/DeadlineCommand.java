package chatbot.command;

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
    public String execute(Ui ui, TaskList taskList) throws MissingParameterException, MissingFlagException {
        String fragment = getFragment();
        if ("".equals(fragment) || fragment.startsWith("/by")) {
            throw new MissingParameterException("Task name");
        }
        String[] segments = fragment.split(" /by ", 2);
        if (segments.length < 2 || "".equals(segments[1])) {
            throw new MissingFlagException("/by");
        }
        Deadline task = new Deadline(segments[0],
                DateTimeParser.parseDateTime(segments[1], TaskStorage.DATE_FORMAT));
        return taskList.addTask(task);
    }
}
