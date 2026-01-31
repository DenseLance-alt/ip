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
    public String execute(Ui ui, TaskList taskList) throws MissingParameterException {
        String fragment = getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        return taskList.removeTask(taskNumber);
    }
}
