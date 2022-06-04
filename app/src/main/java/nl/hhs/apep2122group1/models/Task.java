package nl.hhs.apep2122group1.models;

import java.time.LocalDateTime;

public class Task {
    private Integer id;
    private String title;
    private LocalDateTime deadline;
    private LocalDateTime completed;
    private String description;
    // foreign keys to be added

    public Task() {
    }

    // all setters and getters added, remove unneeded
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
