package Task002;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Айгуль on 22.02.2016.
 */
public class Matrix2x2Test {

    private static final double EPS = 1e-9;

    private Matrix2x2 matrix;
    private static Matrix2x2 matrix1;
    private Matrix2x2 matrix2;
    //private Matrix2x2 matrix3;
    private static Matrix2x2 matrix4;

    @BeforeClass
    public static void beforeClassMethod() {
        matrix1 = new Matrix2x2();
        matrix4 = new Matrix2x2(2, 2, 2, 2);  //используются только при проверке соответствующих конструкторов (не изменяются)
    }

    @Before
    public void beforeMethod() {
        matrix = new Matrix2x2(4, 3, 2, 1);
        matrix2 = new Matrix2x2(2);
        //matrix3 = new Matrix2x2(new double[][]{{2, 2}, {2, 2}});
    }

    @Test
    public void defaultMatrixShouldHaveZeroValueElements() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(0, matrix1.getMatrix()[i][j], EPS);
    }

    @Test
    public void secondConstructorShouldFillTheArrayWithConcreteValue() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(2, matrix2.getMatrix()[i][j], EPS);
    }

    //проверяется ссылка
    @Test
    public void thirdConstructorShouldLinkTheArray() {
        double[][] m = new double[][]{{2, 2}, {2, 2}};
        Assert.assertSame(m, new Matrix2x2(m).getMatrix());
    }

    @Test
    public void forthConstructorShouldFillTheArrayWithConcreteValues() {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(2, matrix4.getMatrix()[i][j], EPS);
    }

    @Test
    public void getElementMethodShouldReturnCorrectDoubleValue() {
        Assert.assertEquals(2, matrix2.getElement(0, 0), EPS);
    }

    @Test
    public void addMethodShouldReturnTheCorrectMatrix() {
        Matrix2x2 result = matrix2.add(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(4, result.getMatrix()[i][j], EPS);
    }

    @Test
    public void add2MethodShouldChangeTheMatrixCorrectly() {
        matrix2.add2(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(4, matrix2.getMatrix()[i][j], EPS);
    }

    @Test
    public void subMethodShouldSubtractTheValuesCorrectly() {
        Matrix2x2 result = matrix2.sub(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(0, result.getMatrix()[i][j], EPS);
    }

    @Test
    public void sub2MethodShouldChangeTheMatrixValuesCorrectly() {
        matrix2.sub2(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(0, matrix2.getMatrix()[i][j], EPS);
    }

    @Test
    public void multNumberShouldMultiplyTheValues() {
        Matrix2x2 result = matrix2.multNumber(5);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(10, result.getMatrix()[i][j], EPS);
    }

    @Test
    public void multNumber2ShouldChangeValuesToMultipliedValues() {
        matrix2.multNumber2(5);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(10, matrix2.getMatrix()[i][j], EPS);
    }

    @Test
    public void multMethodShouldReturnTheCorrectResultMatrix() {
        Matrix2x2 result = matrix2.mult(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(8, result.getMatrix()[i][j], EPS);
    }

    @Test
    public void multMethodShouldWorkCorrectlyOnMatrixWithDifferentValues() {
        Matrix2x2 result = matrix.mult(matrix2);
        Assert.assertEquals(14, result.getMatrix()[0][0], EPS);
        Assert.assertEquals(14, result.getMatrix()[0][1], EPS);
        Assert.assertEquals(6, result.getMatrix()[1][0], EPS);
        Assert.assertEquals(6, result.getMatrix()[1][1], EPS);
    }

    @Test
    public void mult2MethodShouldChangeValuesToMultiplicationResultMatrix() {
        matrix2.mult2(matrix2);
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                Assert.assertEquals(8, matrix2.getMatrix()[i][j], EPS);
    }

    @Test
    public void detShouldCalculateTheDeterminantOfMatrix() {
        Assert.assertEquals(-2, matrix.det(), EPS);
    }

    @Test
    public void detCalculationWorksCorrectlyWithZeroDeterminant() {
        Assert.assertEquals(0, matrix2.det(), EPS);
    }

    @Test
    public void transposeMethodShouldReturnCorrectlyTransposedMatrix() {
        matrix.transpose();
        Assert.assertEquals(3, matrix.getMatrix()[1][0], EPS);
        Assert.assertEquals(2, matrix.getMatrix()[0][1], EPS);
    }

    @Test
    public void inverseMatrixShouldReturnInvertedMatrixIfNonZeroDet() throws InverseMatrixDoesNotExistException {
        Matrix2x2 result = matrix.inverseMatrix();
        Assert.assertEquals(-0.5, result.getMatrix()[0][0], EPS);
        Assert.assertEquals(1, result.getMatrix()[0][1], EPS);
        Assert.assertEquals(1.5, result.getMatrix()[1][0], EPS);
        Assert.assertEquals(-2, result.getMatrix()[1][1], EPS);
    }

    @Test(expected = InverseMatrixDoesNotExistException.class)
    public void inverseMatrixWithZeroDetExpectedException() throws InverseMatrixDoesNotExistException {
        matrix2.inverseMatrix();
    }

    @Test
    public void equivalentDiagonalShouldReturnCorrectMatrixWithNonZeroValuesOnlyOnDiagonal() {
        Matrix2x2 diag = matrix.equivalentDiagonal();
        Assert.assertEquals(0.0, diag.getElement(0, 1), EPS);
        Assert.assertEquals(0.0, diag.getElement(1, 0), EPS);
        Assert.assertEquals(2.0, diag.getElement(0, 0), EPS);
        Assert.assertEquals(1.0, diag.getElement(1, 1), EPS);
    }

    @Test
    public void multVectorMethodShouldReturnCorrectColumnVector() {
        Vector2D result = matrix.multVector(new Vector2D(2, 2));
        Assert.assertEquals(14, result.getX(), EPS);
        Assert.assertEquals(6, result.getY(), EPS);
    }

    @Test
    public void equalsMethodShouldCompareTwoMatrix() {
        Assert.assertTrue(matrix2.equals(matrix4));
    }
}
