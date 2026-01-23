package command;

import exception.ChatbotException;
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
