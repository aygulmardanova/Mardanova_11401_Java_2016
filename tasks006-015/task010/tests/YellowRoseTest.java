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
public class YellowRoseTest {

    private static final double EPS = 1e-9;
    private static YellowRose yellowRose;
    private static YellowRose yellowRose3;
    private static ApplicationContext ac;


    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        yellowRose = (YellowRose) ac.getBean("yellowrose");
        yellowRose3 = (YellowRose) ac.getBean("yellowrose3");
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, yellowRose.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount() {
        Assert.assertEquals(3, yellowRose3.getCount());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        String name = yellowRose.getName();
        Assert.assertEquals("Yellow Rose", name);
    }


    @Test
    public void getColorShouldReturnCorrectYellowColor() {
        Assert.assertEquals("yellow", yellowRose.getColor());
    }

    @Test
    public void getTypeShouldReturnCorrectRoseType() {
        Assert.assertEquals("rose", yellowRose.getType());
    }

    @Test
    public void getDescriptionShouldReturnCorrectDescription() {
        Assert.assertEquals("Extremely beautiful flower with pleasant fragrance", yellowRose.getDescription());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Assert.assertEquals(170, yellowRose.getPrice(), EPS);
    }

    @Test
    public void getDiscountShouldReturnCorrectDiscountValue() {
        Assert.assertEquals(0.1, yellowRose.getDiscount(), EPS);
    }


    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfSame() {
        RedRose redRose = (RedRose) ac.getBean("redrose");
        Assert.assertTrue(yellowRose.equalsType(redRose));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        WhiteAster whiteAster = (WhiteAster) ac.getBean("whiteaster");
        Assert.assertFalse(yellowRose.equalsType(whiteAster));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        Assert.assertTrue(yellowRose.equals(yellowRose3));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        WhiteAster whiteAster = (WhiteAster) ac.getBean("whiteaster");
        Assert.assertFalse(yellowRose.equals(whiteAster));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        YellowRose yr = (YellowRose) ac.getBean("yellowrose");
        yr.setPrice(200);
        Assert.assertEquals(200, yr.getPrice(), EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        YellowRose yr = (YellowRose) ac.getBean("yellowrose");
        yr.setDiscount(0.25);
        Assert.assertEquals(0.25, yr.getDiscount(), EPS);
    }
}
