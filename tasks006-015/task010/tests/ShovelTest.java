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
public class ShovelTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static Shovel shovel;
    private static Shovel shovel2;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");
        shovel = (Shovel) ac.getBean("shovel");
        shovel2 = (Shovel) ac.getBean("shovel2");

    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, shovel.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Assert.assertEquals(2, shovel2.getCount());
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
        Hoe hoe = (Hoe) ac.getBean("hoe");
        Assert.assertTrue(shovel.equalsTypes(hoe));
    }

    @Test
    public void equalsTypesShouldReturnFalseIfDifferentMainOptions() {
        WateringCan wateringCan = (WateringCan) ac.getBean("wateringcan");
        Assert.assertFalse(shovel.equalsTypes(wateringCan));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameObjects() {
        Assert.assertTrue(shovel.equalsTypes(shovel2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        Shovel s = (Shovel) ac.getBean("shovel");
        s.setPrice(100);
        Assert.assertEquals(100, s.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        Shovel s = (Shovel) ac.getBean("shovel");
        s.setDiscount(0.3);
        Assert.assertEquals(0.3, s.getDiscount(), EPS);
    }

}
