import java.util.InputMismatchException;
import java.util.Scanner;

import ToDoList.ToDoList;

public class Main {
    public static void main(String[] args) {
        AsciiArt.printAsciiArt();

        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        toDoList.loadFromFile("tasks.dat");

        while (true) {
            System.out.println("\n+------------------------------------+");
            System.out.println("|             CMDLIST Menu           |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. To-Do List                      |");
            System.out.println("| 2. Calculator | Coming soon        |");
            System.out.println("| 3. Binary Translator               |");
            System.out.println("| 4. Exit                            |");
            System.out.println("+------------------------------------+");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);
            if (choice == -1)
                continue;

            switch (choice) {
                case 1 -> toDoListMenu(toDoList, scanner);
                case 2 -> System.out.println("Calculator feature is coming soon!");
                case 3 -> {
                    System.out.print("Enter text to translate to binary: ");
                    scanner.nextLine(); // Consume newline
                    String inputText = scanner.nextLine();
                    String binaryResult = BinaryTranslator.textToBinary(inputText);
                    System.out.println("Binary code: " + binaryResult);
                }
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
            System.out.println("| 5. Edit task               |");
            System.out.println("| 6. Sort tasks by priority  |");
            System.out.println("| 7. Save and exit           |");
            System.out.println("+----------------------------+");
            System.out.print("Choose an option: ");

            int choice = getIntInput(scanner);
            scanner.nextLine();

            switch (choice) {
                case 1 -> toDoList.showTasks();
                case 2 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task priority (1 - High, 2 - Medium, 3 - Low): ");
                    int priority = getIntInput(scanner);
                    if (priority >= 1 && priority <= 3) {
                        toDoList.addTask(title, priority);
                    } else {
                        System.out.println("Invalid priority! Task not added.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter task number to remove: ");
                    int index = getIntInput(scanner) - 1;
                    toDoList.removeTask(index);
                }
                case 4 -> {
                    System.out.print("Enter task number to mark as completed: ");
                    int index = getIntInput(scanner) - 1;
                    toDoList.markTaskAsCompleted(index);
                }
                case 5 -> {
                    System.out.print("Enter task number to edit: ");
                    int index = getIntInput(scanner) - 1;
                    System.out.print("Enter new task title: ");
                    String newTitle = scanner.nextLine();
                    toDoList.editTask(index, newTitle);
                }
                case 6 -> toDoList.sortTasksByPriority();
                case 7 -> {
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
