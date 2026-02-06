package chatbot.command;

import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute DELETE commands.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList tasks) throws MissingParameterException {
        String fragment = getFragment();
        int taskNumber = getTaskNumberFromFragment(fragment);
        return tasks.removeTask(taskNumber);
    }
}
