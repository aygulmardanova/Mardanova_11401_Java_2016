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
public class YellowRoseTest {

    private static final double EPS = 1e-9;
    private static YellowRose yellowRose;

    @BeforeClass
    public static void beforeMethod() {
        yellowRose = new YellowRose();
    }

    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, yellowRose.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        YellowRose yellowRose = new YellowRose(3);
        Assert.assertEquals(3, yellowRose.getCount());
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
        RedRose redRose = mock(RedRose.class);
        when(redRose.getType()).thenReturn("rose");
        Assert.assertTrue(yellowRose.equalsType(redRose));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        WhiteAster whiteAster = mock(WhiteAster.class);
        when(whiteAster.getType()).thenReturn("aster");
        Assert.assertFalse(yellowRose.equalsType(whiteAster));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        YellowRose yellowRose2 = new YellowRose();
        Assert.assertTrue(yellowRose.equals(yellowRose2));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        WhiteAster whiteAster = mock(WhiteAster.class);
        when(whiteAster.getType()).thenReturn("aster");
        when(whiteAster.getColor()).thenReturn("white");
        Assert.assertFalse(yellowRose.equals(whiteAster));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        YellowRose yr = new YellowRose();
        yr.setPrice(200);
        Assert.assertEquals(200, yr.getPrice(), EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        YellowRose yr = new YellowRose();
        yr.setDiscount(0.25);
        Assert.assertEquals(0.25, yr.getDiscount(), EPS);
    }
}
