/**
 * Created by Айгуль on 12.02.2016.
 */
public class MyRestaurant implements Restaurant {
    @Override
    public String getName() {
        return "Pupkin's Restaurant";
    }

    @Override
    public Authority getDirector() {
        return null;
    }

    @Override
    public String getAddress() {
        return "Pushkin street, Kazan";
    }
}
