package task007.classes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Айгуль on 20.02.2016.
 */

public class CardCheque implements Cheque {

    public List<Subject> getGoods() {
        return goods;
    }

    public List<Selling> getPrices() {
        return prices;
    }

    List<Subject> goods;
    List<Selling> prices;

    public String getCardNumber() {
        return cardNumber;
    }

    String cardNumber;
    int number;

    public int getNumber() {
        return number;
    }

    public CardCheque(String cardNumber, int number) {
        this.goods = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.cardNumber = cardNumber;
        this.number = number;
    }

    @Override
    public void addGood(Object good) {
        Subject subjectGood = (Subject) good;    //чтобы получить название товара
        Selling sellingGood = (Selling) good;    //чтобы получить цену, скидку и количество товара
        goods.add(subjectGood);
        prices.add(sellingGood);
    }

    @Override
    public void viewGoods() {
        System.out.println("Your card number is: " + cardNumber);
        for (Subject good : goods) {
            System.out.println(good.getName());
        }
    }

    @Override
    public void viewPrices() {
        System.out.println("Your card number is: " + cardNumber);
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
        return "card";
    }

    @Override
    public void printCheque() {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("You are paying by card");
        System.out.println("Your payment card's number is: " + cardNumber);
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
