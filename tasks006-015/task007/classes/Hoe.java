package task007.classes;

import org.springframework.stereotype.Component;

/**
 * Created by Айгуль on 26.02.2016.
 */

@Component
public class Hoe implements Accessory, Selling {

    private double price;
    private double discount;
    private int count;

    public Hoe() {
        this.price = 250;
        this.discount = 0.1;
        this.count = 1;
    }

    public Hoe(int count) {
        this.price = 250;
        this.discount = 0.1;
        this.count = count;
    }

    @Override
    public String getMainOption() {
        return "digging";
    }

    @Override
    public String demonstrate() {
        return "I'm a hoe. \n        I'm very useful in the garden! I can hoe the ground. " +
                "This can help you before digging the soil. Buy me and you won't regret!";
    }

    @Override
    public boolean equalsTypes(Accessory a) {
        //return "digging".equals(a.getMainOption());
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
        return "Hoe";
    }
}
