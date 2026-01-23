package command;

import exception.MissingParameterException;
import storage.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        taskList.removeTask(taskNumber);
    }
}
