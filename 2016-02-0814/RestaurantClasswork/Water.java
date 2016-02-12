/**
 * Created by Айгуль on 12.02.2016.
 */
public class Water implements Drinkable {
    @Override
    public void drink() {
        System.out.println("Drink water");
    }

    @Override
    public void pour() {
        System.out.println("Pour water from jar into the glass");
    }

    @Override
    public void pourOut() {
        System.out.println("Pour out the water from the glass");
    }
}
