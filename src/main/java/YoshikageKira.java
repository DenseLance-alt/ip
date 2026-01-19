import java.util.Scanner;

public class YoshikageKira {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";

        System.out.println(separator);
        System.out.println("\tHello! I'm Yoshikage Kira");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(separator);
            System.out.println("\t" + command);
            System.out.println(separator);
        }
        scanner.close();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
