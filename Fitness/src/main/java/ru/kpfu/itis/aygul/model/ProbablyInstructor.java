package ru.kpfu.itis.aygul.model;

import javax.persistence.*;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class ProbablyInstructor {
    private int id;
    private User user;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "pi_id_seq")
    @SequenceGenerator(name="pi_id_seq", sequenceName = "probablyinstructor_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProbablyInstructor that = (ProbablyInstructor) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ProbablyInstructor{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
