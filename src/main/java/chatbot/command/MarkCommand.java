package chatbot.command;

import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute MARK commands.
 */
public class MarkCommand extends Command {
    public MarkCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList tasks) throws MissingParameterException {
        String fragment = getFragment();
        int taskNumber = getTaskNumberFromFragment(fragment);
        return tasks.markTask(taskNumber);
    }
}
