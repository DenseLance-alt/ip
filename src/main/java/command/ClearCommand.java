package command;

import task.TaskManager;
import ui.Ui;

public class ClearCommand extends Command {
    public ClearCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) {
        taskManager.clearList(true);
    }
}
