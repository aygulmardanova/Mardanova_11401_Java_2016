package ru.kpfu.itis.aygul.model;

import javax.persistence.*;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class Level {
    private int id;
    private String levelName;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "level_id_seq")
    @SequenceGenerator(name="level_id_seq", sequenceName = "level_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "level_name")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Level level = (Level) o;

        if (id != level.id) return false;
        if (levelName != null ? !levelName.equals(level.levelName) : level.levelName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (levelName != null ? levelName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
