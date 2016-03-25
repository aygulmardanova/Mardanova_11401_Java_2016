package task007.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by Айгуль on 17.02.2016.
 */

public class MyBouquet implements Bouquet, Selling {

    @Autowired
    List<Flower> flowers;

    @Value("#{mybouquet.checkPrice()}")
    private double price;

    private double discount;
    private int count;

    public MyBouquet(){
        //this.price = 0;
        //this.flowers = new ArrayList<>();
        this.discount = 0;
        this.count = 1;
    }

    public MyBouquet(List<Flower> flowers) {
        for (Flower flower : flowers) {
            price += flower.getPrice() * (1 - flower.getDiscount());
        }
        this.flowers = flowers;
        this.discount = 0;
        this.count = 1;
    }

    public double checkPrice() {
        if (flowers != null) {
            for (Flower flower : flowers) {
                price += flower.getPrice() * (1 - flower.getDiscount());
            }
        }
        return price;
    }

    public MyBouquet(List<Flower> flowers, int count) {
        this(flowers);
        this.count = count;
    }

    @Override
    public String getName() {
        return "A beautiful bouquet";
    }

    @Override
    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void addFlower(Flower flower) {
        flowers.add(flower);
        price += flower.getPrice() * (1 - flower.getDiscount());
    }

    @Override
    public int getFlowersCount() {
        return flowers.size();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double newPrice) {
        System.out.println("You can only change flowers' price");
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
        return "MyBouquet{" +
                "price=" + price +
                ", flowers=" + flowers +
                ", discount=" + discount +
                ", count=" + count +
                '}';
    }
}
