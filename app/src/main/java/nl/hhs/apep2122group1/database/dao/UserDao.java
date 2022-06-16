package nl.hhs.apep2122group1.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User getByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM user WHERE username = :username")
    User getByUsername(String username);

    @Insert
    void insert(User user);
}
