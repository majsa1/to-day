package nl.hhs.apep2122group1.database;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public interface Database {
    // tasks
    Task[] getAllTasks();
    Task getTask(int id);
    void upsertTask(Task task);
    void deleteTask(Task task);
    void markTask(Task task, boolean done);

    // labels
    Label[] getAllLabels();
    void upsertLabel(Label label);
    void deleteLabel(Label label);

    // users
    User getUser(String username, String password);
    User getUser(String username);
    boolean insertUser(String username, String password, String name);
}
