/**
 * Created by Айгуль on 12.02.2016.
 */
public class Director implements Authority {
    @Override
    public String getStatus() {
        return "Director";
    }

    @Override
    public String getName() {
        return "Pupkin";
    }

    @Override
    public String saySorry() {
        return "Please, accept my apologies. We will cook this dish again for you";
    }

    @Override
    public String getWorkPlace() {
        return "Pupkin's Restaurant";
    }

}
