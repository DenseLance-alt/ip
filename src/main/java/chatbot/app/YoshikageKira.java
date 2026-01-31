package chatbot.app;

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
        ui = new Ui();
        taskList = TaskStorage.loadTasks();
    }

    /**
     * Gets hello response from chatbot.
     * @return Hello response.
     */
    public String getHello() {
        return ui.sayHello();
    }

    /**
     * Gets response from chatbot based on user input.
     * @param input
     * @return Response from chatbot.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = CommandParser.parseCommand(input);
            response = command.execute(ui, taskList);
            TaskStorage.saveTasks(taskList);
        } catch (ChatbotException | MissingFlagException | MissingParameterException e) {
            response = e.getMessage();
        } catch (NumberFormatException e) {
            response = "\tINVALID PARAMETER - Task ID is not a number.";
        } catch (IndexOutOfBoundsException e) {
            response = "\tINVALID PARAMETER - Task ID is not found in list of tasks.";
        } catch (DateTimeException e) {
            response = "\tINVALID PARAMETER - Datetime is not in correct format.";
        }
        return response;
    }

    /**
     * Executes program via CLI.
     */
    public void run() {
        ui.printSeparator();
        System.out.println(ui.sayHello());
        ui.printSeparator();
        while (!ui.isDone()) {
            String input = ui.getUserInput();
            ui.printSeparator();
            System.out.println(this.getResponse(input));
            ui.printSeparator();
        }
    }

    /**
     * Provides entry point to run program via CLI.
     * @param args Arguments to main.
     */
    public static void main(String... args) {
        new YoshikageKira().run();
    }
}
