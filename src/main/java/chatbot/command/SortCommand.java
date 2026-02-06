package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute SORT commands.
 */
public class SortCommand extends Command {
    public SortCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return tasks.sortTasksByDate();
    }
}
