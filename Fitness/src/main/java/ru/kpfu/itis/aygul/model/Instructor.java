package ru.kpfu.itis.aygul.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class Instructor {
    private int id;
    private String description;
    private String qualification;
    private String awards;
    private Date experience;
    private Collection<Schedule> schedules;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "instructor_id_seq")
    @SequenceGenerator(name="instructor_id_seq", sequenceName = "instructor_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "qualification")
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Basic
    @Column(name = "awards")
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    @Basic
    @Column(name = "experience")
    public Date getExperience() {
        return experience;
    }

    public void setExperience(Date experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instructor that = (Instructor) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (qualification != null ? !qualification.equals(that.qualification) : that.qualification != null)
            return false;
        if (awards != null ? !awards.equals(that.awards) : that.awards != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (qualification != null ? qualification.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "instructor")
    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name=" + user.getName() +
                ", surname=" + user.getSurname() + '\'' +
                ", description='" + description + '\'' +
                ", qualification='" + qualification + '\'' +
                ", awards='" + awards + '\'' +
                '}';
    }
}
