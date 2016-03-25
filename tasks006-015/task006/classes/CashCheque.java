package task006.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 20.02.2016.
 */
public class CashCheque implements Cheque {

    List<Subject> goods;
    List<Selling> prices;
    int number;

    public List<Subject> getGoods() {
        return goods;
    }

    public List<Selling> getPrices() {
        return prices;
    }


    public int getNumber() {
        return number;
    }

    public CashCheque(int number) {
        this.goods = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.number = number;
    }

    @Override
    public void addGood(Object good) {
        Subject subjectGood = (Subject) good;    //чтобы получить название товара
        Selling sellingGood = (Selling) good;    //чтобы получить цену, скиду и количество товара
        goods.add(subjectGood);
        prices.add(sellingGood);
    }

    @Override
    public void viewGoods() {
        for (Subject good : goods) {
            System.out.println(good.getName());
        }
    }


    @Override
    public void viewPrices() {
        for (int i = 0; i < goods.size(); i++) {
            System.out.println(goods.get(i).getName() + " - " + prices.get(i).getPrice());
        }
    }

    @Override
    public double getTotalAmount() {
        double price = 0;
        for (Selling p : prices) {
            price += p.getCount() * p.getPrice() * (1 - p.getDiscount());
        }
        return price;
    }

    @Override
    public String getPaymentType() {
        return "cash";
    }

    @Override
    public void printCheque() {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("You are paying by cash");
        System.out.println("Your purchases:");
        for (int i = 0; i < goods.size(); i++) {
            System.out.println(goods.get(i).getName() + " - " + prices.get(i).getCount() + "pcs "
                    + prices.get(i).getPrice() + " -" + prices.get(i).getDiscount() * 100 + "% "
                    + prices.get(i).getCount() * prices.get(i).getPrice() * (1 - prices.get(i).getDiscount()));
        }
        System.out.println("Total:                  " + getTotalAmount());
        System.out.println("-----------------------------------");
    }

}
