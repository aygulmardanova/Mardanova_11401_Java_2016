package Task003;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Айгуль on 23.02.2016.
 */
public class ComplexMatrix2x2Test {

    private static final double EPS = 1e-9;

    private ComplexNumber cn;
    private ComplexVector2D v;
    private ComplexMatrix2x2 complexMatrix2x2;
    private ComplexMatrix2x2 cm2;

    @BeforeClass
    public static void beforeClassMethod() {

    }

    @Before
    public void beforeMethod() {
        cn = mock(ComplexNumber.class);
        //when(cn.add(any(ComplexNumber.class))).thenReturn(new ComplexNumber(1, 2));
        when(cn.add(any(ComplexNumber.class))).thenReturn(cn);
        //when(cn.sub(any(ComplexNumber.class))).thenReturn(new ComplexNumber(1, 2));
        when(cn.sub(any(ComplexNumber.class))).thenReturn(cn);
        //when(cn.mult(any(ComplexNumber.class))).thenReturn(new ComplexNumber(1, 2));
        when(cn.mult(any(ComplexNumber.class))).thenReturn(cn);
        when(cn.getA()).thenReturn((double) 1);
        when(cn.getB()).thenReturn((double) 2);
        when(cn.equals(cn)).thenReturn(true);

        v = mock(ComplexVector2D.class);
        when(v.getX()).thenReturn(cn);   //1 + 2i
        when(v.getY()).thenReturn(cn);   //1 + 2i

        cm2 = mock(ComplexMatrix2x2.class);
        //when(cm2.add(any(ComplexMatrix2x2.class))).thenReturn(new ComplexMatrix2x2(cn));
        when(cm2.getMatrix()).thenReturn(new ComplexNumber[][]{{cn, cn}, {cn, cn}});
        when(cm2.getElement(0, 0)).thenReturn(cn);
        when(cm2.getElement(0, 1)).thenReturn(cn);
        when(cm2.getElement(1, 0)).thenReturn(cn);
        when(cm2.getElement(1, 1)).thenReturn(cn);

        complexMatrix2x2 = new ComplexMatrix2x2(cn);

        //when(cm2.mult(any(ComplexMatrix2x2.class))).thenReturn(new ComplexMatrix2x2(cn));
        //when(cm2.multVector(any(ComplexVector2D.class))).thenReturn(v);


    }

    @Test
    public void emptyConstructorShouldCreateZeroValueElements() {
        ComplexMatrix2x2 matrix = new ComplexMatrix2x2();
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                //Assert.assertTrue(matrix.getElement(i, j).equals(new ComplexNumber()));
                Assert.assertEquals(0, matrix.getMatrix()[i][j].getA(), EPS);
                Assert.assertEquals(0, matrix.getMatrix()[i][j].getB(), EPS);
            }
    }

    @Test
    public void secondConstructorShouldFillTheMatrixWithConcreteValue() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(1, complexMatrix2x2.getMatrix()[i][j].getA(), EPS);
                Assert.assertEquals(2, complexMatrix2x2.getMatrix()[i][j].getB(), EPS);
            }
    }

    @Test
    public void thirdConstructorShouldLinkTheArray() {
        ComplexMatrix2x2 matrix  = new ComplexMatrix2x2(cn, cn, cn, cn);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(cn, matrix.getMatrix()[i][j]);
    }

    @Test
    public void forthConstructorShouldLinkTheSameArray() {
        ComplexNumber[][] matrix = new ComplexNumber[][]{{cn, cn}, {cn, cn}};
        Assert.assertSame(matrix, new ComplexMatrix2x2(matrix).getMatrix());
    }

    @Test
    public void getElementMethodShouldReturnCorrectComplexNumberValue() {
        Assert.assertEquals(cn, complexMatrix2x2.getElement(0, 0));
    }

    @Test
    public void addMethodShouldAddTheElementsOfEachMatrix() {
        ComplexMatrix2x2 matrix = complexMatrix2x2.add(cm2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertTrue(cn.equals(matrix.getMatrix()[i][j]));
                Assert.assertEquals(1, matrix.getElement(i, j).getA(), EPS);
                Assert.assertEquals(2, matrix.getElement(i, j).getB(), EPS);
            }
    }

    @Test
    public void multMethodShouldMultiplyTheComplexMatrix(){

        ComplexMatrix2x2 matrix = complexMatrix2x2.mult(cm2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(1, matrix.getElement(i, j).getA(), EPS);
                Assert.assertEquals(2, matrix.getElement(i, j).getB(), EPS);
            }
    }

    @Test
    public void detMethodShouldCalculateTheCorrectDeterminant() {
        ComplexNumber det = complexMatrix2x2.det();   //cn*cn - cn*cn = (1+2i)*(1+2i)-(1+2i)*(2+2i) = (1+2i)-(1+2i) = 0
        Assert.assertEquals(1, det.getA(), EPS);
        Assert.assertEquals(2, det.getB(), EPS);
    }

    @Test
    public void multVectorMethodShouldReturnCorrectComplexVector2D() {
        ComplexVector2D v2 = complexMatrix2x2.multVector(v);
        Assert.assertEquals(1, v2.getX().getA(), EPS);
        Assert.assertEquals(2, v2.getX().getB(), EPS);
        Assert.assertEquals(1, v2.getY().getA(), EPS);
        Assert.assertEquals(2, v2.getY().getB(), EPS);
    }

    @Test
    public void equalsMethodWorksCorrectlyWithEquivalentMatrix() {
        Assert.assertTrue(complexMatrix2x2.equals(complexMatrix2x2));
    }
}
