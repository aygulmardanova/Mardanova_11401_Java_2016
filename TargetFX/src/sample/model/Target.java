package sample.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by aygulmardanova on 19.05.16.
 */
public class Target {

    private int count;
    private double width;
    private double height;
    private Image image;

    private Circle circle;

    public Target() {}

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public void setPosition(double x, double y) {
        circle.setCenter(x, y);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX() {
        return circle.getX() - circle.getRadius();
    }

    public double getY() {
        return circle.getY() - circle.getRadius();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage( image, getX(), getY() );
    }

}
