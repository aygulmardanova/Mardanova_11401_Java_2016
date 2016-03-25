package task010.tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class CardChequeTest {

    private static final double EPS = 1e-9;

    private static ApplicationContext ac;

    private CardCheque cc;
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
        cc = new CardCheque("12345", 1);
    }


    @Test
    public void constructorShouldSaveFirstParameterAsCardNumber() {
        Assert.assertEquals("12345", cc.getCardNumber());
    }

    @Test
    public void constructorShouldSaveSecondParameterAsNumber() {
        Assert.assertEquals(1, cc.getNumber());
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
    public void getTotalAmountMethodShouldReturnCorrectSumOfPurchasesIfOne() {
        cc.addGood(shovel);
        Assert.assertEquals(240, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getTotalAmountCalculateSumCorrectlyForSeveralPurchases() {
        cc.addGood(shovel);
        cc.addGood(wateringCan);
        Assert.assertEquals(490, cc.getTotalAmount(), EPS);
    }

    @Test
    public void getPaymentTypeReturnsCorrectCardType() {
        Assert.assertEquals("card", cc.getPaymentType());
    }
}
