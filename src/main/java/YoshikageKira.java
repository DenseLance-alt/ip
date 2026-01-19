import java.util.Scanner;
import java.util.ArrayList;

public class YoshikageKira {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";

        System.out.println(separator);
        System.out.println("\tHello! I'm Yoshikage Kira");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> textList = new ArrayList<>();
        while (!done) {
            String command = scanner.nextLine();

            // Respond to Commands
            System.out.println(separator);
            if (command.equals("bye")) {
                done = true;
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                for (int i = 0; i < textList.size(); i++) {
                    System.out.println(
                            String.format("\t%d. %s", i + 1, textList.get(i)));
                }
            } else {
                textList.add(command);
                System.out.println("\tadded: " + command);
            }
            System.out.println(separator);
        }
        scanner.close();
    }
}
