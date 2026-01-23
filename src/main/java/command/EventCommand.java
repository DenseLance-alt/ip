package command;

import exception.MissingFlagException;
import exception.MissingParameterException;
import parser.DateTimeParser;
import storage.TaskList;
import storage.TaskStorage;
import task.Event;
import ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException, MissingFlagException {
        String fragment = this.getFragment();
        if ("".equals(fragment) || fragment.startsWith("/from")) {
            throw new MissingParameterException("Task name");
        }
        String[] segments = fragment.split(" /from ", 2);
        String event = segments[0];
        if (segments.length < 2 || "".equals(segments[1]) || segments[1].startsWith("/to")) {
            throw new MissingFlagException("/from");
        }
        segments = segments[1].split(" /to ", 2);
        if (segments.length < 2 || "".equals(segments[1])) {
            throw new MissingFlagException("/to");
        }
        Event task = new Event(event,
                DateTimeParser.parseDateTime(segments[0], TaskStorage.DATE_FORMAT),
                DateTimeParser.parseDateTime(segments[1], TaskStorage.DATE_FORMAT));
        taskList.addTask(task);
    }
}
