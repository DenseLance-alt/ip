package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute LIST commands.
 */
public class ListCommand extends Command {
    public ListCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return tasks.listTasks();
    }
}
