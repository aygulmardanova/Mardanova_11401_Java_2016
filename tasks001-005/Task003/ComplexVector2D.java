package Task003;

/**
 * @author Elvira Batyrova
 * 402
 * 054
 */
public class ComplexVector2D {

    private ComplexNumber x;
    private ComplexNumber y;

    public ComplexNumber getX() {
        return x;
    }

    public void setX(ComplexNumber x) {
        this.x = x;
    }

    public ComplexNumber getY() {
        return y;
    }

    public void setY(ComplexNumber y) {
        this.y = y;
    }

    public ComplexVector2D() {
        this(new ComplexNumber(), new ComplexNumber());
    }

    public ComplexVector2D(ComplexNumber x, ComplexNumber y) {
        this.x = x;
        this.y = y;
    }

    public ComplexVector2D add(ComplexVector2D cVector2D) {
        return new ComplexVector2D(x.add(cVector2D.getX()), y.add(cVector2D.getY()));
    }

    public String toString() {
        return ("(" + x.toString() + ", " + y.toString() + ")");
    }

    public ComplexNumber scalarProduct(ComplexVector2D cVector2D) {
        return x.mult(cVector2D.getX()).add(y.mult(cVector2D.getY()));
    }

    public boolean equals(ComplexVector2D cVector2D) {
        return (x.equals(cVector2D.getX()) && y.equals(cVector2D.getY()));
    }

}
