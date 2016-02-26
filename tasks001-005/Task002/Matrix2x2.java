package Task002;

/**
 * Created by Айгуль on 22.02.2016.
 */
public class Matrix2x2 {

    private double[][] matrix;

    public double[][] getMatrix() {
        return matrix;
    }

    public double getElement(int i, int j) {
        return this.matrix[i][j];
    }

    public Matrix2x2() {
        this.matrix = new double[2][2];
    }

    public Matrix2x2(double value) {
        this(new double[][]{{value, value}, {value, value}});
    }

    public Matrix2x2(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix2x2(double value1, double value2, double value3, double value4) {
        this(new double[][]{{value1, value2}, {value3, value4}});
    }

    public Matrix2x2 add(Matrix2x2 matrix2) {
        double[][] m = new double[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                m[i][j] = this.matrix[i][j] + matrix2.getMatrix()[i][j];
        return new Matrix2x2(m);
    }

    public void add2(Matrix2x2 matrix2) {
        this.matrix = this.add(matrix2).getMatrix();
    }

    public Matrix2x2 sub(Matrix2x2 matrix2) {
        double[][] m = new double[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                m[i][j] = this.matrix[i][j] - matrix2.getMatrix()[i][j];
        return new Matrix2x2(m);
    }

    public void sub2(Matrix2x2 matrix2) {
        this.matrix = this.sub(matrix2).getMatrix();
    }

    public Matrix2x2 multNumber(double mult) {
        double[][] m = new double[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                m[i][j] = this.matrix[i][j] *  mult;
        return new Matrix2x2(m);
    }

    public void multNumber2(double mult) {
        this.matrix = this.multNumber(mult).getMatrix();
//        for (int i = 0; i < 2; i++)
//            for (int j = 0; j < 2; j++)
//                this.matrix[i][j] *= mult;
    }

    public Matrix2x2 mult(Matrix2x2 matrix2) {
        double[][] m = new double[2][2];
        double[][] a = matrix;
        double[][] b = matrix2.getMatrix();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++)
                for (int r = 0; r < 2; r++) {
                    m[i][j] += a[i][r] * b[r][j];
                }
        }
        return new Matrix2x2(m);
    }

    public void mult2(Matrix2x2 matrix2) {
        this.matrix = this.mult(matrix2).getMatrix();
//        double[][] a = getMatrix();
//        double[][] b = matrix2.getMatrix();
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++)
//                for (int r = 0; r < 2; r++) {
//                    this.matrix[i][j] += a[i][r] * b[r][j];
//                }
//        }
    }

    public double det() {
        double[][] m = getMatrix();
        return (m[0][0] * m[1][1] - m[0][1] * m[1][0]);
    }

    public void transpose() {
        double x = getMatrix()[0][1];
        this.matrix[0][1] = getMatrix()[1][0];
        this.matrix[1][0] = x;
    }

    public Matrix2x2 inverseMatrix() throws InverseMatrixDoesNotExistException {
        double[][] m = new double[2][2];
        double det = det();
        if (det == 0) {
            throw new InverseMatrixDoesNotExistException();
        } else {
            m[0][0] = this.matrix[1][1] / this.det();
            m[0][1] = - this.matrix[1][0] / this.det();
            m[1][0] = - this.matrix[0][1] / this.det();
            m[1][1] = this.matrix[0][0] / this.det();
            return new Matrix2x2(m);
        }
    }

    public Matrix2x2 equivalentDiagonal() {


        Matrix2x2 diag = new Matrix2x2();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                diag.matrix[i][j] = this.matrix[i][j];
            }
        }
        if (diag.matrix[1][0] < diag.matrix[0][0]) {
            double[] x = diag.matrix[0];
            diag.matrix[0] = diag.matrix[1];
            diag.matrix[1] = x;
        }

        double r = diag.matrix[1][0] / diag.matrix[0][0];
        for (int i = 0; i < 2; i++) {
            diag.matrix[1][i] -= diag.matrix[0][i] * r;
        }

        r = diag.matrix[0][1] / diag.matrix[1][1];
        for (int i = 0; i < 2; i++) {
            diag.matrix[0][i] -= diag.matrix[1][i] * r;
        }
        return diag;
    }

    public Vector2D multVector(Vector2D vector2D) {
        return new Vector2D(this.matrix[0][0] * vector2D.getX() + this.matrix[0][1] * vector2D.getY(),
                this.matrix[1][0] * vector2D.getX() + this.matrix[1][1] * vector2D.getY());
    }

    public String toString() {
        return (matrix[0][0] + " " + matrix[0][1] + "\n" + matrix[1][0] + " " + matrix[1][1] + "\n");
    }

    public boolean equals(Matrix2x2 matrix2) {
        boolean flag = true;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (this.matrix[i][j] != matrix2.getMatrix()[i][j])
                    flag = false;
        return flag;
    }

}
