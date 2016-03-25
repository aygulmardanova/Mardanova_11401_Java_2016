package task007.classes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Айгуль on 21.02.2016.
 */

@Component
public class WateringCan implements Accessory, Selling {

    private double price;
    private double discount;

    @Value("2")
    private int count;

    public WateringCan() {
        this.price = 250;
        this.discount = 0;
        this.count = 1;
    }

    public WateringCan (int count) {
        this.price = 250;
        this.discount = 0;
        this.count = count;
    }

    @Override
    public String getMainOption() {
        return "watering";
    }

    @Override
    public String demonstrate() {
        return "I'm a watering can. \n       I can water your plants, flowers, so they won't need water. "
                + "I won't let them dry!";
    }

    @Override
    public boolean equalsTypes(Accessory a) {
        return getMainOption().equals(a.getMainOption());
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(double newDiscount) {
        this.discount = newDiscount;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getName() {
        return "Watering Can";
    }

    @Override
    public String toString() {
        return "WateringCan{" +
                "price=" + price +
                ", discount=" + discount +
                ", count=" + count +
                '}';
    }

}
