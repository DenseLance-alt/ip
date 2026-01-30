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
    public void execute(Ui ui, TaskList taskList) {
        taskList.clearList();
    }
}
