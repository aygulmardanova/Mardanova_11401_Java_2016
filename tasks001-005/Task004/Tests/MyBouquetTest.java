package Task004.Tests;

import Task004.classes.Flower;
import Task004.classes.MyBouquet;
import Task004.classes.RedRose;
import Task004.classes.WhiteAster;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class MyBouquetTest {

    private static final double EPS = 1e-9;
    private MyBouquet mb1;
    private List<Flower> flowers;
    private MyBouquet mb2;
    private MyBouquet mb3;

    @Before
    public void beforeMethod() {
        mb1 = new MyBouquet();
        flowers = new ArrayList<>();
        RedRose rr = mock(RedRose.class);
        when(rr.getPrice()).thenReturn((double) 150);
        when(rr.getDiscount()).thenReturn(0.1);
        when(rr.getCount()).thenReturn(1);
        flowers.add(rr);
        mb2 = new MyBouquet(flowers);
        mb3 = new MyBouquet(flowers, 3);
    }

    @Test
    public void emptyConstructorCreateBouquetWIthEmptyListOfFlowers() {
        Assert.assertEquals(0, mb1.getFlowersCount());
    }

    @Test
    public void constructorCreateBouquetWithFlowersFromList() {
        Assert.assertEquals(1, mb2.getFlowersCount());
    }

    @Test
    public void constructorCreateCountCopiesOfBouquetWithFlowersFromList() {
        Assert.assertEquals(3, mb3.getCount());
    }

    @Test
    public void getNameMethodReturnsTheNameOfBouquet() {
        Assert.assertEquals("A beautiful bouquet", mb3.getName());
    }

    @Test
    public void getPriceMethodShouldCalculateThePriceCorrectlyIfOneRedRose() {
        Assert.assertEquals(135, mb2.getPrice(), EPS);    //150*(1-0.1)
    }

    @Test
    public void getPriceMethodShouldCalculateThePriceCorrectlyIfSeveralFlowers() {
        WhiteAster whiteAster = mock(WhiteAster.class);
        when(whiteAster.getPrice()).thenReturn(120.0);
        when(whiteAster.getDiscount()).thenReturn(0.0);
        mb2.addFlower(whiteAster);
        Assert.assertEquals(255.0, mb2.getPrice(), EPS);
    }

    @Test
    public void getFlowersReturnsCorrectListOfFlowers() {
        Assert.assertEquals(flowers, mb2.getFlowers());
    }

    @Test
    public void getFlowersCountReturnTheCorrectSizeOfListWithFlowers() {
        Assert.assertEquals(1, mb2.getFlowersCount());
    }

    @Test
    public void addFlowerMethodShouldAddFlowerIntoTheListAndIncreaseItsSize() {
        mb2.addFlower(mock(WhiteAster.class));
        Assert.assertEquals(2, mb2.getFlowersCount());
    }

    @Test
    public void getDiscountShouldReturnZeroDiscountIfItWasNotSet() {
        Assert.assertEquals(0, mb2.getDiscount(), EPS);
    }

    @Test
    public void getDiscountShouldReturnChangedDiscount() {
        mb2.setDiscount(0.1);
        Assert.assertEquals(0.1, mb2.getDiscount(), EPS);
    }

    @Test
    public void getCountMethodShouldReturnOneValueIfItWasNotSetInConstructor() {
        Assert.assertEquals(1, mb2.getCount());
    }

    @Test
    public void getCountShouldReturnNonZeroValueIfItWasSentToConstructor() {
        Assert.assertEquals(3, mb3.getCount());
    }

    @Test
    public void setPriceMethodWillLotChangeThePrice() {
        double price = mb2.getPrice();
        mb2.setPrice(200);
        Assert.assertNotEquals(200, price, EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        mb2.setDiscount(0.3);
        Assert.assertEquals(0.3, mb2.getDiscount(), EPS);
    }
}
