package chatbot.command;

import chatbot.exception.MissingParameterException;
import chatbot.storage.TaskList;
import chatbot.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException {
        String fragment = this.getFragment();
        if ("".equals(fragment)) {
            throw new MissingParameterException("Task ID");
        }
        int taskNumber = Integer.parseInt(fragment);
        taskList.markTask(taskNumber);
    }
}
