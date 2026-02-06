package chatbot.app;

import java.io.InputStream;

import chatbot.ui.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
    private boolean canIgnoreInput;

    private Image userImage;
    private Image chatbotImage;

    /**
     * Initializes the main window.
     */
    public MainWindow() {
        canIgnoreInput = false;

        InputStream userImageStream = getClass().getResourceAsStream("/images/hayato_kawajiri.png");
        assert userImageStream != null : "User image should exist in resources folder!";
        userImage = new Image(userImageStream);

        InputStream chatbotImageStream = this.getClass().getResourceAsStream("/images/yoshikage_kira.png");
        assert chatbotImageStream != null : "Chatbot image should exist in resources folder!";
        chatbotImage = new Image(chatbotImageStream);
    }

    /**
     * Initializes the GUI resources for the main window.
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

        if (Ui.getFarewell().equals(response)) {
            //@@author DenseLance-alt-reused
            //Reused from https://stackoverflow.com/a/13602324
            // and https://stackoverflow.com/a/49881572
            // with minor modifications
            canIgnoreInput = true;

            InputStream chatbotImageStream = this.getClass().getResourceAsStream("/images/hands.png");
            assert chatbotImageStream != null : "Chatbot image should exist in resources folder!";
            chatbotImage = new Image(chatbotImageStream);

            Stage stage = (Stage) dialogContainer.getScene().getWindow();
            assert stage != null : "Main window cannot be closed!";
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> stage.close()));
            timeline.play(); // exit program
            //@@author
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChatbotDialog(response.replaceAll("\t", ""), chatbotImage)
        );
        userInput.clear();
    }
}
