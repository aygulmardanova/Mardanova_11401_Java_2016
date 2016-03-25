package task010.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public class RedRose implements Flower {

    private double price;
    private double discount;
    private int count;

    public RedRose() {
        this.price = 150;
        this.discount = 0.1;
        this.count = 1;
    }

    public RedRose(int count) {
        this.price = 150;
        this.discount = 0.1;
        this.count = count;
    }

    @Override
    public String getName() {
        return "Red Rose";
    }

    @Override
    public String getDescription() {
        return "Extremely beautiful flower with pleasant fragrance";
    }

    @Override
    public String getColor() {
        return "red";
    }

    @Override
    public String getType() {
        return "rose";
    }

    @Override
    public boolean equalsType(Flower f) {
        return getType().equals(f.getType());
    }

    @Override
    public boolean equals(Flower f) {
        return (getType().equals(f.getType()) & getColor().equals(f.getColor()));
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

}
