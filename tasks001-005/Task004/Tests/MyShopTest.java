package Tests;

import classes.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class MyShopTest {

    private static final double EPS = 1e-9;
    private MyShop myShop;
    private Worker worker;

    @Before
    public void beforeMethod() {

        myShop = new MyShop();

        worker = mock(Worker1.class);

        when(worker.openCheque()).thenReturn(new CashCheque(1));
        when(worker.openCheque("12345")).thenReturn(new CardCheque("12345", 1));
        when(worker.totalAmount(1)).thenReturn(300.0);
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
    public void setWorkerMethodShouldChangeTheWorker() {
        Worker w1 = myShop.getWorker();
        myShop.setWorker(worker);
        Assert.assertNotEquals(w1, worker);
    }

    @Test
    public void startBuyingMethodShouldReturnCardChequeIfCardNumberWasEntered() {
        myShop.setWorker(worker);
        Assert.assertTrue(myShop.startBuying("12345") instanceof CardCheque);
    }

    @Test
    public void startBuyingMethodShouldReturnCashChequeIfMethodWasCalledWithoutParameters() {
        myShop.setWorker(worker);
        Assert.assertTrue(myShop.startBuying() instanceof CashCheque);
    }

    @Test
    public void askTotalAmountCallTheSayTotalAmountMethodValuesShouldBeSame() {
        myShop.setWorker(worker);
        Assert.assertEquals(myShop.sayTotalAmount(1), myShop.askTotalAmount(1), EPS);
    }

    @Test
    public void sayTotalAmountMethodShouldReturnSumValueCorrectlyByChequeNumber() {
        myShop.setWorker(worker);
        Assert.assertEquals(300.0, myShop.sayTotalAmount(1), EPS);
    }

}
