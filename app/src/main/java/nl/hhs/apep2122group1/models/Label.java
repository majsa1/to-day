package nl.hhs.apep2122group1.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "username",
                childColumns = "User_username",
                onDelete = CASCADE),
})

public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String colorCode;

    @ColumnInfo(name = "User_username")
    private String ownerUsername;

    @Ignore
    private User owner;

    // for demo:
    public Label(Integer id, String title, String colorCode, String ownerUsername) {
        this.id = id;
        this.title = title;
        this.colorCode = colorCode;
        this.ownerUsername = ownerUsername;
    }

    public Label(String title, String colorCode, String ownerUsername) {
        this.title = title;
        this.colorCode = colorCode;
        this.ownerUsername = ownerUsername;
    }

    // for db:
    public Label() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { // not needed due to autoGenerate?
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
