package task010.classes;

/**
 * Created by Айгуль on 21.02.2016.
 */
public class Shovel implements Accessory, Selling {

    private double price;
    private double discount;
    private int count;

    public Shovel() {
        this.price = 300;
        this.discount = 0.2;
        this.count = 1;
    }

    public Shovel(int count) {
        this.price = 300;
        this.discount = 0.2;
        this.count = count;
    }

    @Override
    public String getMainOption() {
        return "digging";
    }

    @Override
    public String demonstrate() {
        return "I'm a shovel. \n        I'm very useful in the garden! I can dig the ground. " +
                "Buy me and you won't regret!";
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
        return "Shovel";
    }

}
