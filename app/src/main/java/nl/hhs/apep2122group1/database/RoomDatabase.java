package nl.hhs.apep2122group1.database;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public class RoomDatabase implements Database {
    @Override
    public Task[] getAllTasks(String ownerUsername) {
        // TODO
        return new Task[0];
    }

    @Override
    public Task getTask(int id) {
        // TODO
        return null;
    }

    @Override
    public void upsertTask(Task task) {
        // TODO
    }

    @Override
    public void deleteTask(Task task) {
        // TODO
    }

    @Override
    public void markTask(Task task, boolean done) {
        // TODO
    }

    @Override
    public Label[] getAllLabels(String ownerUsername) {
        // TODO
        return new Label[0];
    }

    @Override
    public void upsertLabel(Label label) {
        // TODO
    }

    @Override
    public void deleteLabel(Label label) {
        // TODO
    }

    @Override
    public User getUser(String username, String password) {
        // TODO
        return null;
    }

    @Override
    public User getUser(String username) {
        // TODO
        return null;
    }


    @Override
    public boolean insertUser(String username, String password, String name) {
        return true;
        // TODO
    }
}
