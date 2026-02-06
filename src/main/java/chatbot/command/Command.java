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
     * Obtains task number from the given command fragment.
     * @param fragment Command fragment containing only the task ID.
     * @return Task number.
     * @throws MissingParameterException Exceptions that occur due to missing parameters.
     */
    public static int getTaskNumberFromFragment(String fragment) throws MissingParameterException {
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        return Integer.parseInt(fragment);
    }

    /**
     * Returns user input without the main command
     * @return User input without the main command.
     */
    public String getFragment() {
        return fragment;
    }

    /**
     * Returns parameters provided by user input.
     * @return Parameters provided by user input.
     * @throws MissingParameterException Exceptions that occur due to missing parameters.
     * @throws MissingFlagException      Exceptions that occur due to missing flags.
     */
    public String[] getParameters() throws MissingParameterException, MissingFlagException {
        return new String[] {getFragment()};
    }

    /**
     * Executes user command.
     *
     * @param ui       User interface.
     * @param tasks List of tasks.
     * @return Notification to user about outcome.
     * @throws ChatbotException          Exceptions that occur due to invalid or unknown commands.
     * @throws MissingParameterException Exceptions that occur due to missing parameters.
     * @throws MissingFlagException      Exceptions that occur due to missing flags.
     */
    public abstract String execute(Ui ui, TaskList tasks)
            throws ChatbotException, MissingParameterException, MissingFlagException;
}
