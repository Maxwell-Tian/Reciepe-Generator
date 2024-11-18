package data_access;

import entity.Ingredient;
import entity.Recipe;
import use_case.addorcancelingredient.AddorCancelIngredientIngredientDataAccessInterface;
import use_case.recipe_management.RecipeManagementUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing ingredient data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryIngredientDataAccessObject implements AddorCancelIngredientIngredientDataAccessInterface {

    private final Map<String, Ingredient> ingredients = new HashMap<>();

    @Override
    public boolean existsByIngredientName(String ingredientname) {
        return ingredients.containsKey(ingredientname);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
    }

}
