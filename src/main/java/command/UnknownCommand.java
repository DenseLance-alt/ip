package command;

import exception.ChatbotException;
import exception.UnknownCommandException;
import storage.TaskList;
import ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ChatbotException {
        throw new UnknownCommandException();
    }
}
