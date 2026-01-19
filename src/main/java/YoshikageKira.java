import java.util.Scanner;
import java.util.ArrayList;

public class YoshikageKira {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";

        System.out.println(separator);
        System.out.println("\tHello! I'm Yoshikage Kira");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> textList = new ArrayList<>();
        while (true) {
            String command = scanner.nextLine();

            // Exit Program
            if (command.equals("bye")) {
                break;
            }

            // Respond to Other Commands
            System.out.println(separator);
            if (command.equals("list")) {
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
        System.out.println(separator);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
