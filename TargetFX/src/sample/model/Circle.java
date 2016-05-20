package sample.model;

import javafx.geometry.Point2D;

/**
 * Created by aygulmardanova on 15.05.16.
 */

public class Circle {

    private Point2D center;
    private double  radius;

    public Circle(double x, double y, double r) {
        setCenter(x,y);
        setRadius(r);
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(double x, double y) {
        center = new Point2D(x,y);
    }

    public void setRadius(double r) {
        radius = r;
    }

    public double getX() {
        return center.getX();
    }

    public double getY() {
        return center.getY();
    }

    public double getRadius() {
        return radius;
    }

    public boolean containsPoint(double x, double y) {
        return (center.distance(x,y) < radius);
    }

    public int getPoint(double x, double y) {
        return 5 - (int)(center.distance(x, y) / 6.4);
    }

}
