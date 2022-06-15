package nl.hhs.apep2122group1.models;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
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

    // for demo // TODO: remove when ready
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

    public String getOwnerUsername() {
        return ownerUsername;
    }

    @Override
    public String toString() {
        return title;
    }
}
