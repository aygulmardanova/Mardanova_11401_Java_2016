package task010.tests;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task010.classes.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Айгуль on 26.02.2016.
 */
public class HoeTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static Hoe hoe;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        hoe = (Hoe) ac.getBean("hoe");
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount(){
        Assert.assertEquals(1, hoe.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Hoe hoe2 = (Hoe) ac.getBean("hoe2");
        Assert.assertEquals(2, hoe2.getCount());
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
        Shovel shovel = (Shovel) ac.getBean("shovel");
        Assert.assertTrue(hoe.equalsTypes(shovel));
    }

    @Test
    public void equalsTypesShouldReturnFalseIfDifferentMainOptions() {
        WateringCan wateringCan = (WateringCan) ac.getBean("wateringcan");
        Assert.assertFalse(hoe.equalsTypes(wateringCan));
    }

    @Test
    public void equalsTypesShouldReturnTrueIfSameObjects() {
        Hoe hoe2 = (Hoe) ac.getBean("hoe2");
        Assert.assertTrue(hoe.equalsTypes(hoe2));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        Hoe h = (Hoe) ac.getBean("hoe");
        h.setPrice(300);
        Assert.assertEquals(300, h.getPrice(), EPS);
    }

    @Test
    public void setDiscountMethodShouldChangeTheDiscount() {
        Hoe h = (Hoe) ac.getBean("hoe");
        h.setDiscount(0.3);
        Assert.assertEquals(0.3, h.getDiscount(), EPS);
    }

}
