package task010.tests;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;


/**
 * Created by Айгуль on 24.02.2016.
 */
public class Worker1Test {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static Worker worker1;
    private static Cheque cardCheque;
    private static Cheque cashCheque;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        worker1 = (Worker1) ac.getBean("worker1");

        cardCheque = (CardCheque) ac.getBean("cardcheque");
        cashCheque = (CashCheque) ac.getBean("cashcheque");
        cashCheque.addGood(ac.getBean("redrose"));

    }

    @After
    public void afterMethod() {
        worker1.clearCheques();
    }

    @Test
    public void openChequeShouldIncreaseChequesNumberToOne() {
        int count = worker1.chequesCount();
        worker1.openCheque();
        Assert.assertEquals(count + 1, worker1.chequesCount());
    }

    @Test
    public void addChequeShouldIncreaseChequesNumberToOne() {
        worker1.openCheque(String.valueOf(cardCheque.getNumber()));
        int count1 = worker1.chequesCount();
        worker1.addCheque(cashCheque);
        Assert.assertEquals(count1 + 1, worker1.chequesCount());
    }

    @Test
    public void totalAmountMethodShouldCorrectlyCalculateTheAmountOfPurchases() {
        worker1.addCheque(cashCheque);
        Assert.assertEquals(cashCheque.getTotalAmount(), worker1.totalAmount(worker1.chequesCount()), EPS);
    }

    @Test
    public void clearChequesShouldClearTheLIstWithCheques() {
        worker1.addCheque(cashCheque);
        worker1.clearCheques();
        Assert.assertEquals(0, worker1.chequesCount());
    }

    @Test
    public void chequesCountMethodReturnsCorrectNumberOfChequesForTheWorkerIfEmpty() {
        Assert.assertEquals(0, worker1.chequesCount());
    }

    @Test
    public void chequesCountMethodReturnsCorrectNumberOfChequesForTheWorkerIfSeveral() {
        worker1.addCheque(cashCheque);
        worker1.addCheque(cashCheque);
        Assert.assertEquals(2, worker1.chequesCount());
    }

    @Test
    public void getTotalAmountReturnsAmountForCertainCheque() {
        worker1.addCheque(cashCheque);
        Assert.assertEquals(cashCheque.getTotalAmount(), worker1.totalAmount(1), EPS);
    }

    @Test
    public void openChequeMethodShouldReturnCashChequeMethodWasCalledWithoutParameters() {
        Cheque cashCheque = worker1.openCheque();
        Assert.assertTrue(cashCheque instanceof CashCheque);
    }

    @Test
    public void openChequeMethodShouldReturnCardChequeIfEnteredCardPaymentType() {
        Cheque cardCheque = worker1.openCheque("12345");
        Assert.assertTrue(cardCheque instanceof CardCheque);
    }

    @Test
    public void getNameMethodShouldReturnCorrectNameIvan() {
        Assert.assertEquals("Ivan", worker1.getName());
    }

    @Test
    public void getWorkerReturnsTheSameWorkerEveryTime() {
        Assert.assertEquals(worker1, Worker1.getWorker());
    }
}
