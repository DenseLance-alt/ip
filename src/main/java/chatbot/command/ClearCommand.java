package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute CLEAR commands.
 */
public class ClearCommand extends Command {
    public ClearCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return taskList.clearList();
    }
}
