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
 * Created by Айгуль on 26.02.2016.
 */
public class HoeTest {

    private static final double EPS = 1e-9;
    private static Hoe hoe;


    @BeforeClass
    public static void beforeClassMethod() {
        hoe = new Hoe();
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, hoe.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Hoe hoe = new Hoe(3);
        Assert.assertEquals(3, hoe.getCount());
    }

    @Test
    public void getNameMethodShouldReturnCorrectNameHoe() {
        Assert.assertEquals("Hoe", hoe.getName());
    }

    @Test
    public void demonstrateMethodShouldDemonstrateTheHoe() {
        Assert.assertEquals("I'm a hoe. \n        I'm very useful in the garden! I can hoe the ground. " +
                "This can help you before digging the soil. Buy me and you won't regret!", hoe.demonstrate());
    }

    @Test
    public void getPriceMethodShouldReturnTheCorrectPriceOfHoe() {
        Assert.assertEquals(250, hoe.getPrice(), EPS);
    }

    @Test
    public void getDiscountMethodShouldReturnTheCorrectDiscountValueForShovel() {
        Assert.assertEquals(0.1, hoe.getDiscount(), EPS);
    }


    @Test
    public void getMainOptionShouldReturnDigging() {
        String mainOption = hoe.getMainOption();
        Assert.assertEquals("digging", mainOption);
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameMainOptions() {
        Shovel shovel = mock(Shovel.class);
        when(shovel.getMainOption()).thenReturn("digging");
        Assert.assertTrue(hoe.equalsTypes(shovel));
    }

    @Test
    public void equalsTypesShouldReturnFalseIfDifferentMainOptions() {
        WateringCan wateringCan = mock(WateringCan.class);
        when(wateringCan.getMainOption()).thenReturn("watering");
        Assert.assertFalse(hoe.equalsTypes(wateringCan));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameObjects() {
        Hoe hoe2 = new Hoe();
        Assert.assertTrue(hoe.equalsTypes(hoe2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        Hoe h = new Hoe();
        h.setPrice(300);
        Assert.assertEquals(300, h.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        Hoe h = new Hoe();
        h.setDiscount(0.3);
        Assert.assertEquals(0.3, h.getDiscount(), EPS);
    }

}
