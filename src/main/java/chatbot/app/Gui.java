package chatbot.app;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Creates the GUI for the application.
 */
public class Gui extends Application {
    public static final String MAIN_WINDOW_RESOURCE_LOCATION =
            "/view/MainWindow.fxml";
    public static final String DIALOG_BOX_RESOURCE_LOCATION =
            "/view/DialogBox.fxml";

    private static final URL MAIN_WINDOW_RESOURCE =
            Gui.class.getResource(MAIN_WINDOW_RESOURCE_LOCATION);

    private static final String APPLICATION_NAME =
            "Yoshikage Kira Chatbot";

    /**
     * Initializes the application.
     * @param stage Container for GUI resources.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MAIN_WINDOW_RESOURCE);
            Scene scene = createScene(fxmlLoader);
            setupStage(stage, scene);
            initializeChatbot(fxmlLoader);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.setTitle(APPLICATION_NAME);

        // Set window size limits
        stage.setMinHeight(220);
        stage.setMinWidth(417);
        // stage.setMaxWidth(417); // prevent horizontal resize
    }

    private Scene createScene(FXMLLoader fxmlLoader) throws IOException {
        AnchorPane anchorPane = fxmlLoader.load();
        return new Scene(anchorPane);
    }

    private void initializeChatbot(FXMLLoader fxmlLoader) {
        YoshikageKira chatbot = new YoshikageKira();
        fxmlLoader.<MainWindow>getController().injectChatbotInstance(chatbot);
    }
}
