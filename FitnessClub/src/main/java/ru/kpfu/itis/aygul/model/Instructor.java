package ru.kpfu.itis.aygul.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "Instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "instructor_id_seq")
    @SequenceGenerator(name="instructor_id_seq", sequenceName = "instructor_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "qualification")
    private String qualification;

    @Basic
    @Column(name = "awards")
    private String awards;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @Basic
    @Column(name = "experience")
    private Date experience;

    @OneToMany(mappedBy = "instructor")
    private List<Schedule> schedules;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExperience() {
        return experience;
    }

    public void setExperience(Date experience) {
        this.experience = experience;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
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


