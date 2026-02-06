package chatbot.command;

import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.task.ToDo;
import chatbot.ui.Ui;

/**
 * Provides a way to execute TODO commands.
 */
public class ToDoCommand extends Command {
    public ToDoCommand(String fragment) {
        super(fragment);
    }

    @Override
    public String[] getParameters() throws MissingParameterException, MissingFlagException {
        String fragment = getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task name");
        }

        return new String[] {fragment};
    }

    @Override
    public String execute(Ui ui, TaskList tasks) throws MissingParameterException {
        String fragment = getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task name");
        }
        ToDo task = new ToDo(fragment);
        return tasks.addTask(task);
    }
}
