package ru.kpfu.itis.aygul.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Айгуль on 17.04.2016.
 */
@Entity
public class Subscription {
    private int id;
    private int validity;
    private Integer price;
    private List<Purchase> purchases;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "validity")
    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "subscription", targetEntity = Purchase.class)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (validity != that.validity) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + validity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
