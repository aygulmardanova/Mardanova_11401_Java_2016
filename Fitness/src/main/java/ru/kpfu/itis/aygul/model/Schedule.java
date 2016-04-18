package ru.kpfu.itis.aygul.model;

import javax.persistence.*;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class Schedule {
    private int id;
    private int startTime;
    private String dayOfWeek;
    private Class classByClassId;
    private Instructor instructor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "startTime")
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "dayOfWeek")
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (id != schedule.id) return false;
        if (startTime != schedule.startTime) return false;
        if (dayOfWeek != null ? !dayOfWeek.equals(schedule.dayOfWeek) : schedule.dayOfWeek != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + startTime;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return result;
    }

    @ManyToOne(targetEntity = Class.class)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    public Class getClassByClassId() {
        return classByClassId;
    }

    public void setClassByClassId(Class classByClassId) {
        this.classByClassId = classByClassId;
    }

    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "instr_id", referencedColumnName = "id")
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                " name =" + classByClassId.getName() +
                ", instructor=" + instructor.getUser().getName() + " " + instructor.getUser().getSurname() +
                ", startTime=" + startTime +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                '}';
    }
}
