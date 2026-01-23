package command;

import exception.MissingFlagException;
import exception.MissingParameterException;
import parser.DateTimeParser;
import task.Deadline;
import task.TaskManager;
import ui.Ui;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) throws MissingParameterException, MissingFlagException {
        String fragment = this.getFragment();
        if ("".equals(fragment) || fragment.startsWith("/by")) {
            throw new MissingParameterException("Task name");
        }
        String[] segments = fragment.split(" /by ", 2);
        if (segments.length < 2 || "".equals(segments[1])) {
            throw new MissingFlagException("/by");
        }
        Deadline task = new Deadline(segments[0],
                DateTimeParser.parseDateTime(segments[1], TaskManager.DATE_FORMAT_FILE));
        taskManager.addTask(task);
    }
}
