package chatbot.command;

import chatbot.storage.TaskList;
import chatbot.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.sayGoodbye();
    }
}
