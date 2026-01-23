import command.Command;
import exception.ChatbotException;
import exception.MissingFlagException;
import exception.MissingParameterException;
import parser.CommandParser;
import storage.TaskList;
import storage.TaskStorage;
import ui.Ui;

import java.time.DateTimeException;

public class YoshikageKira {
    private Ui ui;
    private TaskList taskList;

    public YoshikageKira() {
        this.ui = new Ui();
        this.taskList = TaskStorage.loadTasks();
    }

    public void run() {
        this.ui.sayHello();
        while (!ui.isDone()) {
            String input = this.ui.getUserInput();
            this.ui.printSeparator();
            try {
                Command command = CommandParser.parseCommand(input);
                command.execute(this.ui, this.taskList);
                TaskStorage.saveTasks(this.taskList);
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
