package chatbot.command;

import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

/**
 * Provides a way to execute FIND commands.
 */
public class FindCommand extends Command {
    public FindCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String execute(Ui ui, TaskList tasks) throws MissingParameterException {
        String fragment = getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Keyword");
        }
        return tasks.findTasks(fragment);
    }
}
