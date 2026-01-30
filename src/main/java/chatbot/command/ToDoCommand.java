package chatbot.command;

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
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException {
        String fragment = getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task name");
        }
        ToDo task = new ToDo(fragment);
        taskList.addTask(task);
    }
}
