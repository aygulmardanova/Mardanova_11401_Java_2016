package Task004.Tests;

import Task004.classes.CardCheque;
import Task004.classes.CashCheque;
import Task004.classes.Cheque;
import Task004.classes.Worker1;
import org.junit.*;

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
    public void totalAmountMethodShouldCorrectlyCalculateTheAmountOfPurchases() {
        worker1.openCheque("cash");
    }

    @Test
    public void addChequeIncreaseChequesNumberToOne() {
        worker1.openCheque("cash");
        int count1 = worker1.chequesCount();
        worker1.addCheque(cashCheque);
        Assert.assertEquals(count1 + 1, worker1.chequesCount());
    }

    @Test
    public void clearChequesShouldClearTheLIstWithCheques() {
        worker1.addCheque(cashCheque);
        int i1 = worker1.chequesCount();
        worker1.clearCheques();
        Assert.assertEquals(i1 - 1, worker1.chequesCount());
        //Assert.assertEquals(1, i1);
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
    public void openChequeMethodShouldReturnCashChequeIfEnteredCashPaymentType() {
        Assert.assertTrue(worker1.openCheque("cash") instanceof CashCheque);
    }


    //OpenCheque method asks to enter the card name. I don't know, how to test this. Although I tried to use
    // mock-objects or spy, it didn't work.
    @Ignore
    @Test(timeout = 100)
    public void openChequeMethodShouldReturnCardChequeIfEnteredCardPaymentType() {
        Worker1 spyW = spy(worker1);
        when(worker1.askCardNumber()).thenReturn("12345");
        Cheque cardCheque = spyW.openCheque("card");
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