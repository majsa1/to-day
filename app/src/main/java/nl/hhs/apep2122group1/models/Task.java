package nl.hhs.apep2122group1.models;

import static androidx.room.ForeignKey.SET_NULL;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "username",
                childColumns = "User_username",
                onDelete = SET_NULL), // check if correct

        @ForeignKey(
                entity = Label.class,
                parentColumns = "id",
                childColumns = "Label_id",
                onDelete = SET_NULL) // check if correct
})

public class Task implements Comparable<Task> {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
//    @TypeConverters({Converter.class}) -> make class
    private LocalDateTime deadline;
    private LocalDateTime completed;
    private String description;

    @ColumnInfo(name = "User_username") // name from ERD
    private Integer ownerUsername;

    @ColumnInfo(name = "Label_id") // name from ERD
    private Integer labelId;

    @Ignore
    private User owner;

    @Ignore
    private Label label;

    // for demo data on list (constructor and static list):
    public Task(Integer id, String title, LocalDateTime deadline, LocalDateTime completed) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.completed = completed;
    }

    @Ignore
    private static ArrayList<Task> demo = new ArrayList<Task>(){{
        add(new Task(1, "Buy birthday present",
        LocalDateTime.of(2022, 6, 10, 14, 30),
        LocalDateTime.of(2022, 6, 8, 14, 10)));

        add(new Task(2, "Submit project",
        LocalDateTime.of(2022, 6, 17, 23, 59),
        null));

        add(new Task(3, "Visit grandma",
        LocalDateTime.of(2022, 7, 1, 11, 0),
        null));

        add(new Task(4, "Test task One",
                LocalDateTime.of(2022, 5, 1, 11, 0),
                null));

        add(new Task(5, "Test task Two",
                LocalDateTime.of(2022, 7, 1, 11, 0),
                null));
    }};

    public static ArrayList<Task> getDemo() {
        return demo;
    }

    // constructor for db:
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

    public Integer getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(Integer ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelOwnerId) {
        this.labelId = labelOwnerId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public int compareTo(Task task) {
        if (this.completed == null) { // task not yet completed
            return this.deadline.compareTo(task.getDeadline());
        } else {
            return this.completed.compareTo(task.getCompleted());
        }
    }

    public void markTaskDone() {
        this.completed = LocalDateTime.now();
    }

    public void markTaskToDo() {
        this.completed = null;
    }
}
