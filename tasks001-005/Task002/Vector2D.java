package Task002;

public class Vector2D {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public void add2(Vector2D v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public Vector2D sub(Vector2D v) {
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    public void sub2(Vector2D v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public Vector2D mult(double mult) {
        return new Vector2D(x * mult, y * mult);
    }

    public void mult2(double mult) {
        this.x *= mult;
        this.y *= mult;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double scalarProduct(Vector2D v) {
        return x * v.getX() + y * v.getY();
    }

    public double cos(Vector2D v) {
        return Math.cos(this.scalarProduct(v) / (this.length() + v.length()));
    }

    public boolean equals(Vector2D v) {
        return x == v.getX() && y == v.getY();
    }

}
