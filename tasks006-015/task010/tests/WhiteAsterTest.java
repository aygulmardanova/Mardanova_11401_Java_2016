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
public class WhiteAsterTest {

    private static final double EPS = 1e-9;
    private static ApplicationContext ac;
    private static WhiteAster whiteAster;
    private static WhiteAster whiteAster5;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task010/spring-config10.xml");

        whiteAster = (WhiteAster) ac.getBean("whiteaster");
        whiteAster5 = (WhiteAster) ac.getBean("whiteaster5");
    }


    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, whiteAster.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        Assert.assertEquals(5, whiteAster5.getCount());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        String name = whiteAster.getName();
        Assert.assertEquals("White Aster", name);
    }

    @Test
    public void getColorShouldReturnCorrectWhiteColor() {
        Assert.assertEquals("white", whiteAster.getColor());
    }

    @Test
    public void getTypeShouldReturnCorrectAsterType() {
        Assert.assertEquals("aster", whiteAster.getType());
    }

    @Test
    public void getDescriptionShouldReturnCorrectDescription() {
        Assert.assertEquals("A delicate flower with light fragrance", whiteAster.getDescription());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Assert.assertEquals(120, whiteAster.getPrice(), EPS);
    }

    @Test
    public void getDiscountShouldReturnCorrectDiscountValue() {
        Assert.assertEquals(0.0, whiteAster.getDiscount(), EPS);
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfSame() {
        Assert.assertTrue(whiteAster.equalsType(whiteAster5));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        RedRose redRose = (RedRose) ac.getBean("redrose");
        Assert.assertFalse(whiteAster.equalsType(redRose));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        Assert.assertTrue(whiteAster.equals(whiteAster5));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        RedRose redRose = (RedRose) ac.getBean("redrose");
        Assert.assertFalse(whiteAster.equals(redRose));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        WhiteAster wa = (WhiteAster) ac.getBean("whiteaster");
        wa.setPrice(200);
        Assert.assertEquals(200, wa.getPrice(), EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        WhiteAster wa = (WhiteAster) ac.getBean("whiteaster");
        wa.setDiscount(0.25);
        Assert.assertEquals(0.25, wa.getDiscount(), EPS);
    }
}
