package task006.classes;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        MyShop shop = new MyShop();
        Cheque cheque = shop.startBuying("12345");
        RedRose redRose1 = new RedRose(3);
        cheque.addGood(redRose1);
        Shovel shovel1 = new Shovel(2);
        System.out.println(shovel1.demonstrate());
        cheque.addGood(shovel1);
        cheque.viewPrices();
        System.out.println();
        cheque.printCheque();
        shop.askTotalAmount(cheque.getNumber());
        System.out.println("///////////////////////////");
        Cheque cheque1 = shop.startBuying();
        WhiteAster whiteAster = new WhiteAster(5);
        Shovel shovel3 = new Shovel(3);
        WateringCan wateringCan = new WateringCan(2);
        System.out.println(wateringCan.demonstrate());
        cheque1.addGood(whiteAster);
        cheque1.addGood(shovel3);
        cheque1.addGood(wateringCan);
        System.out.println("View prices:");
        cheque1.viewPrices();
        System.out.println();
        System.out.println("View goods:");
        cheque1.viewGoods();
        System.out.println();
        cheque1.getTotalAmount();
        cheque1.printCheque();
        System.out.println(shop.getWorker());
    }
}
