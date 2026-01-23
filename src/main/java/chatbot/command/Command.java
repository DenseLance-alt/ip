package chatbot.command;

import chatbot.exception.ChatbotException;
import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

public abstract class Command {
    private String fragment;

    public Command(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return this.fragment;
    }

    public abstract void execute(Ui ui, TaskList taskList)
            throws ChatbotException, MissingParameterException, MissingFlagException;
}
