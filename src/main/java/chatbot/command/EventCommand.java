package chatbot.command;

import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.parser.DateTimeParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;
import chatbot.task.Event;
import chatbot.ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String fragment) {
        super(fragment);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws MissingParameterException, MissingFlagException {
        String fragment = getFragment();
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
