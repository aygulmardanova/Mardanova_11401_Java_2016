package Tests;

import classes.*;
import org.junit.*;
import sun.print.PSPrinterJob;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class Worker1Test {

    private static final double EPS = 1e-9;
    private Worker1 worker1;
    private static Cheque cashCheque;

    @BeforeClass
    public static void beforeClassMethod() {
        cashCheque = mock(CashCheque.class);
        when(cashCheque.getNumber()).thenReturn(1);
        when(cashCheque.getPaymentType()).thenReturn("cash");
        when(cashCheque.getTotalAmount()).thenReturn(300.0);
    }

    @Before
    public void beforeMethod() {
        worker1 = Worker1.getWorker();
    }

    @After
    public void afterMethod() {
        worker1.clearCheques();
    }

    @Test
    public void addChequeIncreaseChequesNumberToOne() {
        worker1.openCheque();
        int count1 = worker1.chequesCount();
        worker1.addCheque(cashCheque);
        Assert.assertEquals(count1 + 1, worker1.chequesCount());
    }

    @Test
    public void totalAmountMethodShouldCorrectlyCalculateTheAmountOfPurchases() {
        worker1.addCheque(cashCheque);
        Assert.assertEquals(300.0, worker1.totalAmount(worker1.chequesCount()), EPS);
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
        Assert.assertEquals(300.0, worker1.totalAmount(1), EPS);
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
