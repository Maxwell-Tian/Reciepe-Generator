package interface_adapter.ExpirationWarning;
import entity.Ingredient;
import use_case.expired_food.ExpiredIngredientInteractor;

import java.util.List;

/**
 * The ExpirationWarningController class serves as the interface between the
 * ExpirationWarningView and the ExpiredIngredientInteractor, handling user actions
 * and managing the flow of data.
 */
public class ExpirationWarningController {
    private final ExpiredIngredientInteractor interactor;

    /**
     * Constructs an ExpirationWarningController with the specified interactor.
     *
     * @param interactor the interactor managing the expiration warning use case
     * @throws IllegalArgumentException if the interactor is null
     */
    public ExpirationWarningController(ExpiredIngredientInteractor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor cannot be null");
        }
        this.interactor = interactor;
    }

    /**
     * Retrieves the list of expired ingredients.
     *
     * @return a list of expired ingredients
     */
    public List<Ingredient> getExpiredIngredients() {
        return interactor.execute();
    }

    /**
     * Executes the expiration warning use case and retrieves expired ingredients.
     *
     * @return a list of expired ingredients
     */
    public List<Ingredient> execute() {
        return interactor.execute();
    }

    /**
     * Deletes the specified ingredient from the system.
     *
     * @param ingredient the ingredient to delete
     */
    public void deleteSelectedIngredients(Ingredient ingredient) {
        interactor.deleteIngredients(ingredient);
    }

    /**
     * Switches the view back to the initial screen.
     */
    public void switchToInitialView() {
        interactor.switchToInitialView();
    }
}

