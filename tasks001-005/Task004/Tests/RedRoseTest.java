package Task004.Tests;

import Task004.classes.RedRose;
import Task004.classes.WhiteAster;
import Task004.classes.YellowRose;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class RedRoseTest {

    private static final double EPS = 1e-9;
    private static RedRose redRose;

    @BeforeClass
    public static void beforeMethod() {
        redRose = new RedRose();
    }


    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, redRose.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        RedRose redRose = new RedRose(3);
        Assert.assertEquals(3, redRose.getCount());
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
        YellowRose yellowRose = mock(YellowRose.class);
        when(yellowRose.getType()).thenReturn("rose");
        Assert.assertTrue(redRose.equalsType(yellowRose));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        WhiteAster whiteAster = mock(WhiteAster.class);
        when(whiteAster.getType()).thenReturn("aster");
        Assert.assertFalse(redRose.equalsType(whiteAster));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        RedRose redRose2 = new RedRose();
        Assert.assertTrue(redRose.equals(redRose2));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        WhiteAster whiteAster = mock(WhiteAster.class);
        when(whiteAster.getType()).thenReturn("aster");
        when(whiteAster.getColor()).thenReturn("white");
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
