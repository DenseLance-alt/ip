package command;

import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import storage.TaskList;
import ui.Ui;

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
