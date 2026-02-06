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
    // EXCEPTIONS
    private static final String TASK_ID_IS_NAN_MESSAGE =
            "\tINVALID PARAMETER - Task ID is not a number.";
    private static final String TASK_ID_IS_NOT_FOUND_MESSAGE =
            "\tINVALID PARAMETER - Task ID is not found in list of tasks.";
    private static final String DATETIME_NOT_CORRECT_FORMAT_MESSAGE =
            "\tINVALID PARAMETER - Datetime is not in correct format.";

    private Ui ui;
    private TaskList tasks;

    /**
     * Initializes the chatbot.
     */
    public YoshikageKira() {
        ui = new Ui();
        tasks = TaskStorage.loadTasks();
    }

    /**
     *
     */
    public static String processResponseForGui(String response, boolean removeLineSeparator) {
        String processedResponse = response.replaceAll("\t", "");
        processedResponse = removeLineSeparator
                ? processedResponse.replaceAll(System.lineSeparator(), "")
                : processedResponse;
        return processedResponse;
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
     * @param input User input.
     * @return Response from chatbot.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = CommandParser.parseCommand(input);
            response = command.execute(ui, tasks);
            TaskStorage.saveTasks(tasks);
        } catch (ChatbotException | MissingFlagException | MissingParameterException e) {
            response = e.getMessage();
        } catch (NumberFormatException e) {
            response = TASK_ID_IS_NAN_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            response = TASK_ID_IS_NOT_FOUND_MESSAGE;
        } catch (DateTimeException e) {
            response = DATETIME_NOT_CORRECT_FORMAT_MESSAGE;
        }
        assert response != null : "Response from chatbot should not be NULL!";
        return response;
    }

    /**
     * Displays response from chatbot based on user input.
     * @param input User input.
     */
    public void printResponse(String input) {
        System.out.println(getResponse(input));
    }

    /**
     * Executes program via CLI.
     */
    public void run() {
        ui.printSeparator(ui::printHello);
        while (!ui.isDone()) {
            String input = ui.getUserInput();
            ui.printSeparator(() -> printResponse(input));
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
