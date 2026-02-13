package chatbot.app;

import java.io.IOException;
import java.net.URL;

import chatbot.exception.ExceptionCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Creates a dialog box for GUI.
 */
public class DialogBox extends HBox {
    private static final URL DIALOG_BOX_RESOURCE =
            MainWindow.class.getResource(Gui.DIALOG_BOX_RESOURCE_LOCATION);

    @FXML
    private Label label;
    @FXML
    private ImageView displayImage;

    /**
     * Initializes the dialog box for GUI.
     * @param text Text to show in dialog box.
     * @param img Image of user or chatbot.
     */
    public DialogBox(String text, Image img) {
        loadDialogBox();
        label.setText(text);
        displayImage.setImage(img);
    }

    /**
     * Loads dialog box onto GUI.
     */
    private void loadDialogBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DIALOG_BOX_RESOURCE);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flips the dialog box such that image is on the left and text is on the right.
     * Used for chatbot speech bubble.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> dialogBoxObservables = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(dialogBoxObservables);
        getChildren().setAll(dialogBoxObservables);
        label.getStyleClass().add("reply-label"); // link to css to flipped reply bubble
    }

    /**
     * Creates a user dialog.
     * @param s Text sent by user.
     * @param i Image of user.
     * @return Dialog box of user.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a chatbot dialog.
     * @param s Text sent by chatbot.
     * @param i Image of chatbot.
     * @return Dialog box of chatbot.
     */
    public static DialogBox getChatbotDialog(String s, Image i) {
        DialogBox dialogBox = new DialogBox(s, i);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Colors message in the dialog box.
     */
    public void colorMessage() {
        String text = label.getText();
        if (ExceptionCategory.isErrorMessage(text)) {
            colorErrorMessage();
        }
    }

    /**
     * Colors the error message in the dialog box.
     */
    public void colorErrorMessage() {
        label.getStyleClass().add("error-label");
    }
}
