package nl.hhs.apep2122group1.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Label {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String colorCode;

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
}
