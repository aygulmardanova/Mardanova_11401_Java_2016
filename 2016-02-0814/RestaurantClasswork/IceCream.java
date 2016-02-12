import java.util.ArrayList;

/**
 * Created by Айгуль on 12.02.2016.
 */
public class IceCream implements Eatable, Cookable {

    private String flavor;
    private String recipeText;
    private int cost;
    private ArrayList<String> ingredients;
    boolean vegetarian;

    @Override
    public void cook() {
        prepareIngredients();
        System.out.println("Preparing an ice-cream with " + flavor);
    }

    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients for " + flavor + " ice-cream before cooking: ");
        for (String ingr : ingredients)
            System.out.print(ingr + ", ");
    }

    @Override
    public void complain(Cookable courseName) {

    }

    @Override
    public void cookAgain() {
        System.out.println("Cooking the " + flavor + " ice-cream again");
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
        System.out.println("Eating the " + flavor + " ice-cream");
    }

    @Override
    public void eatWithWater() {
        Water water = new Water();
        System.out.println("Eating the " + flavor + " ice-cream with water:");
        water.drink();
    }

    IceCream(String flavor) {
        this.cost = 150;
        this.flavor = flavor;
        this.recipeText = "An ice-cream recipe. You can find it in the Internet.";
        this.vegetarian = true;

        ingredients = new ArrayList<String>();
        ingredients.add("milk");
        ingredients.add("sugar");
        ingredients.add("eggs");
        ingredients.add("cream");
        ingredients.add(flavor);
    }
}
