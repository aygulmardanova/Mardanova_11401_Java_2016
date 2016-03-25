package task010.tests;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class MyShopTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static MyShop myShop;
    private static Worker worker;
    private static CardCheque cc;

    @BeforeClass
    public static void beforeClassMethod() {

        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        cc = (CardCheque) ac.getBean("cardcheque");
        cc.addGood(ac.getBean("redrose"));

        myShop = (MyShop) ac.getBean("myshop");

        worker = (Worker) ac.getBean("worker1");
        worker.addCheque(cc);
    }

    @Before
    public void beforeMethod() {

    }

    @AfterClass
    public static void afterClassMethod() {
        worker.clearCheques();
    }

    @Test
    public void getWorkerShouldReturnTheObjectOfWorker1Class() {
        Assert.assertTrue(myShop.getWorker() instanceof Worker1);
    }

    @Test
    public void getNameMethodShouldReturnCorrectNameOfTheShop() {
        Assert.assertEquals("Best Shop in London", myShop.getName());
    }

    @Test
    public void getAddressShouldReturnAddressOfMyShop() {
        Assert.assertEquals("England, London", myShop.getAddress());
    }


    @Test
    public void startBuyingMethodShouldReturnCardChequeIfCardNumberWasEntered() {
        Assert.assertTrue(myShop.startBuying("12345") instanceof CardCheque);
    }

    @Test
    public void startBuyingMethodShouldReturnCashChequeIfMethodWasCalledWithoutParameters() {
        Assert.assertTrue(myShop.startBuying() instanceof CashCheque);
    }

    @Test
    public void askTotalAmountCallTheSayTotalAmountMethodValuesShouldBeSame() {
        Assert.assertEquals(myShop.sayTotalAmount(1), myShop.askTotalAmount(1), EPS);
    }

    @Test
    public void sayTotalAmountMethodShouldReturnSumValueCorrectlyByChequeNumber() {
        Assert.assertEquals(cc.getTotalAmount(), myShop.sayTotalAmount(1), EPS);
    }

}
