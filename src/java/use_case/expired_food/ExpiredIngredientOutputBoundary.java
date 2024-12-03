package use_case.expired_food;

import entity.Ingredient;

import java.util.List;

/**
 * The ExpiredIngredientOutputBoundary interface defines the output operations
 * for the expiration warning use case, facilitating interaction with the presenter.
 */
public interface ExpiredIngredientOutputBoundary {
//    void execute();
    /**
     * Deletes the specified ingredient.
     *
     * @param ingredient the ingredient to delete
     */
    //    void prepareFailView(String error);
    void deleteIngredient(Ingredient ingredient);

    void switchToInitialView();

    /**
     * Retrieves the list of current ingredients.
     *
     * @return a list of current ingredients
     */
    List<Ingredient> getCurrentIngredients();
}