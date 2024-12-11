import java.util.InputMismatchException;
import java.util.Scanner;

import ToDoList.ToDoList;

public class Main {
    public static void main(String[] args) {
        AsciiArt.printAsciiArt();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n+------------------------------------+");
            System.out.println("|             CMDLIST Menu           |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. To-Do List                      |");
            System.out.println("| 2. Calculator | Coming soon        |");
            System.out.println("| 3. Binary Translator | Coming soon |");
            System.out.println("| 4. Exit                            |");
            System.out.println("+------------------------------------+");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);
            if (choice == -1)
                continue;

            switch (choice) {
                case 1 -> {
                    ToDoList toDoList = new ToDoList();
                    toDoList.loadFromFile("tasks.dat");
                    toDoListMenu(toDoList, scanner);
                }
                case 2 -> System.out.println("Calculator feature is coming soon!");
                case 3 -> System.out.println("Binary Translator feature is coming soon!");
                case 4 -> {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void toDoListMenu(ToDoList toDoList, Scanner scanner) {
        while (true) {
            System.out.println("\n+----------------------------+");
            System.out.println("|     To-Do List Menu        |");
            System.out.println("+----------------------------+");
            System.out.println("| 1. Show tasks              |");
            System.out.println("| 2. Add task                |");
            System.out.println("| 3. Remove task             |");
            System.out.println("| 4. Mark task as completed  |");
            System.out.println("| 5. Save and exit           |");
            System.out.println("+----------------------------+");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);
            scanner.nextLine();

            switch (choice) {
                case 1 -> toDoList.showTasks();
                case 2 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    if (title.isBlank()) {
                        System.out.println("Task title cannot be empty!");
                    } else {
                        toDoList.addTask(title);
                    }
                }
                case 3 -> {
                    System.out.print("Enter task number to remove: ");
                    int index = getIntInput(scanner) - 1;
                    if (index >= 0)
                        toDoList.removeTask(index);
                }
                case 4 -> {
                    System.out.print("Enter task number to mark as completed: ");
                    int index = getIntInput(scanner) - 1;
                    if (index >= 0)
                        toDoList.markTaskAsCompleted(index);
                }
                case 5 -> {
                    toDoList.saveToFile("tasks.dat");
                    System.out.println("Exiting To-Do List menu.");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine();
            return -1;
        }
    }
}