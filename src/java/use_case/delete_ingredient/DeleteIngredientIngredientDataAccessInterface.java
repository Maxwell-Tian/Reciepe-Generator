package use_case.delete_ingredient;

import entity.Ingredient;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * DAO for the Delete Ingredient Use Case.
 */
public interface DeleteIngredientIngredientDataAccessInterface {

    /**
     * Returns the ingredients of the current user of the application.
     * @return the ingredients of the current user; empty list indicates that current user has not added any ingredient.
     */
    List<Ingredient> getCurrentIngredients() throws FileNotFoundException;

    /**
     * Delete the ingredient current user asked for.
     * @param ingredient the ingredient needs to be deleted.
     */
    void deleteIngredient(Ingredient ingredient);
}
