package Task004.Tests;

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
public class WateringCanTest {

    private static final double EPS = 1e-9;
    private static WateringCan wateringCan;

    @BeforeClass
    public static void beforeClassMethod() {
        wateringCan = new WateringCan();
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, wateringCan.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        WateringCan wateringCan = new WateringCan(3);
        Assert.assertEquals(3, wateringCan.getCount());
    }

    @Test
    public void getNameMethodShouldReturnCorrectNameWateringCan() {
        Assert.assertEquals("Watering Can", wateringCan.getName());
    }

    @Test
    public void demonstrateMethodShouldDemonstrateTheShovel() {
        Assert.assertEquals("I'm a watering can. \n       I can water your plants, flowers, so they won't need water. " +
                "I won't let them dry!", wateringCan.demonstrate());
    }

    @Test
    public void getPriceMethodShouldReturnTheCorrectPriceOfWateringCan() {
        Assert.assertEquals(250, wateringCan.getPrice(), EPS);
    }

    @Test
    public void getDiscountMethodShouldReturnTheCorrectDiscountValueForWateringCan() {
        Assert.assertEquals(0.0, wateringCan.getDiscount(), EPS);
    }


    @Test
    public void getMainOptionShouldReturnWatering() {
        String mainOption = wateringCan.getMainOption();
        Assert.assertEquals("watering", mainOption);
    }

    @Test
    public void equalsTypesShouldReturnFalseIfDifferentMainOptions() {
        Shovel shovel = mock(Shovel.class);
        when(shovel.getMainOption()).thenReturn("digging");
        Assert.assertFalse(wateringCan.equalsTypes(shovel));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameMainOptions() {
        WateringCan wateringCan2 = new WateringCan();
        Assert.assertTrue(wateringCan.equalsTypes(wateringCan2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        WateringCan wc = new WateringCan();
        wc.setPrice(300);
        Assert.assertEquals(300, wc.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        WateringCan wc = new WateringCan();
        wc.setDiscount(0.3);
        Assert.assertEquals(0.3, wc.getDiscount(), EPS);
    }

}
