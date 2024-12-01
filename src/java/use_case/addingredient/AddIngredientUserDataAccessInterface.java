package use_case.addingredient;

import entity.Ingredient;

/**
 * Interface for accessing user data related to ingredients.
 * Provides methods to add an ingredient to a user's collection.
 */
public interface AddIngredientUserDataAccessInterface {
    /**
     * Adds an ingredient to the user's collection.
     *
     * @param userId The ID of the user.
     * @param ingredient The ingredient to be added.
     */
    void addIngredientToUser(String userId, Ingredient ingredient);
}