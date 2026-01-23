package command;

public enum Command {
    UNKNOWN,
    BYE, // quit program
    LIST, // list all tasks
    MARK, UNMARK, // mark/unmark task
    TODO, DEADLINE, EVENT, // create tasks
    DELETE, // delete task
    CLEAR; // clear all tasks in list

    public static Command convertToCommand(String commandString) {
        commandString = commandString.toUpperCase();
        for (Command command : values()) {
            if (command.name().equals(commandString)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
