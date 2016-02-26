package Task003;

/**
 *@author Elvira Batyrova
 * 402
 * 051
 */
public class ComplexNumber {
    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public ComplexNumber() {
        this(0, 0);
    }

    public ComplexNumber(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public ComplexNumber add(ComplexNumber comp) {
        return new ComplexNumber(a + comp.getA(), b + comp.getB());
    }

    public void add2(ComplexNumber comp) {
        a += comp.getA();
        b += comp.getB();
    }

    public ComplexNumber sub(ComplexNumber comp) {
        return new ComplexNumber(a - comp.getA(), b - comp.getB());
    }

    public void sub2(ComplexNumber comp) {
        a -= comp.getA();
        b -= comp.getB();
    }

    public ComplexNumber multNumber(double v) {
        return new ComplexNumber(a * v, b * v);
    }

    public void multNumber2(double v) {
        a *= v;
        b *= v;
    }

    public ComplexNumber mult(ComplexNumber comp) {
        return new ComplexNumber(a * comp.getA() + (-1) * b * comp.getB(), a * comp.getB() + b * comp.getA());
    }

    public void mult2(ComplexNumber comp) {
        a = a * comp.getA() + (-1) * b * comp.getB();
        b = a * comp.getB() + b * comp.getA();
    }

    public ComplexNumber div(ComplexNumber comp) {
        return new ComplexNumber((a * comp.getA() + b * comp.getB()) / (comp.getA() * comp.getA() + comp.getB() * comp.getB()),
                (a * (-1) * comp.getB() + b * comp.getA()) / (comp.getA() * comp.getA() + comp.getB() * comp.getB()));
    }

    public void div2(ComplexNumber comp) {
        a = (a * comp.getA() + b * comp.getB()) / (comp.getA() * comp.getA() + comp.getB() * comp.getB());
        b = (a * (-1) * comp.getB() + b * comp.getA()) / (comp.getA() * comp.getA() + comp.getB() * comp.getB());
    }

    public double length() {
        return Math.sqrt(a * a + b * b);
    }

    public String toString() {
        if (a != 0) {
            if (b > 0) {
                return (a + " + " + b + "i");
            } else if (b < 0) {
                return (a + " - " + (-1) * b + "i");
            } else {
                return (a + "");
            }
        } else if (b != 0) {
                return (b + "i");
        } else {
            return("0");
        }
    }
    public double arg() {
        if (a > 0) {
            return Math.atan(b / a);
        } else if (a == 0) {
            if (b > 0) {
                return Math.PI / 2;
            } else if (b < 0) {
                return -Math.PI / 2;
            } else {
                return 0;
            }
        } else {
            if (b > 0) {
                return Math.PI + Math.atan(b / a);
            } else if (b < 0) {
                return -Math.PI + Math.atan(b / a);
            } else {
                return 0;
            }
        }
    }
    public ComplexNumber pow(double n) {
        return new ComplexNumber (Math.pow(this.length(), n) * Math.cos(n * this.arg()), Math.pow(this.length(),n) * Math.sin(n * this.arg()));
    }

    public boolean equals(ComplexNumber comp) {
        return (a == comp.getA() && b == comp.getB());
    }

}
