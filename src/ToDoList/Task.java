package ToDoList;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable {
    private String title;
    private boolean isCompleted;
    private int priority; // 1 - High, 2 - Medium, 3 - Low
    private LocalDateTime createdAt;

    public Task(String title, int priority) {
        this.title = title;
        this.isCompleted = false;
        this.priority = priority;
        this.createdAt = LocalDateTime.now(); // Устанавливаем дату и время создания
    }

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + title + " (Priority: " + priority + ")";
    }
}
