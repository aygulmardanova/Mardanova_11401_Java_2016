package task007.classes;

/**
 * Created by Айгуль on 17.02.2016.
 */
public class WhiteAster implements Flower {

    private double price;
    private double discount;
    private int count;

    public WhiteAster() {
        this.price = 120;
        this.discount = 0;
        this.count = 1;
    }

    public WhiteAster(int count) {
        this.price = 120;
        this.discount = 0;
        this.count = count;
    }

    @Override
    public String getName() {
        return "White Aster";
    }

    @Override
    public String getDescription() {
        return "A delicate flower with light fragrance";
    }

    @Override
    public String getColor() {
        return "white";
    }

    @Override
    public String getType() {
        return "aster";
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
    public String toString() {
        return "WhiteAster{" +
                "price=" + price +
                ", discount=" + discount +
                ", count=" + count +
                '}';
    }
}
