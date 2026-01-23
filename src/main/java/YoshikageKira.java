import java.time.DateTimeException;

import command.Command;
import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import exception.UnknownCommandException;
import parser.CommandParser;
import parser.DateTimeParser;
import task.Deadline;
import task.Event;
import task.TaskManager;
import task.ToDo;
import ui.Ui;

public class YoshikageKira {
    private Ui ui;
    private TaskManager taskManager;

    public YoshikageKira() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    public void run() {
        this.ui.sayHello();

        while (!ui.isDone()) {
            String input = this.ui.getUserInput();
            this.ui.printSeparator();
            try {
                Command command = CommandParser.parseCommand(input);
                command.execute(this.ui, this.taskManager);
            } catch (ChatbotException e) {
                System.out.println(e.getMessage());
            } catch (MissingParameterException e) {
                System.out.println(e.getMessage());
            } catch (MissingFlagException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("\tINVALID PARAMETER - Task ID is not a number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tINVALID PARAMETER - Task ID is not found in list of tasks.");
            } catch (DateTimeException e) {
                System.out.println("\tINVALID PARAMETER - Datetime is not in correct format.");
            } finally {
                this.ui.printSeparator();
            }
        }
    }

    public static void main(String[] args) {
        new YoshikageKira().run();
    }
}
