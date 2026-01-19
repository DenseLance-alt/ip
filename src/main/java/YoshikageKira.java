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
        ArrayList<Task> taskList = new ArrayList<>();
        while (!done) {
            String command = scanner.nextLine();

            // Respond to Commands
            System.out.println(separator);
            if (command.equals("bye")) {
                done = true;
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(
                            String.format("\t%d.%s", i + 1, taskList.get(i)));
                }
            } else if (command.startsWith("mark ")) {
                String[] segments = command.split(" ", 2);
                int taskNumber = Integer.parseInt(segments[1]);
                Task task = taskList.get(taskNumber - 1);
                task.markTask();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + task);
            } else if (command.startsWith("unmark ")) {
                String[] segments = command.split(" ", 2);
                int taskNumber = Integer.parseInt(segments[1]);
                Task task = taskList.get(taskNumber - 1);
                task.unmarkTask();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + task);
            } else {
                Task task = new Task(command);
                taskList.add(task);
                System.out.println("\tadded: " + command);
            }
            System.out.println(separator);
        }
        scanner.close();
    }
}
