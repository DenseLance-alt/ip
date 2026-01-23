package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.listTasks();
    }
}
