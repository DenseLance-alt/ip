package command;

import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import task.TaskManager;
import ui.Ui;

public abstract class Command {
    private String fragment;

    public Command(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return this.fragment;
    }

    public abstract void execute(Ui ui, TaskManager taskManager)
            throws ChatbotException, MissingParameterException, MissingFlagException;
}
