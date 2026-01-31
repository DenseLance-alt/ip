package chatbot.app;

import javafx.application.Application;

/**
 * Launches the application.
 * Used as a workaround for classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
