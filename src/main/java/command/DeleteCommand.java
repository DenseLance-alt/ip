package command;

import exception.MissingParameterException;
import task.TaskManager;
import utils.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        taskManager.removeTask(taskNumber);
    }
}
