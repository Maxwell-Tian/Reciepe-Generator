package java.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User{

    private List<Ingredient> ingredients;

    public CommonUser() {
        this.ingredients = new ArrayList<>();
    }

    @Override
    public List<Ingredient> getIngredients() {return ingredients;}

    @Override
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
