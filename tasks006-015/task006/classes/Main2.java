package task006.classes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Айгуль on 17.03.2016.
 */
public class Main2 {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("task006/spring-config6.xml");
        MyShop myShop = (MyShop) ac.getBean("myshop");

        RedRose redRose = (RedRose) ac.getBean("redrose");
        WateringCan wc = (WateringCan) ac.getBean("wateringcan");
        WhiteAster wa = (WhiteAster) ac.getBean("whiteaster");
        Hoe hoe = (Hoe) ac.getBean("hoe");
        MyBouquet mb = (MyBouquet) ac.getBean("mybouquet");

        Cheque cardCheque = (CardCheque) ac.getBean("cardcheque");

        Worker w1 = (Worker1) ac.getBean("worker1");
        w1.addCheque(cardCheque);

        cardCheque.addGood(redRose);
        cardCheque.addGood(wc);
        cardCheque.addGood(wa);
        cardCheque.printCheque();

        System.out.println("Worker " + w1.getName() + " has " + w1.chequesCount()+ " cheques");
        System.out.println("The cheque's total amount is " + w1.totalAmount(cardCheque.getNumber()));

        System.out.println(myShop.getWorker());
        System.out.println(w1.equals(myShop.getWorker()));

        Cheque cashCheque = (CashCheque) ac.getBean("cashcheque");
        cashCheque.addGood(hoe);
        cashCheque.addGood(mb);

        cashCheque.printCheque();

        System.out.println(mb);
    }
}
