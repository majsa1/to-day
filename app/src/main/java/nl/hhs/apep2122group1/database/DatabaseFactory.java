package nl.hhs.apep2122group1.database;

public class DatabaseFactory {
    private static Database database = new FakeDatabase();

    public static Database getDatabase() {
        return database;
    }
}
