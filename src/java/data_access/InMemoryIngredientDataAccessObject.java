package data_access;

import entity.Ingredient;
import use_case.addorcancelingredient.AddorCancelIngredientIngredientDataAccessInterface;
import use_case.delete_ingredient.DeleteIngredientIngredientDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing ingredient data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryIngredientDataAccessObject implements AddorCancelIngredientIngredientDataAccessInterface,
        DeleteIngredientIngredientDataAccessInterface {

    private final Map<String, Ingredient> ingredients = new HashMap<>();
    private final List<Ingredient> ingredientsList = new ArrayList<>();

    @Override
    public boolean existsByIngredientName(String ingredientname) {
        return ingredients.containsKey(ingredientname);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredients.put(ingredient.getName(), ingredient);
        ingredientsList.add(ingredient);
    }

//    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientsList;
    }

    @Override
    public List<Ingredient> getCurrentIngredients() {
        return ingredientsList; }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient.getName());
        ingredientsList.remove(ingredient);
    }
}
