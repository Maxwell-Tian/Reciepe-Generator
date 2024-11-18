package use_case.delete_ingredient;

/**
 * The Delete Ingredient Use Case.
 */
public interface DeleteIngredientInputBoundary {

    /**
     * Execute the Delete Ingredient Use Case.
     * @param deleteIngredientInputData the input data for this use case
     */
    void execute(DeleteIngredientInputData deleteIngredientInputData);

    /**
     * Executes the switch to Add Ingredient view use case.
     */
    void switchToAddIngredientView();

    /**
     * Executes the switch to Recipe view use case.
     */
    void switchToRecipeView();
}
