package chatbot.ui;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {
    // Adapted from the copypasta https://www.reddit.com/r/copypasta/comments/mmaq30/my_name_is_yoshikage_kira/
    public static final String FAREWELL = "\tNo... No, No, No! Where are they taking me!? "
            + System.lineSeparator()
            + "\tThey're dragging me away! Nooo!";
    private static final String INTRODUCTION = "\tMy name is Yoshikage Kira. I'm 33 years old. My house is in "
            + System.lineSeparator()
            + "\tthe northeast section of Morioh, where all the villas are, "
            + System.lineSeparator()
            + "\tand I am not married. I work as an employee for the Kame Yu "
            + System.lineSeparator()
            + "\tdepartment stores, and I get home every day by 8 PM at the "
            + System.lineSeparator()
            + "\tlatest. I don't smoke, but I occasionally drink. I'm in bed "
            + System.lineSeparator()
            + "\tby 11 PM, and make sure I get eight hours of sleep, no "
            + System.lineSeparator()
            + "\tmatter what. After having a glass of warm milk and doing "
            + System.lineSeparator()
            + "\tabout twenty minutes of stretches before going to bed, I "
            + System.lineSeparator()
            + "\tusually have no problems sleeping until morning. Just like "
            + System.lineSeparator()
            + "\ta baby, I wake up without any fatigue or stress in the "
            + System.lineSeparator()
            + "\tmorning. I was told there were no issues at my last "
            + System.lineSeparator()
            + "\tcheck-up. I'm trying to explain that I'm a person who "
            + System.lineSeparator()
            + "\twishes to live a very quiet life. I take care not to "
            + System.lineSeparator()
            + "\ttrouble myself with any enemies, like winning and losing, "
            + System.lineSeparator()
            + "\tthat would cause me to lose sleep at night. That is how I "
            + System.lineSeparator()
            + "\tdeal with society, and I know that is what brings me "
            + System.lineSeparator()
            + "\thappiness. Although, if I were to fight I wouldn't lose to "
            + System.lineSeparator()
            + "\tanyone. ";
    private static final String SEPARATOR = "\t____________________________________________________________";

    private Scanner scanner;
    private boolean isDone;

    /**
     * Initializes the user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        isDone = false;
    }

    /**
     * Displays greeting to user.
     * @return Chatbot introduction message.
     */
    public String sayHello() {
        return INTRODUCTION;
    }

    /**
     * Displays farewell to user.
     * Closes scanner and terminates program.
     * @return Chatbot farewell message.
     */
    public String sayGoodbye() {
        scanner.close();
        isDone = true;
        return FAREWELL;
    }

    /**
     * Displays separator for readability.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Gets input from user.
     * @return User input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Checks if program has terminated.
     * @return Termination status.
     */
    public boolean isDone() {
        return isDone;
    }
}
