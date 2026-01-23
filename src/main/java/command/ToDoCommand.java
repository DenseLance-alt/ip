package command;

import exception.MissingParameterException;
import task.TaskManager;
import task.ToDo;
import utils.Ui;

public class ToDoCommand extends Command {
    public ToDoCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task name");
        }
        ToDo task = new ToDo(fragment);
        taskManager.addTask(task);
    }
}
