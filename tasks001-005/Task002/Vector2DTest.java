package Task002;

import org.junit.*;

/**
 * Created by Айгуль on 17.02.2016.
 */
public class Vector2DTest {

    private static final double EPS = 1e-9;
    private static Vector2D simpleVector;
    private static Vector2D notSimpleVector;


    @Test
    public void defaultVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, simpleVector.length(), EPS);
    }

    @Test
    public void lengthOfVectorShouldWorkCorrectly() {
        double length = Math.sqrt(
                notSimpleVector.getX() * notSimpleVector.getX() +
                notSimpleVector.getY() * notSimpleVector.getY()
        );
        Assert.assertEquals(length, notSimpleVector.length(), EPS);
    }

    @Test
    public void firstParamOfConstructorShouldBeSavedInX() {
        Vector2D v = new Vector2D(3, 4);
        Assert.assertEquals(3, v.getX(), EPS);
    }

    @Test
    public void secondParamOfConstructorShouldBeSavedInY() {
        Vector2D v = new Vector2D(3, 4);
        Assert.assertEquals(4, v.getY(), EPS);
    }

    @Test
    public void vectorAddingWorkCorrectlyWithX() {
        Vector2D v = notSimpleVector.add(notSimpleVector);
        Assert.assertEquals(6, v.getX(), EPS);
    }

    @Test
    public void vectorAddingWorkCorrectlyWithY() {
        Vector2D v = notSimpleVector.add(notSimpleVector);
        Assert.assertEquals(8, v.getY(), EPS);
    }

    @Test
    public void vectorAddingWorksCorrectlyWithEmptyVector() {
        Vector2D v = simpleVector.add(simpleVector);
        Assert.assertEquals(0, v.getY(), EPS);
        Assert.assertEquals(0, v.getY(), EPS);
    }

    @BeforeClass
    public static void beforeClass() {
        simpleVector = new Vector2D();
    }

    @AfterClass
    public static void afterClass() {
        notSimpleVector = new Vector2D(3, 4);
    }

}
