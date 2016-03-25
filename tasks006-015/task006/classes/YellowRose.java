package task006.classes;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class YellowRose implements Flower {

    private double price;
    private double discount;
    private int count;

    public YellowRose() {
        this.price = 170;
        this.discount = 0.1;
        this.count = 1;
    }

    public YellowRose(int count) {
        this.price = 170;
        this.discount = 0.1;
        this.count = count;
    }
    @Override
    public String getDescription() {
        return "Extremely beautiful flower with pleasant fragrance";
    }

    @Override
    public String getColor() {
        return "yellow";
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

    @Override
    public String getName() {
        return "Yellow Rose";
    }

    @Override
    public String toString() {
        return "YellowRose{" +
                "price=" + price +
                ", discount=" + discount +
                ", count=" + count +
                '}';
    }
}
