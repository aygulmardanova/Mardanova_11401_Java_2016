package task009;

/**
 * Created by Айгуль on 23.02.2016.
 */
public class ComplexMatrix2x2 {

    private ComplexNumber[][] matrix;

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public ComplexNumber getElement(int i, int j) {
        return this.matrix[i][j];
    }

    public ComplexMatrix2x2() {
        this(new ComplexNumber[][]{{new ComplexNumber(), new ComplexNumber()}, {new ComplexNumber(), new ComplexNumber()}});
    }

    public ComplexMatrix2x2(ComplexNumber complexNumber) {
        this(new ComplexNumber[][]{{complexNumber, complexNumber}, {complexNumber, complexNumber}});
    }

    public ComplexMatrix2x2(ComplexNumber c1, ComplexNumber c2, ComplexNumber c3, ComplexNumber c4) {
        this.matrix = new ComplexNumber[][]{{c1, c2}, {c3, c4}};
    }

    public ComplexMatrix2x2(ComplexNumber[][] matrix) {
        this.matrix = matrix;
    }

    public ComplexMatrix2x2 add(ComplexMatrix2x2 matrix2x2) {
        ComplexNumber[][] matrix = new ComplexNumber[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                matrix[i][j] = this.getElement(i, j).add(matrix2x2.getElement(i, j));
                //matrix[i][j] = this.matrix[i][j].add(matrix2x2.getMatrix()[i][j]);
        return new ComplexMatrix2x2(matrix);
    }

    public ComplexMatrix2x2 mult(ComplexMatrix2x2 matrix2x2) {
        ComplexNumber[][] matrix = new ComplexNumber[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = new ComplexNumber();
                for (int r = 0; r < 2; r++)
                    matrix[i][j] =
                            matrix[i][j].add(this.getElement(i, r).mult(matrix2x2.getElement(r, j)));
            }
        return new ComplexMatrix2x2(matrix);
    }

    public ComplexNumber det() {
        return ((getElement(0, 0).mult(getElement(1, 1))).sub(getElement(1, 0).mult(getElement(0, 1))));
        //return ((matrix[0][0].mult(matrix[1][1])).sub(matrix[1][0].mult(matrix[0][1])));
    }

    public ComplexVector2D multVector(ComplexVector2D cVector2D) {
        return new ComplexVector2D(this.matrix[0][0].mult(cVector2D.getX()).add(this.matrix[0][1].mult(cVector2D.getY())),
                this.matrix[1][0].mult(cVector2D.getX()).add(this.matrix[1][1].mult(cVector2D.getY())));
    }

    public String toString() {
        return (matrix[0][0].toString() + " " + matrix[0][1].toString()
                + "\n" + matrix[1][0].toString() + " " + matrix[1][1].toString() + "\n");
    }

    public boolean equals(ComplexMatrix2x2 matrix2x2) {
        boolean flag = true;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (! (this.matrix[i][j].equals(matrix2x2.getMatrix()[i][j])))
                    flag = false;
        return flag;
    }

}
