package use_case.expired_food;

import entity.Ingredient;

import java.util.List;

/**
 * Interface for accessing and managing user data related to ingredients.
 * Provides methods to retrieve, update, and verify ingredients.
 */
public interface CheckExpiredIngredientUserDataAccessInterface {
    /**
     * Retrieves a list of all ingredients.
     *
     * @return A list of all ingredients.
     */
    List<Ingredient> getAllIngredients();
    /**
     * Checks if an ingredient with the specified name exists.
     *
     * @param ingredientName The name of the ingredient to check.
     * @return True if the ingredient exists, false otherwise.
     */
    boolean existsByIngredientName(String ingredientName);
    /**
     * Updates the list of current ingredients.
     *
     * @param currentIngredients The updated list of ingredients.
     */
    void setIngredients(List<Ingredient> currentIngredients);
}