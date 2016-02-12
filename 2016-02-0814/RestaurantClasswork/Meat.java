import java.util.ArrayList;

/**
 * Created by Айгуль on 12.02.2016.
 */
public class Meat implements Eatable, Cookable {

    private ArrayList<String> ingredients;
    private String recipeText;
    private int cost;
    private boolean vegetarian;

    @Override
    public void cook() {
        prepareIngredients();
        System.out.println("Meat is cooking");
    }

    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients: ");
        for (String ingr : ingredients)
            System.out.print(ingr + ", ");
    }

    @Override
    public void complain(Cookable courseName) {

    }

    @Override
    public void cookAgain() {
        System.out.println("Cooking the meat again");
        prepareIngredients();
        cook();
    }

    @Override
    public void changeRecipeText(String newRecipeText) {
        recipeText = newRecipeText;
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
    public void eat() {
        System.out.println("Eating the meat");
    }

    @Override
    public void eatWithWater() {
        Water water = new Water();
        System.out.println("Eating the meat with water:");
        water.drink();
    }

    Meat() {
        this.cost = 300;
        vegetarian = false;
        this.recipeText = "Look the recipes for meat in some cooking book";
        ingredients = new ArrayList<String>();
        ingredients.add("meat");
        ingredients.add("salt");
        ingredients.add("species");
    }
}
