package use_case.expired_food;

import entity.Ingredient;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * The ExpiredIngredientUserDataAccessInterface defines the operations for
 * accessing and managing ingredient data in the system.
 */
public interface ExpiredIngredientUserDataAccessInterface {

    /**
     * Retrieves the list of current ingredients.
     *
     * @return a list of current ingredients
     * @throws FileNotFoundException if the ingredient data cannot be found
     */
    List<Ingredient> getCurrentIngredients() throws FileNotFoundException;
//    boolean existsByIngredientName(String ingredientName);

    /**
     * Deletes the specified ingredient from the system.
     *
     * @param ingredient the ingredient to delete
     */
    void deleteIngredient(Ingredient ingredient);
}
