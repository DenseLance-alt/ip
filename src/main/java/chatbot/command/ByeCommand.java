package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute BYE commands.
 */
public class ByeCommand extends Command {
    public ByeCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.sayGoodbye();
    }
}
