import java.util.ArrayList;

/**
 * Created by Айгуль on 12.02.2016.
 */
public interface Cookable {
    void cook();
    void prepareIngredients();
    void complain(Cookable courseName);
    void cookAgain();
    void changeRecipeText(String newRecipeText);
    String getRecipeText();
    int getCost();
    ArrayList<String> getIngredients();
    boolean isVegetarian();
}
