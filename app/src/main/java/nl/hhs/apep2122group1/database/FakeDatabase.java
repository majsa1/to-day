package nl.hhs.apep2122group1.database;

import java.util.ArrayList;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public class FakeDatabase implements Database {
    private final ArrayList<User> users = new ArrayList<User>() {{
        add(new User("test", "Test User", "testpw"));
    }};
    private final ArrayList<Task> tasks = new ArrayList<Task>() {{
        add(new Task());
        add(new Task());
        add(new Task());
        add(new Task());
    }};
    private final ArrayList<Label> labels = new ArrayList<Label>() {{
        add(new Label());
        add(new Label());
        add(new Label());
    }};

    @Override
    public Task[] getAllTasks() {
        return tasks.toArray(new Task[0]);
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

    @Override
    public void upsertTask(Task task) {
        deleteTask(task);
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
    public Label[] getAllLabels() {
        return labels.toArray(new Label[0]);
    }

    @Override
    public void upsertLabel(Label label) {
        deleteLabel(label);
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
