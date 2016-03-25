package task010.tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Created by Айгуль on 24.02.2016.
 */
public class WateringCanTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static WateringCan wateringCan;
    private static WateringCan wateringCan2;


    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");
        wateringCan = (WateringCan) ac.getBean("wateringcan");
        wateringCan2 = (WateringCan) ac.getBean("wateringcan2");
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, wateringCan.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount() {
        Assert.assertEquals(2, wateringCan2.getCount());
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
        Shovel shovel = (Shovel) ac.getBean("shovel");
        Assert.assertFalse(wateringCan.equalsTypes(shovel));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameMainOptions() {
        Assert.assertTrue(wateringCan.equalsTypes(wateringCan2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        WateringCan wc = (WateringCan) ac.getBean("wateringcan");
        wc.setPrice(300);
        Assert.assertEquals(300, wc.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        WateringCan wc = (WateringCan) ac.getBean("wateringcan");
        wc.setDiscount(0.3);
        Assert.assertEquals(0.3, wc.getDiscount(), EPS);
    }

}
