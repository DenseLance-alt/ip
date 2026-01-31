package chatbot.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Creates the GUI for the application.
 */
public class Gui extends Application {
    private YoshikageKira chatbot = new YoshikageKira();

    /**
     * Initializes the application.
     * @param stage Container for GUI resources.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yoshikage Kira Chatbot");

            // set window size limits
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            // stage.setMaxWidth(417); // prevent horizontal resize

            fxmlLoader.<MainWindow>getController().injectChatbotInstance(chatbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
