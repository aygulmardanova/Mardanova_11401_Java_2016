package task007.classes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import task007.JavaConfig;

/**
 * Created by Айгуль on 18.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);

        MyShop myShop = ac.getBean(MyShop.class);
        Worker w1 = myShop.getWorker();
        System.out.println(w1);

        MyBouquet mb = ac.getBean(MyBouquet.class);
        System.out.println(mb);

        RedRose rr = ac.getBean(RedRose.class);

        Cheque cardCheque = ac.getBean(CardCheque.class);
//        Cheque cardCheque = (Cheque) ac.getBean("cardcheque");
        w1.addCheque(cardCheque);
        cardCheque.addGood(mb);
        cardCheque.addGood(rr);
        cardCheque.printCheque();

        System.out.println();
        myShop.askTotalAmount(cardCheque.getNumber());

        System.out.println();
        System.out.println("View prices:");
        cardCheque.viewPrices();
        System.out.println();
        System.out.println("View goods:");
        cardCheque.viewGoods();

        System.out.println();

        Shovel sh = ac.getBean(Shovel.class);
        System.out.println(sh.demonstrate());
        System.out.println("Count: " + sh.getCount() + " shovels");

        System.out.println();

        WateringCan wc = (WateringCan) ac.getBean("wateringCan");
        System.out.println(wc);

    }
}
