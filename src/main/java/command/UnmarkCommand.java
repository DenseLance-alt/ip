package command;

import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import task.TaskManager;
import ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        taskManager.unmarkTask(taskNumber);
    }
}
