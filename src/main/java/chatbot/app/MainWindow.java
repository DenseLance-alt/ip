package chatbot.app;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private YoshikageKira chatbot;
    private boolean canIgnoreInput = false;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/hayato_kawajiri.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/yoshikage_kira.png"));

    /**
     * Initializes the GUI resources.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the chatbot instance into the GUI logic.
     * @param chatbot Chatbot instance.
     */
    public void injectChatbotInstance(YoshikageKira chatbot) {
        this.chatbot = chatbot;
        dialogContainer.getChildren().addAll(
                DialogBox.getChatbotDialog(
                        this.chatbot.getHello()
                                .replaceAll("\t", "")
                                .replaceAll(System.lineSeparator(), ""),
                        chatbotImage)
        );
    }

    /**
     * Creates a dialog box for user input and another for chatbot response.
     * Appends the dialog box to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (canIgnoreInput) {
            return; // ignore user input
        }
        String input = userInput.getText();
        String response = chatbot.getResponse(input);
        Image chatbotImage = this.chatbotImage;
        Timeline timeline = null;

        if (Ui.FAREWELL.equals(response)) {
            //@@author DenseLance-alt-reused
            //Reused from https://stackoverflow.com/a/13602324
            // and https://stackoverflow.com/a/49881572
            // with minor modifications
            canIgnoreInput = true;
            chatbotImage = new Image(this.getClass().getResourceAsStream("/images/hands.png"));

            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            if (stage != null) {
                timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> stage.close()));
            }
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatbotDialog(response.replaceAll("\t", ""), chatbotImage)
        );
        userInput.clear();

        // Exit program
        if (timeline != null) {
            timeline.play();
        }
    }
}
