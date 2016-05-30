package ru.kpfu.itis.aygul.model.api;

import ru.kpfu.itis.aygul.model.ClassEntity;

/**
 * Created by aygulmardanova on 28.05.16.
 */
public class ClassClient {

    private int id;

    private String name;

    private String description;

    public ClassClient(ClassEntity classEntity) {
        this(classEntity.getId(), classEntity.getName(), classEntity.getDescription());
    }

    private ClassClient(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClassClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
