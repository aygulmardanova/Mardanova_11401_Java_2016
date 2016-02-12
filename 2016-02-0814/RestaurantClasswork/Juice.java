/**
 * Created by Айгуль on 12.02.2016.
 */
public class Juice implements Drinkable {
    @Override
    public void drink() {
        System.out.println("Drink juice");
    }

    @Override
    public void pour() {
        System.out.println("Pour juice from jar into the glass");
    }

    @Override
    public void pourOut() {
        System.out.println("Pour out the juice from the glass");
    }
}
