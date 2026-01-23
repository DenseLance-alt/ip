package chatbot.command;

import chatbot.exception.ChatbotException;
import chatbot.exception.UnknownCommandException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws ChatbotException {
        throw new UnknownCommandException();
    }
}
