import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

public class YoshikageKira {
    public static void main(String[] args) {
        String separator = "\t____________________________________________________________";

        System.out.println(separator);
        System.out.println("\tHello! My name is Yoshikage Kira.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(separator);

        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (!done) {
            String input = scanner.nextLine();

            // Respond to Commands
            System.out.println(separator);
            if (input.equals("bye")) {
                done = true;
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(
                            String.format("\t%d.%s", i + 1, taskList.get(i)));
                }
            } else if (input.startsWith("mark ")) {
                String[] segments = input.split(" ", 2);
                int taskNumber = Integer.parseInt(segments[1]);
                Task task = taskList.get(taskNumber - 1);
                task.markTask();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  " + task);
            } else if (input.startsWith("unmark ")) {
                String[] segments = input.split(" ", 2);
                int taskNumber = Integer.parseInt(segments[1]);
                Task task = taskList.get(taskNumber - 1);
                task.unmarkTask();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  " + task);
            } else if (input.startsWith("todo ")) {
                String[] segments = input.split(" ", 2);
                ToDo task = new ToDo(segments[1]);
                taskList.add(task);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + task);
                System.out.println(String.format(
                        "\tNow you have %d tasks in the list.",
                        taskList.size()));
            } else if (input.startsWith("deadline ")) {
                String[] segments = input.split(" ", 2);
                segments = segments[1].split(" /by ", 2);
                Deadline task = new Deadline(segments[0], segments[1]);
                taskList.add(task);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + task);
                System.out.println(String.format(
                        "\tNow you have %d tasks in the list.",
                        taskList.size()));
            } else if (input.startsWith("event ")) {
                String[] segments = input.split(" ", 2);
                segments = segments[1].split(" /from ", 2);
                String event = segments[0];
                segments = segments[1].split(" /to ", 2);
                Event task = new Event(event, segments[0], segments[1]);
                taskList.add(task);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + task);
                System.out.println(String.format(
                        "\tNow you have %d tasks in the list.",
                        taskList.size()));
            } else {
                Task task = new Task(input);
                taskList.add(task);
                System.out.println("\tadded: " + input);
            }
            System.out.println(separator);
        }
        scanner.close();
    }
}
