package command;

import task.TaskManager;
import ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) {
        ui.sayGoodbye();
    }
}
