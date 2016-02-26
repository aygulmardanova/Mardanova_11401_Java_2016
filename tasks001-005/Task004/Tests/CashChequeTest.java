package Task004.Tests;

import Task004.classes.CashCheque;
import Task004.classes.Shovel;
import Task004.classes.WateringCan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class CashChequeTest {

    private static final double EPS = 1e-9;
    private CashCheque cc;
    private static Shovel shovel;
    private static WateringCan wateringCan;

    @BeforeClass
    public static void beforeClassMethod() {

        shovel = mock(Shovel.class);
        when(shovel.getPrice()).thenReturn(100.0);
        when(shovel.getCount()).thenReturn(1);
        when(shovel.getDiscount()).thenReturn(0.0);

        wateringCan = mock(WateringCan.class);
        when(wateringCan.getPrice()).thenReturn(250.0);
        when(wateringCan.getDiscount()).thenReturn(0.2);
        when(wateringCan.getCount()).thenReturn(2);
    }

    @Before
    public void beforeMethod() {
        cc = new CashCheque(1);
    }

    @Test
    public void constructorShouldSaveParameterAsNumber() {
        Assert.assertEquals(1, cc.getNumber());
    }

    @Test
    public void addGoodMethodShouldIncreaseSizeOfTwoListsOfPricesAndGoods() {
        int s1 = cc.getGoods().size();
        int s2 = cc.getPrices().size();
        cc.addGood(shovel);
        Assert.assertEquals(s1 + 1, cc.getGoods().size());
        Assert.assertEquals(s2 + 1, cc.getPrices().size());
    }

    @Test
    public void getTotalAmountMethodShouldCalculateSumCorrectlyForOnePurchase() {
        cc.addGood(shovel);
        Assert.assertEquals(100, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getTotalAmountMethodShouldCalculateSumCorrectlyForSeveralPurchases() {
        cc.addGood(shovel);
        cc.addGood(wateringCan);
        Assert.assertEquals(500, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getPaymentTypeReturnsCorrectType() {
        Assert.assertEquals("cash", cc.getPaymentType());
    }

}