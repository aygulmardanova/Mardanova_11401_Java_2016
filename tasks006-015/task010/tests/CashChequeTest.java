package task010.tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class CashChequeTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private CashCheque cc;
    private static Shovel shovel;
    private static WateringCan wateringCan;

    @BeforeClass
    public static void beforeClassMethod() {

        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        shovel = (Shovel) ac.getBean("shovel");

        wateringCan = (WateringCan) ac.getBean("wateringcan");
    }

    @Before
    public void beforeMethod() {
        cc = (CashCheque) ac.getBean("cashcheque");
    }

    @Test
    public void constructorShouldSaveParameterAsNumber() {
        Assert.assertEquals(2, cc.getNumber());
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
    public void addGoodMethodShouldIncreaseSizeOfGoodsList() {
        int s = cc.getGoods().size();
        cc.addGood(shovel);
        Assert.assertEquals(s + 1, cc.getGoods().size());
    }

    @Test
    public void addGoodMethodShouldIncreaseSizeOfPricesList() {
        int s = cc.getPrices().size();
        cc.addGood(shovel);
        Assert.assertEquals(s + 1, cc.getPrices().size());
    }

    @Test
    public void getTotalAmountMethodShouldCalculateSumCorrectlyForOnePurchase() {
        cc.addGood(shovel);
        Assert.assertEquals(240, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getTotalAmountMethodShouldCalculateSumCorrectlyForSeveralPurchases() {
        cc.addGood(shovel);
        cc.addGood(wateringCan);
        Assert.assertEquals(490, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getPaymentTypeReturnsCorrectType() {
        Assert.assertEquals("cash", cc.getPaymentType());
    }

}
