package nl.hhs.apep2122group1.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task implements Comparable<Task> {
    private Integer id;
    private String title;
    private LocalDateTime deadline;
    private LocalDateTime completed;
    private String description;
    // foreign keys to be added

    // for demo data on list (constructor and static list):
    public Task(String title, LocalDateTime deadline, LocalDateTime completed) {
        this.title = title;
        this.deadline = deadline;
        this.completed = completed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static ArrayList<Task> demo = new ArrayList<Task>(){{
        add(new Task("Buy birthday present",
        LocalDateTime.of(2022, 6, 10, 14, 30),
        LocalDateTime.of(2022, 6, 8, 14, 10)));

        add(new Task("Submit project",
        LocalDateTime.of(2022, 6, 17, 23, 59),
        null));

        add(new Task("Visit grandma",
        LocalDateTime.of(2022, 7, 1, 11, 0),
        null));

        add(new Task("Test task One",
                LocalDateTime.of(2022, 7, 1, 11, 0),
                null));

        add(new Task("Test task Two",
                LocalDateTime.of(2022, 7, 1, 11, 0),
                null));
    }};

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Task> getDemo() {
        return demo;
    }

    // for db:
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Task task) {
        if (this.completed == null) { // task not yet completed
            return this.deadline.compareTo(task.getDeadline());
        } else {
            return this.completed.compareTo(task.getCompleted());
        }
    }
}
