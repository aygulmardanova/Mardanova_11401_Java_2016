package task010.tests;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


/**
 * Created by Айгуль on 24.02.2016.
 */
public class MyBouquetTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;

    private MyBouquet mb;
    private List<Flower> flowers;
    private static MyBouquet myBouquet;
    private static MyBouquet mb2;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        myBouquet = (MyBouquet) ac.getBean("mybouquet");   //bouquet with the list of flowers

        mb2 = (MyBouquet) ac.getBean("mb2");    //bouquet with entered flowers' list and count

    }

    @Before
    public void beforeMethod() {

        mb = (MyBouquet) ac.getBean("mb");    //empty bouquet

    }

    @Test
    public void emptyConstructorCreateBouquetWIthEmptyListOfFlowers() {
        Assert.assertEquals(0, mb.getFlowersCount());
    }

    @Test
    public void constructorCreateCountCopiesOfBouquetWithFlowersFromList() {
        Assert.assertEquals(2, mb2.getCount());
    }

    @Test
    public void getNameMethodReturnsTheNameOfBouquet() {
        Assert.assertEquals("A beautiful bouquet", myBouquet.getName());
    }
    @Test
    public void getPriceMethodShouldCalculateThePriceCorrectlyIfEmptyBouquet() {
        Assert.assertEquals(0.0, mb.getPrice(), EPS);
    }

    @Test
    public void getPriceMethodShouldCalculateThePriceCorrectlyIfSeveralFlowers() {
        Assert.assertEquals(408, myBouquet.getPrice(), EPS);    //135+120+153(redrose, whiteaster, yellowrose)
    }

    @Test
    public void getFlowersCountReturnTheCorrectSizeOfListForEmptyBouquet() {
        Assert.assertEquals(0, mb.getFlowersCount());
    }

    @Test
    public void getFlowersCountReturnTheCorrectSizeOfListWithFlowers() {
        Assert.assertEquals(3, myBouquet.getFlowersCount());
    }

    @Test
    public void addFlowerMethodShouldAddFlowerIntoTheListAndIncreaseItsSize() {
        mb.addFlower((Flower) ac.getBean("redrose"));
        Assert.assertEquals(1, mb.getFlowersCount());
    }

    @Test
    public void getDiscountShouldReturnZeroDiscountIfItWasNotSet() {
        Assert.assertEquals(0, mb.getDiscount(), EPS);
    }

    @Test
    public void getDiscountShouldReturnChangedDiscount() {
        mb.setDiscount(0.1);
        Assert.assertEquals(0.1, mb.getDiscount(), EPS);
    }

    @Test
    public void getCountMethodShouldReturnOneValueIfItWasNotSetInConstructor() {
        Assert.assertEquals(1, myBouquet.getCount());
    }

    @Test
    public void getCountShouldReturnNonZeroValueIfItWasSentToConstructor() {
        Assert.assertEquals(2, mb2.getCount());
    }

    @Test
    public void setPriceMethodWillNotChangeThePrice() {
        double price = mb.getPrice();
        mb.setPrice(200);
        Assert.assertNotEquals(200, price, EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        mb.setDiscount(0.3);
        Assert.assertEquals(0.3, mb.getDiscount(), EPS);
    }
}
