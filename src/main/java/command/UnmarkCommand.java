package command;

import exception.MissingParameterException;
import storage.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        taskList.unmarkTask(taskNumber);
    }
}
