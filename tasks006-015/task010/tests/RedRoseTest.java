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
public class RedRoseTest {

    private static final double EPS = 1e-9;
    private static RedRose redRose;
    private static RedRose redRose3;
    private static ApplicationContext ac;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        redRose = (RedRose) ac.getBean("redrose");
        redRose3 = (RedRose) ac.getBean("redrose3");
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, redRose.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Assert.assertEquals(3, redRose3.getCount());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        String name = redRose.getName();
        Assert.assertEquals("Red Rose", name);
    }

    @Test
    public void getColorShouldReturnCorrectRedColor() {
        Assert.assertEquals("red", redRose.getColor());
    }

    @Test
    public void getTypeShouldReturnCorrectRoseType() {
        Assert.assertEquals("rose", redRose.getType());
    }

    @Test
    public void getDescriptionShouldReturnCorrectDescription() {
        Assert.assertEquals("Extremely beautiful flower with pleasant fragrance", redRose.getDescription());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Assert.assertEquals(150, redRose.getPrice(), EPS);
    }

    @Test
    public void getDiscountShouldReturnCorrectDiscountValue() {
        Assert.assertEquals(0.1, redRose.getDiscount(), EPS);
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfSame() {
        YellowRose yellowRose = (YellowRose) ac.getBean("yellowrose");
        Assert.assertTrue(redRose.equalsType(yellowRose));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        WhiteAster whiteAster = (WhiteAster) ac.getBean("whiteaster");
        Assert.assertFalse(redRose.equalsType(whiteAster));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        Assert.assertTrue(redRose.equals(redRose3));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        WhiteAster whiteAster = (WhiteAster) ac.getBean("whiteaster");
        Assert.assertFalse(redRose.equals(whiteAster));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        RedRose rr = new RedRose();
        rr.setPrice(200);
        Assert.assertEquals(200, rr.getPrice(), EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        RedRose rr = new RedRose();
        rr.setDiscount(0.25);
        Assert.assertEquals(0.25, rr.getDiscount(), EPS);
    }

}
