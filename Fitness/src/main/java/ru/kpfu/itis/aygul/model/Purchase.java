package ru.kpfu.itis.aygul.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class Purchase {
    private int id;
    private Date buyDate;
    private Integer prolong;
    private Subscription subscription;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_id_seq")
    @SequenceGenerator(name = "purchase_id_seq", sequenceName = "purchase_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "buy_date")
    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Basic
    @Column(name = "prolong")
    public Integer getProlong() {
        return prolong;
    }

    public void setProlong(Integer prolong) {
        this.prolong = prolong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (id != purchase.id) return false;
        if (buyDate != null ? !buyDate.equals(purchase.buyDate) : purchase.buyDate != null) return false;
        if (prolong != null ? !prolong.equals(purchase.prolong) : purchase.prolong != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (buyDate != null ? buyDate.hashCode() : 0);
        result = 31 * result + (prolong != null ? prolong.hashCode() : 0);
        return result;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Subscription.class)
    @JoinColumn(name = "subscr_id", referencedColumnName = "id", nullable = false)
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", buyDate=" + buyDate +
                ", prolong=" + prolong +
                ", subscription=" + subscription +
                ", user=" + user.getName() + " " + user.getSurname() +
                '}';
    }
}
