package ru.kpfu.itis.aygul.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_id_seq")
    @SequenceGenerator(name = "purchase_id_seq", sequenceName = "purchase_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "buy_date")
    private Date buyDate;

    @Basic
    @Column(name = "prolong")
    private Integer prolong;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Subscription.class)
    @JoinColumn(name = "subscr_id", referencedColumnName = "id", nullable = false)
    private Subscription subscription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getProlong() {
        return prolong;
    }

    public void setProlong(Integer prolong) {
        this.prolong = prolong;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
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
