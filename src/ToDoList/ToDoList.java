package ToDoList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ToDoList {
    private List<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String title, int priority) {
        tasks.add(new Task(title, priority));
        System.out.println("Task added: " + title);
        saveToFile("tasks.dat"); // Автосохранение
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            System.out.println("Task removed: " + tasks.get(index).getTitle());
            tasks.remove(index);
            saveToFile("tasks.dat"); // Автосохранение
        } else {
            System.out.println("Invalid task index! Please enter a number between 1 and " + tasks.size());
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
            System.out.println("Task marked as completed: " + tasks.get(index).getTitle());
            saveToFile("tasks.dat"); // Автосохранение
        } else {
            System.out.println("Invalid task index!");
        }
    }

    public void editTask(int index, String newTitle) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setTitle(newTitle);
            System.out.println("Task updated: " + newTitle);
            saveToFile("tasks.dat"); // Автосохранение
        } else {
            System.out.println("Invalid task index!");
        }
    }

    public void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found!");
        } else {
            System.out.println("\n+----------------------------+");
            System.out.println("|         To-Do Tasks         |");
            System.out.println("+----------------------------+");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String status = task.isCompleted() ? "\u001B[32m[X]\u001B[0m" : "\u001B[31m[ ]\u001B[0m"; // Цветовой
                                                                                                          // статус
                System.out.println(
                        (i + 1) + ". " + status + " " + task.getTitle() + " (Priority: " + task.getPriority() + ")");
                System.out.println("    Created on: "
                        + task.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            }
            System.out.println("+----------------------------+");
        }
    }

    public void searchTasks(String keyword) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found matching keyword: " + keyword);
        }
    }

    public void removeTaskWithConfirmation(int index, Scanner scanner) {
        if (index >= 0 && index < tasks.size()) {
            System.out
                    .print("Are you sure you want to delete the task \"" + tasks.get(index).getTitle() + "\"? (y/n): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")) {
                removeTask(index);
            } else {
                System.out.println("Task not deleted.");
            }
        } else {
            System.out.println("Invalid task index!");
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
            System.out.println("Tasks loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
        System.out.println("Tasks sorted by priority.");
        saveToFile("tasks.dat"); // Автосохранение
    }
}
