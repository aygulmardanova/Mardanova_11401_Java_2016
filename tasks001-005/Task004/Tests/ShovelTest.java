package Task004.Tests;

import Task004.classes.Hoe;
import Task004.classes.Shovel;
import Task004.classes.WateringCan;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class ShovelTest {

    private static final double EPS = 1e-9;
    private static Shovel shovel;

    @BeforeClass
    public static void beforeClassMethod() {
        shovel = new Shovel();
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, shovel.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Shovel shovel = new Shovel(3);
        Assert.assertEquals(3, shovel.getCount());
    }

    @Test
    public void getNameMethodShouldReturnCorrectNameShovel() {
        Assert.assertEquals("Shovel", shovel.getName());
    }

    @Test
    public void demonstrateMethodShouldDemonstrateTheShovel() {
        Assert.assertEquals("I'm a shovel. \n        I'm very useful in the garden! I can dig the ground. " +
                "Buy me and you won't regret!", shovel.demonstrate());
    }

    @Test
    public void getPriceMethodShouldReturnTheCorrectPriceOfShovel() {
        Assert.assertEquals(300, shovel.getPrice(), EPS);
    }

    @Test
    public void getDiscountMethodShouldReturnTheCorrectDiscountValueForShovel() {
        Assert.assertEquals(0.2, shovel.getDiscount(), EPS);
    }


    @Test
    public void getMainOptionShouldReturnDigging() {
        String mainOption = shovel.getMainOption();
        Assert.assertEquals("digging", mainOption);
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameMainOptions() {
        Hoe hoe = mock(Hoe.class);
        when(hoe.getMainOption()).thenReturn("digging");
        Assert.assertTrue(shovel.equalsTypes(hoe));
    }

    @Test
    public void equalsTypesShouldReturnFalseIfDifferentMainOptions() {
        WateringCan wateringCan = mock(WateringCan.class);
        when(wateringCan.getMainOption()).thenReturn("watering");
        Assert.assertFalse(shovel.equalsTypes(wateringCan));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameObjects() {
        Shovel shovel2 = new Shovel();
        Assert.assertTrue(shovel.equalsTypes(shovel2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        Shovel s = new Shovel();
        s.setPrice(100);
        Assert.assertEquals(100, s.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        Shovel s = new Shovel();
        s.setDiscount(0.3);
        Assert.assertEquals(0.3, s.getDiscount(), EPS);
    }

}
