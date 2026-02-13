package chatbot.app;

import java.io.InputStream;

import chatbot.ui.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for GUI.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMAGE_LOCATION =
            "/images/hayato_kawajiri.png";
    private static final String CHATBOT_IMAGE_LOCATION =
            "/images/yoshikage_kira.png";
    private static final String CHATBOT_EXIT_IMAGE_LOCATION =
            "/images/hands.png";

    private static final Duration TIME_TAKEN_TO_EXIT = Duration.seconds(2);

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private YoshikageKira chatbot;
    private boolean canIgnoreInput;

    private Image userImage;
    private Image chatbotImage;

    /**
     * Initializes the main window.
     */
    public MainWindow() {
        canIgnoreInput = false;
        userImage = createImage(USER_IMAGE_LOCATION);
        chatbotImage = createImage(CHATBOT_IMAGE_LOCATION);
    }

    /**
     * Initializes the GUI resources for the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Displays dialog boxes on the GUI.
     * @param dialogBoxes Dialog boxes to display.
     */
    public void displayDialogBoxes(DialogBox... dialogBoxes) {
        dialogContainer.getChildren().addAll(dialogBoxes);
    }

    /**
     * Creates an Image object.
     * @param imageLocation Location where image is stored.
     * @return Image object.
     */
    private static Image createImage(String imageLocation) {
        InputStream imageStream = MainWindow.class.getResourceAsStream(imageLocation);
        assert imageStream != null : "Image should exist!";
        return new Image(imageStream);
    }

    /**
     * Injects the chatbot instance into the GUI logic.
     * @param chatbot Chatbot instance.
     */
    public void injectChatbotInstance(YoshikageKira chatbot) {
        this.chatbot = chatbot;
        String helloMessage = YoshikageKira.processResponseForGui(chatbot.getHello(), true);
        DialogBox chatbotDialogBox = DialogBox.getChatbotDialog(helloMessage, chatbotImage);
        displayDialogBoxes(chatbotDialogBox);
    }

    /**
     * Creates a dialog box for user input and another for chatbot response.
     * Appends the dialog box to the dialog container.
     * Clears the user input after processing.
     */
    //@@author DenseLance-alt-reused
    //Reused from https://stackoverflow.com/a/13602324
    // and https://stackoverflow.com/a/49881572
    // with minor modifications
    @FXML
    private void handleUserInput() {
        if (canIgnoreInput) {
            return;
        }

        String input = userInput.getText();
        String response = chatbot.getResponse(input);
        String processedResponse = YoshikageKira.processResponseForGui(response, false);

        if (Ui.getFarewell().equals(response)) {
            exitProgramAfterAWhile();
        }

        DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
        DialogBox chatbotDialogBox = DialogBox.getChatbotDialog(processedResponse, chatbotImage);
        chatbotDialogBox.colorMessage();
        displayDialogBoxes(userDialogBox, chatbotDialogBox);

        userInput.clear();
    }

    private void exitProgramAfterAWhile() {
        canIgnoreInput = true;
        chatbotImage = createImage(CHATBOT_EXIT_IMAGE_LOCATION);
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        assert stage != null : "Main window cannot be closed!";
        Timeline timeline = new Timeline(
                new KeyFrame(TIME_TAKEN_TO_EXIT, event -> stage.close()));
        timeline.play();
    }
    //@@author
}
