package command;

import exception.ChatbotException;
import exception.UnknownCommandException;
import task.TaskManager;
import utils.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) throws ChatbotException {
        throw new UnknownCommandException();
    }
}
