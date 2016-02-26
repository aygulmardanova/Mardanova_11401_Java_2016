package Task004.Tests;

import Task004.classes.RedRose;
import Task004.classes.WhiteAster;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 24.02.2016.
 */
public class WhiteAsterTest {

    private static final double EPS = 1e-9;
    private static WhiteAster whiteAster;

    @BeforeClass
    public static void beforeMethod() {
        whiteAster = new WhiteAster();
    }


    @Test
    public void emptyConstructorShouldCreateObjectWithOneCount() {
        Assert.assertEquals(1, whiteAster.getCount());
    }

    @Test
    public void constructorShouldCreateObjectWithEnteredCount(){
        WhiteAster whiteAster = new WhiteAster(3);
        Assert.assertEquals(3, whiteAster.getCount());
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
        WhiteAster whiteAster1 = mock(WhiteAster.class);
        when(whiteAster1.getType()).thenReturn("aster");
        Assert.assertTrue(whiteAster.equalsType(whiteAster1));
    }

    @Test
    public void equalsTypeMethodCompareTypesCorrectlyIfDifferent() {
        RedRose redRose = mock(RedRose.class);
        when(redRose.getType()).thenReturn("rose");
        Assert.assertFalse(whiteAster.equalsType(redRose));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfSame() {
        WhiteAster whiteAster2 = new WhiteAster(2);
        Assert.assertTrue(whiteAster.equals(whiteAster2));
    }

    @Test
    public void equalsMethodCompareFlowersCorrectlyIfDifferent() {
        RedRose redRose = mock(RedRose.class);
        when(redRose.getType()).thenReturn("rose");
        when(redRose.getColor()).thenReturn("red");
        Assert.assertFalse(whiteAster.equals(redRose));
    }

    @Test
    public void setPriceMethodShouldChangeThePrice() {
        WhiteAster wa = new WhiteAster();
        wa.setPrice(200);
        Assert.assertEquals(200, wa.getPrice(), EPS);
    }

    @Test
    public void setDiscountShouldChangeTheDiscount() {
        WhiteAster wa = new WhiteAster();
        wa.setDiscount(0.25);
        Assert.assertEquals(0.25, wa.getDiscount(), EPS);
    }
}
