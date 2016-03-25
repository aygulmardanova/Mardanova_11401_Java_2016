package task009;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Айгуль on 23.02.2016.
 */
public class ComplexMatrix2x2Test {

    private static final double EPS = 1e-9;

    private static ApplicationContext ac;
    private static ComplexNumber cn;
    private static ComplexNumber cn2;
    private static ComplexVector2D cv;
    private static ComplexVector2D cv2;
    private static ComplexMatrix2x2 matrix;
    private static ComplexMatrix2x2 matrix2;
    private static ComplexMatrix2x2 matrix3;

    @BeforeClass
    public static void beforeClassMethod() {
        ac = new ClassPathXmlApplicationContext("task009/spring-config9.xml");

        cn = (ComplexNumber) ac.getBean("cn");

        cn2 = (ComplexNumber) ac.getBean("cn2");

        cv = (ComplexVector2D) ac.getBean("cv");

        cv2 = (ComplexVector2D) ac.getBean("cv2");

        matrix = (ComplexMatrix2x2) ac.getBean("matrix");

        matrix2 = (ComplexMatrix2x2) ac.getBean("matrix2");

        matrix3 = (ComplexMatrix2x2) ac.getBean("matrix3");
    }

    @Test
    public void emptyConstructorShouldCreateZeroValueElements() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(0, matrix.getMatrix()[i][j].getA(), EPS);
                Assert.assertEquals(0, matrix.getMatrix()[i][j].getB(), EPS);
            }
    }

    @Test
    public void secondConstructorShouldFillTheMatrixWithConcreteValue() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(1, matrix2.getMatrix()[i][j].getA(), EPS);
                Assert.assertEquals(2, matrix2.getMatrix()[i][j].getB(), EPS);
            }
    }

    @Test
    public void thirdConstructorShouldLinkTheArray() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertTrue(cn2.equals(matrix3.getMatrix()[i][j]));
    }

    @Test
    public void forthConstructorShouldLinkTheSameArray() {
        ComplexNumber[][] m = new ComplexNumber[][]{{cn2, cn2}, {cn2, cn2}};
        Assert.assertSame(m, new ComplexMatrix2x2(m).getMatrix());
    }

    @Test
    public void getElementMethodShouldReturnCorrectComplexNumberValue() {
        Assert.assertTrue(cn2.equals(matrix2.getElement(0, 0)));
    }

    @Test
    public void addMethodShouldAddTheElementsOfEachMatrix() {
        ComplexMatrix2x2 m = matrix2.add(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(2, m.getElement(i, j).getA(), EPS);
                Assert.assertEquals(4, m.getElement(i, j).getB(), EPS);
            }
    }

    @Test
    public void multMethodShouldMultiplyWithTheEmptyComplexMatrix(){
        ComplexMatrix2x2 m = matrix.mult(matrix);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(0, m.getElement(i, j).getA(), EPS);
                Assert.assertEquals(0, m.getElement(i, j).getB(), EPS);
            }
    }

    @Test
    public void multMethodShouldMultiplyTheComplexMatrix(){
        ComplexMatrix2x2 m = matrix2.mult(matrix2);      //-6+8*i
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                Assert.assertEquals(-6, m.getElement(i, j).getA(), EPS);
                Assert.assertEquals(8, m.getElement(i, j).getB(), EPS);
            }
    }

    @Test
    public void detMethodShouldCalculateTheCorrectDeterminant() {
        ComplexNumber det = matrix2.det();   //cn*cn - cn*cn = (1+2i)*(1+2i)-(1+2i)*(2+2i) = (1+2i)-(1+2i) = 0
        Assert.assertTrue(cn.equals(det));
        Assert.assertEquals(0, det.getA(), EPS);
        Assert.assertEquals(0, det.getB(), EPS);
    }

    @Test
    public void multVectorMethodShouldReturnCorrectComplexVector2D() {
        ComplexVector2D vv = matrix2.multVector(cv2);
        Assert.assertEquals(-6, vv.getX().getA(), EPS);
        Assert.assertEquals(8, vv.getX().getB(), EPS);
        Assert.assertEquals(-6, vv.getY().getA(), EPS);
        Assert.assertEquals(8, vv.getY().getB(), EPS);
    }

    @Test
    public void equalsMethodWorksCorrectlyWithEquivalentMatrix() {
        Assert.assertTrue(matrix2.equals(matrix3));
    }
}
