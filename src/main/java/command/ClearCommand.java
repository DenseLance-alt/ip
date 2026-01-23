package command;

import storage.TaskList;
import ui.Ui;

public class ClearCommand extends Command {
    public ClearCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.clearList();
    }
}
