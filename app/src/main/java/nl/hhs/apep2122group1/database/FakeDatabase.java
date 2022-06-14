package nl.hhs.apep2122group1.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public class FakeDatabase implements Database {
    private final ArrayList<User> users = new ArrayList<User>() {{
        add(new User("test", "Test User", "testpw"));
    }};

    private final ArrayList<Label> labels = new ArrayList<Label>() {{
        add(new Label(1, "Test1", "#B833FF", users.get(0).getUsername()));
        add(new Label(2, "Test2", "#ff7133", users.get(0).getUsername()));
        add(new Label(3, "School", "#ff335b", users.get(0).getUsername()));
    }};

    private final ArrayList<Task> tasks = new ArrayList<Task>() {{
        add(new Task(1, "Buy birthday present",
                LocalDateTime.of(2022, 5, 10, 14, 30),
                LocalDateTime.of(2022, 6, 8, 14, 10),
                "Description 1", users.get(0).getUsername(), null));

        add(new Task(2, "Submit project",
                LocalDateTime.of(2022, 6, 17, 23, 59),
                null, "Description 2", users.get(0).getUsername(), labels.get(2).getId()));

        add(new Task(3, "Visit grandma",
                LocalDateTime.of(2022, 7, 1, 11, 0),
                null, "Description 3", users.get(0).getUsername(), labels.get(0).getId()));

        add(new Task(4, "Test task One",
                LocalDateTime.of(2022, 5, 1, 11, 0),
                null, "Description 4", users.get(0).getUsername(), labels.get(0).getId()));

        add(new Task(5, "Test task Two",
                LocalDateTime.of(2022, 7, 1, 11, 0),
                LocalDateTime.of(2022, 6, 2, 14, 10),
                "Description 5", users.get(0).getUsername(), labels.get(1).getId()));
    }};

    @Override
    public Task[] getAllTasks(String ownerUsername) {
        return tasks.stream().filter(task -> task.getOwnerUsername().equals(ownerUsername)).toArray(Task[]::new);
    }

    @Override
    public Task getTask(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // NEW
    @Override
    public Label getLabel(int id) {
        for (Label l : labels) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    @Override
    public void upsertTask(Task task) {
        if (task.getId() == null) {
            Integer maxId = tasks.stream().max(Comparator.comparingInt(Task::getId)).get().getId();
            task.setId(maxId + 1);
        } else {
            deleteTask(task);
        }
        tasks.add(task);
    }

    @Override
    public void deleteTask(Task task) {
        tasks.removeIf((listTask) -> listTask.getId().equals(task.getId()));
    }

    @Override
    public void markTask(Task task, boolean done) {
        for (Task t : tasks) {
            if (t.getId().equals(task.getId())) {
                if (done)
                    t.markTaskDone();
                else
                    t.markTaskToDo();
                return;
            }
        }
    }

    @Override
    public Label[] getAllLabels(String ownerUsername) {
        return labels.stream().filter(label -> label.getOwnerUsername().equals(ownerUsername)).toArray(Label[]::new);
    }

    @Override
    public void upsertLabel(Label label) {
        if (label.getId() == null) {
            Integer maxId = labels.stream().max(Comparator.comparingInt(Label::getId)).get().getId();
            label.setId(maxId + 1);
        } else {
            deleteLabel(label);
        }
        labels.add(label);
    }

    @Override
    public void deleteLabel(Label label) {
        labels.removeIf((listLabel) -> listLabel.getId().equals(label.getId()));
    }

    @Override
    public User getUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean insertUser(String username, String password, String name) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        users.add(new User(username, name, password));
        return true;
    }
}
