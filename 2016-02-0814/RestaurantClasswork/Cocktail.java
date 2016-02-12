import java.util.ArrayList;

/**
 * Created by Айгуль on 12.02.2016.
 */
public class Cocktail implements Drinkable, Cookable {

    private ArrayList<String> ingredients;
    private String recipeText;
    private int cost;
    private String ingr;
    private boolean vegetarian;

    @Override
    public void cook() {
        prepareIngredients();
        System.out.println("Cocktail is cooking");
    }

    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients for cooking cocktail: ");
        for (String ingr : ingredients)
            System.out.print(ingr + ", ");
    }

    @Override
    public void complain(Cookable courseName) {
        System.out.println("complaining");
    }

    @Override
    public void cookAgain() {
        System.out.println("The cocktail is cooking again. Please wait.");
        prepareIngredients();
        cook();
    }

    @Override
    public void changeRecipeText(String newRecipeText) {
        this.recipeText = newRecipeText;
    }

    @Override
    public String getRecipeText() {
        return recipeText;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void drink() {
        System.out.println("Drinking the " + ingr + " cocktail.");
    }

    @Override
    public void pour() {
        System.out.println("Pouring the " + ingr + " cocktail into the glass");
    }

    @Override
    public void pourOut() {
        System.out.println("Pouring the " + ingr + " cocktail out of the glass");
    }

    Cocktail(String ingr) {
        cost = 200;
        recipeText = "How to prepare the cocktail. Look in the Internet";
        this.vegetarian = true;

        ingredients = new ArrayList<String>();
        ingredients.add("milk");
        ingredients.add("sugar");
        ingredients.add("water");
        ingredients.add("ice");
        ingredients.add(ingr);
        this.ingr = ingr;
    }
}
