package ru.kpfu.itis.aygul.model;

import ru.kpfu.itis.aygul.model.enums.WeekDay;

import javax.persistence.*;


@Entity
@Table(name = "Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_id_seq")
    @SequenceGenerator(name = "schedule_id_seq", sequenceName = "schedule_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "\"startTime\"")
    private int startTime;

    @Basic
    @Column(name = "\"dayOfWeek\"")
    @Enumerated(EnumType.STRING)
    private WeekDay dayOfWeek;

    @ManyToOne(targetEntity = ClassEntity.class)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity classByClassId;

    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "instr_id", referencedColumnName = "id")
    private Instructor instructor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public WeekDay getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(WeekDay dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ClassEntity getClassByClassId() {
        return classByClassId;
    }

    public void setClassByClassId(ClassEntity classByClassId) {
        this.classByClassId = classByClassId;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
