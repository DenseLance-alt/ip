package command;

import exception.ChatbotException;
import task.TaskManager;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) {
        taskManager.listTasks();
    }
}
