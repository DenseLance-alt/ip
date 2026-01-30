package chatbot.command;

import chatbot.exception.ChatbotException;
import chatbot.exception.UnknownCommandException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to trigger an error when executing an unknown command.
 */
public class UnknownCommand extends Command {
    public UnknownCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws ChatbotException {
        throw new UnknownCommandException();
    }
}
