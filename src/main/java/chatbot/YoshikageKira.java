package chatbot;

import java.time.DateTimeException;

import chatbot.command.Command;
import chatbot.exception.ChatbotException;
import chatbot.exception.MissingFlagException;
import chatbot.exception.MissingParameterException;
import chatbot.parser.CommandParser;
import chatbot.storage.TaskList;
import chatbot.storage.TaskStorage;
import chatbot.ui.Ui;


/**
 * Represents the chatbot.
 * Provides the main logic of the app.
 */
public class YoshikageKira {
    private Ui ui;
    private TaskList taskList;

    /**
     * Initializes the chatbot.
     */
    public YoshikageKira() {
        this.ui = new Ui();
        this.taskList = TaskStorage.loadTasks();
    }

    /**
     * Executes program.
     */
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

    /**
     * Provides entry point to run program
     * @param args Arguments to main.
     */
    public static void main(String[] args) {
        new YoshikageKira().run();
    }
}
