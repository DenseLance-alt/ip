package chatbot.command;

import chatbot.exception.ChatbotException;
import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute commands.
 */
public abstract class Command {
    private String fragment;

    /**
     * Initializes the command.
     * @param fragment User input without the main command.
     */
    public Command(String fragment) {
        this.fragment = fragment;
    }

    /**
     * Returns user input without the main command
     * @return User input without the main command.
     */
    public String getFragment() {
        return fragment;
    }

    /**
     * Executes user command.
     *
     * @param ui       User interface.
     * @param taskList List of tasks.
     * @return Notification to user about outcome.
     * @throws ChatbotException          Exceptions that occur due to invalid or unknown commands.
     * @throws MissingParameterException Exceptions that occur due to missing parameters.
     * @throws MissingFlagException      Exceptions that occur due to missing flags.
     */
    public abstract String execute(Ui ui, TaskList taskList)
            throws ChatbotException, MissingParameterException, MissingFlagException;
}
